package eco.ast;

import eco.types.PatternType;
import eco.visit.MarkPass;

import polyglot.ast.LocalAssign;
import polyglot.ast.Expr;
import polyglot.ast.FieldAssign;
import polyglot.ast.Node;
import polyglot.types.SemanticException;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.TypeChecker;
import polyglot.ast.Field;
import polyglot.ast.Special;

public class EcoFieldAssignExt extends EcoExt {
	private boolean calibrate = false;

  public boolean calibrate() {
    return this.calibrate;
  }

  public void calibrate(boolean calibrate) {
    this.calibrate = calibrate;
  }

  // TODO: Let's remove this and use the setter directly?
	public void markCalibrate() {
		calibrate = true;
	}

  // TODO: Exposing the inner node left/right is only a problem
  // when the compiler passes logic is defined outside of the node.
  // This will be removed on calibration is pushed to the node
  // just like typecheck and prettyPrint.
  public Node left() {
    return ((LocalAssign) this.node()).left();
  }

  public Node right() {
    return ((LocalAssign) this.node()).right();
  } 

	@Override
	public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
    if (calibrate) {
      calibrate = false;
      Field field = (Field) this.node();
      for (Field demand : MarkPass.demandFields) {
        //if (!demand.name().equals(field.name())) continue;
        if (field.target() instanceof Special &&
            demand.target() instanceof Special) {
          calibrate = true;
          break;
        }
      }
    }

		if (calibrate) {
			w.write("ecor.CalibratorStack.calibrate(");
		}
		super.prettyPrint(w, tr);
		if (calibrate) {
			w.write(")");
		}
	}
	
	@Override
	public Node typeCheck(TypeChecker tc) throws SemanticException {
    FieldAssign fieldAssign = (FieldAssign) this.node();
    Expr left = fieldAssign.left();
    Expr right = fieldAssign.right();

		if (!(left.type() instanceof PatternType) 
        && right.type() instanceof PatternType) {
			Expr newRight = (Expr) ((EcoNodeFactory) tc.nodeFactory()).Select(right);
			return reconstruct(left, newRight).typeCheck(tc);
		} else {
			return super.typeCheck(tc);
		}
	}

  private Node reconstruct(Expr left, Expr right) {
    FieldAssign e = (FieldAssign) this.node().copy();
    e.left(left);
    e.right(right);
    return e;
  }

}
