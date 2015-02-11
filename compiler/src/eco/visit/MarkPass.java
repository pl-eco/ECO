package eco.visit;

import eco.types.EcoLocalInstance;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import eco.ast.Demand;
import eco.ast.Sustainable;
import eco.ast.UniformStmt;
import eco.types.EcoLocalInstance_c;
import polyglot.ast.Call;
import polyglot.ast.Field;
import polyglot.ast.Local;
import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import polyglot.ast.ProcedureDecl;
import polyglot.frontend.Job;
import polyglot.types.CodeInstance;
import polyglot.types.SemanticException;
import polyglot.types.TypeSystem;
import polyglot.visit.NodeVisitor;
import polyglot.visit.TypeChecker;

public class MarkPass extends TypeChecker {
	private boolean inSustainable = false;

	private boolean inUniform = false;

	private boolean inDemand = false;

	private static Map<CodeInstance, Set<CodeInstance>> callGraph = 
    new HashMap<CodeInstance, Set<CodeInstance>>();
	
  private static Set<CodeInstance> sustainableCalls = 
    new HashSet<CodeInstance>();
	
	public static Set<Field> demandFields = 
    new HashSet<Field>();

	public MarkPass(Job job, TypeSystem ts, NodeFactory nf) {
		super(job, ts, nf);
	} 

	@Override
  protected NodeVisitor enterCall(Node n) throws SemanticException {
	  if (n instanceof Sustainable) {
	    inSustainable = true;
      return this;
    }

	  if (n instanceof UniformStmt) {
			inUniform = true;
      return this;
	  } 
    
    if (n instanceof Demand) {
			inDemand = true;
      return this;
	  } 
    
    if (n instanceof ProcedureDecl) {
			callGraph.put(context.currentCode(), new HashSet<CodeInstance>());
      return this;
	  }

		return this;
	}

  @Override
	public Node leaveCall(Node parent, Node old, Node n, NodeVisitor v) {
		if (n instanceof Sustainable) {
			inSustainable = false;
      return n;
    }

		if (n instanceof UniformStmt) {
			inUniform = false;
      return n;
    }

		if (n instanceof Demand) {
			inDemand = false;
      return n;
		} 
    
    if (inDemand && n instanceof Local) {
			Local local = (Local) n;
      EcoLocalInstance localInstance = 
        (EcoLocalInstance) context.findVariableSilent(local.name());
			localInstance.calibrate(true);
      return n;
		} 
    
    if (inDemand && n instanceof Field) {
			Field field = (Field) n;
			demandFields.add(field);
      return n;
		} 
    
    if (n instanceof Call) {
			Call call = (Call) n;
			Set<CodeInstance> codeInstances = callGraph.get(context.currentCode());
			if (codeInstances != null) {
				codeInstances.add(call.methodInstance());
				if (inSustainable && !inUniform) {
					sustainableCalls.add(call.methodInstance());
				}
			}
      return n;
		} 

		return n;
	}

	private static boolean traverseReachablePath(CodeInstance cur, 
											                         CodeInstance to, 
											                         Set<CodeInstance> visited) {
	  visited.add(cur);
		
		if (!callGraph.containsKey(cur)) return false;

		if (callGraph.get(cur).contains(to)) return true;

		for (CodeInstance ci : callGraph.get(cur)) {
			if (!visited.contains(ci)) {
				if (traverseReachablePath(ci, to, visited)) return true;
			}
		}

		return false;
	}
	
	public static boolean reachable(CodeInstance from, CodeInstance to) {
		return traverseReachablePath(from, to, new HashSet<CodeInstance>());
	}
	
	public static boolean reachable(CodeInstance to) {
		for (CodeInstance cur : sustainableCalls) {
			if (cur == to || 
				traverseReachablePath(cur, to, new HashSet<CodeInstance>())) {
				return true;
			}
		}
		return false;
	}
}
