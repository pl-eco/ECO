package et.ast;

import et.types.PatternType;
import polyglot.ast.Expr;
import polyglot.ast.Local;
import polyglot.ast.LocalAssign_c;
import polyglot.ast.Node;
import polyglot.ast.Receiver;
import polyglot.types.SemanticException;
import polyglot.util.Position;
import polyglot.visit.TypeChecker;

public class EcoLocalAssign_c extends LocalAssign_c {

	public EcoLocalAssign_c(Position pos, Local left, Operator op, Expr right) {
		super(pos, left, op, right);
	}

	@Override
	public Node typeCheck(TypeChecker tc) throws SemanticException {
		if (!(left.type() instanceof PatternType) && right.type() instanceof PatternType) {
			Expr newright = (Expr) ((etNodeFactory_c) tc.nodeFactory()).Select(right);
			return reconstruct(left, newright).typeCheck(tc);
		} else {
			return super.typeCheck(tc);
		}
	}
}
