package et.visit;

import java.util.HashSet;
import java.util.Set;

import cs.types.CSObjectType;

import et.ast.EcoField_c;
import et.ast.EcoLocal_c;
import et.ast.Sustainable;
import et.ast.EcoFieldAssign_c;
import et.ast.Demand;
import et.types.EcoLocalInstance_c;
import polyglot.ast.Assign;
import polyglot.ast.Field;
import polyglot.ast.Local;
import polyglot.ast.NamedVariable;
import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import polyglot.ast.Special;
import polyglot.ast.Variable;
import polyglot.frontend.Job;
import polyglot.types.SemanticException;
import polyglot.types.TypeSystem;
import polyglot.visit.NodeVisitor;
import polyglot.visit.TypeChecker;

public class MarkPass extends TypeChecker {
	public MarkPass(Job job, TypeSystem ts, NodeFactory nf) {
		super(job, ts, nf);
	}

	boolean inDemand = false;
	
	@Override
    protected NodeVisitor enterCall(Node n) throws SemanticException {
		if (n instanceof Demand) {
			inDemand = true;
		}
		return this;
	}
	
	//TODO: create global hash set of alias name and field name
	// in pretty print, get CSGraph, checkAlias, compare current node's alias with
	// global list of aliases, if match print calibrate
	// preliminary check type of target and field name
	public static Set<Field> demandFields = new HashSet<Field>();
	
	@Override
	public Node leaveCall(Node parent, Node old, Node n, NodeVisitor v) {
		if (inDemand && n instanceof Field) {
			Field field = (Field) n;
			demandFields.add(field);
			//System.out.println("added demand field: " + field);
		} else if (inDemand && n instanceof Local) {
			Local local = (Local) n;
			((EcoLocalInstance_c) context.findVariableSilent(local.name())).markCalibrate();
		} else if (n instanceof Demand) {
			inDemand = false;
		}
		return n;
	}
}
