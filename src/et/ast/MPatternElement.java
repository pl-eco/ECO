package et.ast;

import java.util.List;

import et.ast.mswitch.ECMContext_c;
import et.ast.mswitch.MSwitchGroup;
import et.types.ETTYPE;
import et.types.EnergyFlags;
import polyglot.ast.Expr;
import polyglot.ast.Expr_c;
import polyglot.ast.Node;
import polyglot.ast.SwitchElement;
import polyglot.ast.Term;
import polyglot.ast.Term_c;
import polyglot.types.Context;
import polyglot.util.Position;
import polyglot.visit.CFGBuilder;
import polyglot.visit.NodeVisitor;

public class MPatternElement extends Term_c implements SwitchElement {
	public MPatternElement(Position pos, String label, Expr expr) {
		super(pos);
		this.label = label;
		this.expr = expr;
	}

	private String label;
	private Expr expr;

	public String getLabel() {
		return label;
	}

	public Expr getExpr() {
		return expr;
	}

	public Context enterScope(Context c) {
		// for default case
		if (label == null)
			return c;

		((ECMContext_c) c).setValues(EnergyFlags
				.createEType(ETTYPE.MODE, label));
		return c;
	}

	protected MPatternElement reconstruct(String lit, Expr expr) {
		if (lit != this.label || expr != this.expr) {
			MPatternElement n = (MPatternElement) copy();
			n.label = lit;
			n.expr = expr;
			return n;
		}
		return this;
	}

	public Node visitChildren(NodeVisitor v) {
		Expr expr = (Expr) visitChild(this.expr, v);
		return reconstruct(label, expr);
	}

	public Term firstChild() {
		return expr;
	}

	public List acceptCFG(CFGBuilder v, List succs) {
		v.visitCFG(expr, this, EXIT);
		return succs;
	}
}
