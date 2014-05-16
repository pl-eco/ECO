package et.visit;

import java.util.HashSet;
import java.util.Set;

import et.ast.EcoField_c;
import et.ast.EcoLocalDecl_c;
import et.ast.EcoLocal_c;
import et.ast.EcoLocalAssign_c;
import et.ast.Sustainable;
import et.ast.Demand;
import et.ast.UniformStmt;
import et.ast.EcoFieldAssign_c;
import et.types.EcoLocalInstance_c;
import polyglot.ast.Assign;
import polyglot.ast.Call;
import polyglot.ast.Field;
import polyglot.ast.FieldAssign;
import polyglot.ast.Local;
import polyglot.ast.LocalAssign;
import polyglot.ast.LocalDecl;
import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import polyglot.ast.Special;
import polyglot.ast.Variable;
import polyglot.frontend.Job;
import polyglot.types.SemanticException;
import polyglot.types.TypeSystem;
import polyglot.visit.NodeVisitor;
import polyglot.visit.TypeChecker;

public class CalibratePass extends TypeChecker {
	public CalibratePass(Job job, TypeSystem ts, NodeFactory nf) {
		super(job, ts, nf);
	}

	boolean inSustainable = false;
	boolean inUniform = false;
	
	@Override
    protected NodeVisitor enterCall(Node n) throws SemanticException {
		if (n instanceof Sustainable) {
			inSustainable = true;
		} else if (n instanceof UniformStmt) {
			inUniform = true;
		}
		return this;
	}

	@Override
	public Node leaveCall(Node parent, Node old, Node n, NodeVisitor v) {
		if (n instanceof Sustainable) {
			inSustainable = false;
		} else if (n instanceof UniformStmt) {
			inUniform = false;
		} else if (n instanceof LocalDecl) {
			LocalDecl decl = (LocalDecl) n;
			if (((EcoLocalInstance_c) decl.localInstance()).calibrate) {
				((EcoLocalDecl_c) decl).markCalibrate();
			}
		} else if (n instanceof Local) {
			Local local = (Local) n;
			if (((EcoLocalInstance_c) context.findVariableSilent(local.name())).calibrate) {
				((EcoLocal_c) local).markCalibrate();
			}
		} else if (inSustainable && !inUniform && n instanceof LocalAssign) {	
			EcoLocalAssign_c assign = (EcoLocalAssign_c) n;
			if (assign.left() instanceof Local) {
				Local local = (Local) assign.left();
				if (((EcoLocalInstance_c) context.findVariableSilent(local.name())).calibrate) {
					assign.markCalibrate();
				}
			}
		} else if (n instanceof FieldAssign &&
				((inSustainable && !inUniform) || MarkPass.reachable(context.currentCode()))) {
			EcoFieldAssign_c assign = (EcoFieldAssign_c) n;
			if (assign.left() instanceof Field) {
				Field field = (Field) assign.left();
				for (Field demand : MarkPass.demandFields) {
					if ((field.target().type().isSubtype(demand.target().type()) ||
							demand.target().type().isSubtype(field.target().type())) &&
							field.name().equals(demand.name())) {
						assign.markCalibrate();
						break;
					}
				}
			}
		}
		return n;
	}
}
