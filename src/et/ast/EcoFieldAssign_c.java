package et.ast;

import et.types.PatternType;
import polyglot.ast.Expr;
import polyglot.ast.Field;
import polyglot.ast.FieldAssign_c;
import polyglot.ast.Local;
import polyglot.ast.LocalAssign_c;
import polyglot.ast.Node;
import polyglot.ast.Receiver;
import polyglot.types.SemanticException;
import polyglot.util.Position;
import polyglot.visit.TypeChecker;

public class EcoFieldAssign_c extends FieldAssign_c {
	

	public EcoFieldAssign_c(Position pos, Field left, Operator op, Expr right) {
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
