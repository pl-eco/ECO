package et.ast;

import et.types.PatternType;
import polyglot.ast.Binary;
import polyglot.ast.Binary_c;
import polyglot.ast.Expr;
import polyglot.ast.Expr_c;
import polyglot.ast.Node;
import polyglot.types.SemanticException;
import polyglot.util.Position;
import polyglot.visit.TypeChecker;

public class EcoBinary_c extends Binary_c {

	public EcoBinary_c(Position pos, Expr left, Operator op, Expr right) {
		super(pos, left, op, right);
		
	}

	@Override
	public Node typeCheck(TypeChecker tc) throws SemanticException {
		if (left.type() instanceof PatternType || right.type() instanceof PatternType) {
			Expr newleft = left, newright = right;
			if (left.type() instanceof PatternType) {
				newleft = (Expr) ((etNodeFactory_c) tc.nodeFactory()).Select(left);
			}
			if (right.type() instanceof PatternType) {
				newright = (Expr) ((etNodeFactory_c) tc.nodeFactory()).Select(right);
			}
			return reconstruct(newleft, newright).typeCheck(tc);
		} else {
			return super.typeCheck(tc);
		}
	}

}