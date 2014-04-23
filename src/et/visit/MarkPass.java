package et.visit;

import java.util.HashSet;
import java.util.Set;

import et.ast.ETField_c;
import et.ast.ETLocal_c;
import et.ast.Sustainable;
import et.ast.EcoFieldAssign_c;
import et.ast.Demand;
import polyglot.ast.Assign;
import polyglot.ast.Field;
import polyglot.ast.Local;
import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import polyglot.ast.Special;
import polyglot.frontend.Job;
import polyglot.types.SemanticException;
import polyglot.types.TypeSystem;
import polyglot.visit.NodeVisitor;
import polyglot.visit.TypeChecker;

public class MarkPass extends TypeChecker {
	public MarkPass(Job job, TypeSystem ts, NodeFactory nf) {
		super(job, ts, nf);
	}

	Sustainable sustainable = null;
	boolean demand = false;
	
	@Override
    protected NodeVisitor enterCall(Node n) throws SemanticException {
		if (n instanceof Sustainable) {
			sustainable = (Sustainable) n;
		} else if (n instanceof Demand) {
			demand = true;
		}
		return this;
	}
	
	@Override
	public Node leaveCall(Node parent, Node old, Node n, NodeVisitor v) {
		if (demand && n instanceof Field) {
			ETField_c field = (ETField_c) n;
			if (field.target() instanceof Special) {
				sustainable.addFieldTrigger(field.name());
			}
		} else if (demand && n instanceof Local) {
			ETLocal_c local = (ETLocal_c) n;
			sustainable.addLocalTrigger(local.name());
		} else if (n instanceof Sustainable) {
			sustainable = null;
		} else if (n instanceof Demand) {
			demand = false;
		}
		return n;
	}
}
