package eco.ast;

import eco.types.PatternType;
import polyglot.ast.Expr;
import polyglot.ast.Node;
import polyglot.ast.Return;
import polyglot.types.Context;
import polyglot.types.CodeInstance;
import polyglot.types.MethodInstance;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.visit.TypeChecker;

public class EcoReturnExt extends EcoExt {
	
	@Override
	public Node typeCheck(TypeChecker tc) throws SemanticException {
		Context c = tc.context();
		CodeInstance ci = c.currentCode();

    Return ret = (Return) this.node();
    Expr expr = ret.expr();
		
		if (ci instanceof MethodInstance) {
			Type retType = ((MethodInstance) ci).returnType();
			if (!(retType instanceof PatternType) && 
          expr != null && 
          expr.type() instanceof PatternType) {
        Expr newExpr = (Expr) ((EcoNodeFactory) tc.nodeFactory()).Select(expr);
        return reconstruct(newExpr).typeCheck(tc);
			} 
		}

		return super.typeCheck(tc);
	}

  private Node reconstruct(Expr expr) {
    Return newReturn = (Return) this.node().copy();
    newReturn.expr(expr);
    return newReturn;
  }
}
