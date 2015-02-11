package eco.ast;

import polyglot.ast.Expr;
import polyglot.ast.Term;

// TODO: MPatternElement should extend from Term OR SwitchElement?
public interface MPatternElement extends Term {

	String getLabel();

	Expr getExpr();

}
