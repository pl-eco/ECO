package et.visit;

import java.util.HashSet;
import java.util.Set;

import et.ast.ETLocalDecl_c;
import et.ast.ETLocal_c;
import et.ast.EcoLocalAssign_c;
import et.ast.Sustainable;
import et.ast.Demand;
import et.ast.UniformStmt;
import et.ast.EcoFieldAssign_c;
import et.types.ETLocalInstance_c;
import polyglot.ast.Assign;
import polyglot.ast.Field;
import polyglot.ast.Local;
import polyglot.ast.LocalDecl;
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

	Set<String> fieldTriggers = null;
	Set<String> localTriggers = null;
	boolean inUniform = false;

	@Override
    protected NodeVisitor enterCall(Node n) throws SemanticException {
		if (n instanceof Sustainable) {
			fieldTriggers = ((Sustainable) n).getFieldTriggers();
			localTriggers = ((Sustainable) n).getLocalTriggers();
		} else if (n instanceof UniformStmt) {
			inUniform = true;
		}
		return this;
	}
	
	@Override
	public Node leaveCall(Node parent, Node old, Node n, NodeVisitor v) {
		if (n instanceof LocalDecl) {
			LocalDecl decl = (LocalDecl) n;
			if (((ETLocalInstance_c) decl.localInstance()).calibrate) {
				((ETLocalDecl_c) decl).markCalibrate();
			}
		} else if (n instanceof Local) {
			Local local = (Local) n;
			if (((ETLocalInstance_c) context.findLocalSilent(local.name())).calibrate) {
				((ETLocal_c) local).markCalibrate();
			}
		} else if (fieldTriggers != null && !inUniform && n instanceof EcoFieldAssign_c) {
			EcoFieldAssign_c assign = (EcoFieldAssign_c) n;
			if (assign.left() instanceof Field) {
				Field field = (Field) assign.left();
				if (field.target() instanceof Special && fieldTriggers.contains(field.name())) {
					assign.markCalibrate();
				}
			}
		} else if (localTriggers != null && !inUniform && n instanceof EcoLocalAssign_c) {	
			EcoLocalAssign_c assign = (EcoLocalAssign_c) n;
			if (assign.left() instanceof Local) {
				Local local = (Local) assign.left();
				if (localTriggers.contains(local.name())) {
					assign.markCalibrate();
				}
			}
		} else if (n instanceof Sustainable) {
			fieldTriggers = null;
			localTriggers = null;
		} else if (n instanceof UniformStmt) {
			inUniform = false;
		}
		return n;
	}
}
