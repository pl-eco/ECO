package eco.ast;

import java.util.List;

import polyglot.ast.Expr_c;
import polyglot.ast.Node;
import polyglot.ast.Term;
import polyglot.types.SemanticException;
import polyglot.util.Position;
import polyglot.visit.CFGBuilder;
import polyglot.visit.TypeChecker;

@SuppressWarnings("serial")
public class SupplyExpr_c extends Expr_c implements SupplyExpr {
  
  public SupplyExpr_c(Position pos) {
    super(pos);
  }

  @Override
  public Term firstChild() {
    return null;
  }

  @Override
  public <T> List<T> acceptCFG(CFGBuilder<?> v, List<T> succs) {
    return succs;
  }
  
  @Override
  public Node typeCheck(TypeChecker tc) throws SemanticException {
    return type(tc.typeSystem().Int());
  }

}
