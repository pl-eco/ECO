package et.visit;

import java.util.HashSet;
import java.util.Set;

import et.ast.Sustainable;
import et.ast.Demand;
import et.ast.UniformStmt;
import et.ast.EcoFieldAssign_c;
import polyglot.ast.Assign;
import polyglot.ast.Field;
import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import polyglot.ast.Special;
import polyglot.frontend.Job;
import polyglot.types.SemanticException;
import polyglot.types.TypeSystem;
import polyglot.visit.NodeVisitor;
import polyglot.visit.TypeChecker;

public class CalibratePass extends TypeChecker {
	public CalibratePass(Job job, TypeSystem ts, NodeFactory nf) {
		super(job, ts, nf);
	}

	Set<String> triggers = null;
	boolean inUniform = false;

	@Override
    protected NodeVisitor enterCall(Node n) throws SemanticException {
		if (n instanceof Sustainable) {
			triggers = ((Sustainable) n).getTriggers();
		} else if (n instanceof UniformStmt) {
			inUniform = true;
		}
		return this;
	}
	
	@Override
	public Node leaveCall(Node parent, Node old, Node n, NodeVisitor v) {
		if (triggers != null && !inUniform && n instanceof EcoFieldAssign_c) {
			EcoFieldAssign_c assign = (EcoFieldAssign_c) n;
			if (assign.left() instanceof Field) {
				Field field = (Field) assign.left();
				if (field.target() instanceof Special && triggers.contains(field.name())) {
					assign.markCalibrate();
				}
			} 
		} else if (n instanceof Sustainable) {
			triggers = null;
		} else if (n instanceof UniformStmt) {
			inUniform = false;
		}
		return n;
	}
}
