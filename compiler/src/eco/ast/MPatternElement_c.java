package eco.ast;

import polyglot.ast.Expr;
import polyglot.ast.Node;
import polyglot.ast.SwitchElement;
import polyglot.ast.Term;
import polyglot.ast.Term_c;
import polyglot.util.Position;
import polyglot.visit.CFGBuilder;
import polyglot.visit.NodeVisitor;

import java.util.List;

public class MPatternElement_c extends Term_c implements MPatternElement, SwitchElement {

	private String label;

	private Expr expr;

	public MPatternElement_c(Position pos, String label, Expr expr) {
		super(pos);
		this.label = label;
		this.expr = expr;
	} 

  @Override
	public String getLabel() {
		return label;
	}

  @Override
	public Expr getExpr() {
		return expr;
	}

  @Override
	public Node visitChildren(NodeVisitor v) {
		Expr expr = (Expr) visitChild(this.expr, v);
		return reconstruct(label, expr);
	}
  
  protected MPatternElement_c reconstruct(String lit, Expr expr) {
		if (lit != this.label || expr != this.expr) {
			MPatternElement_c n = (MPatternElement_c) copy();
			n.label = lit;
			n.expr = expr;
			return n;
		}
		return this;
	}

  @Override
	public Term firstChild() {
		return expr;
	}

  @Override
	public List acceptCFG(CFGBuilder v, List succs) {
		v.visitCFG(expr, this, EXIT);
		return succs;
	}
}
