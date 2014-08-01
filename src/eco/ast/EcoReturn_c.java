package eco.ast;

import eco.types.PatternType;
import eco.util.Names;
import polyglot.ast.Expr;
import polyglot.ast.Node;
import polyglot.ast.Receiver;
import polyglot.ast.Return_c;
import polyglot.types.CodeInstance;
import polyglot.types.MethodInstance;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.TypeChecker;

public class EcoReturn_c extends Return_c {
	public EcoReturn_c(Position pos, Expr expr) {
		super(pos, expr);
	}

	@Override
	public Node typeCheck(TypeChecker tc) throws SemanticException {
		Type retType = ((MethodInstance) tc.context().currentCode()).returnType();
		if (!(retType instanceof PatternType) &&
				expr.type() instanceof PatternType) {
			Expr newExpr = (Expr) ((EcoNodeFactory_c) tc.nodeFactory()).Select(expr);
			return reconstruct(newExpr).typeCheck(tc);
		} else {
			return super.typeCheck(tc);
		}
	}

}
