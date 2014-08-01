package eco.visit;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import eco.ast.Demand;
import eco.ast.Sustainable;
import eco.ast.UniformStmt;
import eco.types.EcoLocalInstance_c;
import polyglot.ast.Call;
import polyglot.ast.ConstructorDecl;
import polyglot.ast.Field;
import polyglot.ast.Local;
import polyglot.ast.MethodDecl;
import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import polyglot.ast.ProcedureDecl;
import polyglot.frontend.Job;
import polyglot.types.CodeInstance;
import polyglot.types.MethodInstance;
import polyglot.types.SemanticException;
import polyglot.types.TypeSystem;
import polyglot.visit.NodeVisitor;
import polyglot.visit.TypeChecker;

public class MarkPass extends TypeChecker {
	public MarkPass(Job job, TypeSystem ts, NodeFactory nf) {
		super(job, ts, nf);
	}

	boolean inSustainable = false;
	boolean inUniform = false;
	boolean inDemand = false;
	
	static Map<CodeInstance, Set<CodeInstance>> callGraph = new HashMap<CodeInstance, Set<CodeInstance>>();
	
	static Set<CodeInstance> sustainableCalls = new HashSet<CodeInstance>();
	
	@Override
    protected NodeVisitor enterCall(Node n) throws SemanticException {
		if (n instanceof Sustainable) {
			inSustainable = true;
		} else if (n instanceof UniformStmt) {
			inUniform = true;
		} else if (n instanceof Demand) {
			inDemand = true;
		} else if (n instanceof ProcedureDecl) {
			callGraph.put(context.currentCode(), new HashSet<CodeInstance>());
		}
		return this;
	}
	public static Set<Field> demandFields = new HashSet<Field>();

	/**
	 * create global hash set of alias name and field name
	 * in pretty print, get CSGraph, checkAlias, compare current node's alias with
	 * global list of aliases, if match print calibrate
	 * preliminary check type of target and field name
	 * 
	 */
	
	/**
	 * Marks everything in the demand blocks
	 * build call graph
	 */
	@Override
	public Node leaveCall(Node parent, Node old, Node n, NodeVisitor v) {
		if (n instanceof Sustainable) {
			inSustainable = false;
		} else if (n instanceof UniformStmt) {
			inUniform = false;
		} else if (n instanceof Demand) {
			inDemand = false;
		} else if (inDemand && n instanceof Local) {
			Local local = (Local) n;
			((EcoLocalInstance_c) context.findVariableSilent(local.name())).calibrate = true;
		} else if (inDemand && n instanceof Field) {
			Field field = (Field) n;
			demandFields.add(field);
		} else if (n instanceof Call) {
			Call call = (Call) n;
			callGraph.get(context.currentCode()).add(call.methodInstance());
			if (inSustainable && !inUniform) {
				sustainableCalls.add(call.methodInstance());
			}
		} 
		return n;
	}
	
	public static boolean reachable(CodeInstance from, CodeInstance to) {
		if (!callGraph.containsKey(from)) return false;
		if (callGraph.get(from).contains(to)) return true;
		for (CodeInstance ci : callGraph.get(from))
			if (reachable(ci, to)) return true;
		return false;
	}
	
	public static boolean reachable(CodeInstance to) {
		for (CodeInstance from : sustainableCalls)
			if (from == to || reachable(from, to)) return true;
		return false;
	}
}
