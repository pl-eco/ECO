package eco.ast;

import eco.types.PatternType;
import polyglot.ast.Expr;
import polyglot.ast.Local;
import polyglot.ast.LocalAssign;
import polyglot.ast.Node;
import polyglot.ast.Receiver;
import polyglot.types.SemanticException;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.TypeChecker;

public class EcoLocalAssignExt extends EcoExt {
	private boolean calibrate = false;
	
  public boolean calibrate() {
    return this.calibrate;
  }

  public void calibrate(boolean calibrate) {
    this.calibrate = calibrate;
  }

  // TODO: Let's remove this and use the setter directly?
	public void markCalibrate() {
		this.calibrate = true;
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
			w.write("ecor.CalibratorStack.calibrate(");
		}
		super.prettyPrint(w, tr);
		if (calibrate) {
			w.write(")");
		}
	}
	
	@Override
	public Node typeCheck(TypeChecker tc) throws SemanticException {
    LocalAssign localAssign = (LocalAssign) this.node();
    Expr left = localAssign.left();
    Expr right = localAssign.right();

		if (!(left.type() instanceof PatternType) 
        && right.type() instanceof PatternType) {
			Expr newRight = (Expr) ((EcoNodeFactory) tc.nodeFactory()).Select(right);
			return reconstruct(left, newRight).typeCheck(tc);
		} else {
			return super.typeCheck(tc);
		}
	}

  private Node reconstruct(Expr left, Expr right) {
    LocalAssign l = (LocalAssign) this.node().copy();
    l.left(left);
    l.right(right);
    return l;
  }

}
