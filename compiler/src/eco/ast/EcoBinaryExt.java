package eco.ast;

import eco.types.PatternType;

import polyglot.ast.Binary;
import polyglot.ast.Binary_c;
import polyglot.ast.Expr;
import polyglot.ast.Node;
import polyglot.types.SemanticException;
import polyglot.visit.TypeChecker;
import polyglot.ext.jl5.ast.JL5BinaryExt;

public class EcoBinaryExt extends EcoExt {

	@Override
  //public Node typeCheckOverride(Node parent, TypeChecker tc) throws SemanticException {
	public Node typeCheck(TypeChecker tc) throws SemanticException {
    Binary b = (Binary) this.node();

    Expr left = b.left();
    Expr right = b.right();

		if (left.type() instanceof PatternType || right.type() instanceof PatternType) {
			Expr newleft = left, newright = right;
			if (left.type() instanceof PatternType) {
				newleft = (Expr) ((EcoNodeFactory_c) tc.nodeFactory()).Select(left);
			}
			if (right.type() instanceof PatternType) {
				newright = (Expr) ((EcoNodeFactory_c) tc.nodeFactory()).Select(right);
			}
			return reconstruct(newleft, newright).typeCheck(tc);
		} else {
			return super.typeCheck(tc);
		}
	}

  private Node reconstruct(Expr left, Expr right) {
    Binary b = (Binary) this.node().copy();
    b = b.left(left);
    b = b.right(right);
    return b;
  }

}
