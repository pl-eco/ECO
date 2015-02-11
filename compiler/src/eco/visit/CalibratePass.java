package eco.visit;

import eco.ast.EcoExt;
import eco.ast.EcoFieldAssignExt;
import eco.ast.EcoLocalAssignExt;
import eco.ast.EcoLocalDeclExt;
import eco.ast.EcoLocalExt;
import eco.ast.Sustainable;
import eco.ast.UniformStmt;
import eco.types.EcoLocalInstance;

import polyglot.ast.Field;
import polyglot.ast.FieldAssign;
import polyglot.ast.Local;
import polyglot.ast.LocalAssign;
import polyglot.ast.LocalDecl;
import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import polyglot.frontend.Job;
import polyglot.types.SemanticException;
import polyglot.types.TypeSystem;
import polyglot.visit.NodeVisitor;
import polyglot.visit.TypeChecker;

public class CalibratePass extends TypeChecker {
	private boolean inSustainable = false;

	private boolean inUniform = false;

	public CalibratePass(Job job, TypeSystem ts, NodeFactory nf) {
		super(job, ts, nf);
	} 
	
	@Override
  protected NodeVisitor enterCall(Node n) throws SemanticException {
	  if (n instanceof Sustainable) {
	    inSustainable = true;
    } else if (n instanceof UniformStmt) {
	    inUniform = true;
    }
		return this;
	}

  // TODO: This should be a method in each node, a NodeOp
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

    EcoExt ext = EcoExt.ext(n);
		if (ext instanceof EcoLocalDeclExt) {
			EcoLocalDeclExt localDecl = (EcoLocalDeclExt) ext;
      EcoLocalInstance localInstance = (EcoLocalInstance) localDecl.localInstance();
			if (localInstance.calibrate()) {
				localDecl.markCalibrate();
			}
      return n;
    }

		if (ext instanceof EcoLocalExt) {
			EcoLocalExt local = (EcoLocalExt) ext;
      EcoLocalInstance localInstance = 
        (EcoLocalInstance) context.findVariableSilent(local.name());
			if (localInstance.calibrate()) {
				local.markCalibrate();
			}
      return n;
    }

		if (inSustainable && !inUniform && ext instanceof EcoLocalAssignExt) {	
			EcoLocalAssignExt localAssign = (EcoLocalAssignExt) ext;
			if (localAssign.left() instanceof Local) {
				Local local = (Local) localAssign.left();
        EcoLocalInstance localInstance = (EcoLocalInstance) context.findVariableSilent(local.name());
				if (localInstance.calibrate()) {
					localAssign.markCalibrate();
				}
			}
      return n;
    }

		if (ext instanceof EcoFieldAssignExt 
        && ((inSustainable && !inUniform) || MarkPass.reachable(context.currentCode()))) {
			EcoFieldAssignExt fieldAssign = (EcoFieldAssignExt) ext;
			if (fieldAssign.left() instanceof Field) {
				Field field = (Field) fieldAssign.left();
				for (Field demand : MarkPass.demandFields) {
					if ((field.target().type().isSubtype(demand.target().type()) 
                || demand.target().type().isSubtype(field.target().type())) 
                && field.name().equals(demand.name())) {
						fieldAssign.markCalibrate();
						break;
					}
				}
			}
      return n;
		}

		return n;
	}
}
