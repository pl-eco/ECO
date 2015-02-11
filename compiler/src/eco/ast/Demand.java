package eco.ast;

import polyglot.ast.Expr;
import polyglot.ast.Stmt;

public interface Demand extends Stmt {

	public Expr getOverall();

	public Expr getRest();

}
