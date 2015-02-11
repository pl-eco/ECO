package eco.ast;

import polyglot.ast.Expr;
import polyglot.ast.Stmt;
import polyglot.visit.PrettyPrinter;
import polyglot.util.CodeWriter;

public interface Supply extends Stmt {

	Expr getNumeric();

	Expr getSupplyExpr();

	void printSupply(CodeWriter w, PrettyPrinter tr);

  void printCalibration(CodeWriter w, PrettyPrinter tr);

}
