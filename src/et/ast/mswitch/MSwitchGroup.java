package et.ast.mswitch;

import java.util.List;




import et.ast.ModeLit_c;
import et.types.ETTYPE;
import et.types.EnergyFlags;
import polyglot.ast.Expr;
import polyglot.ast.Node;
import polyglot.ast.SwitchElement;
import polyglot.ast.Term;
import polyglot.ast.Term_c;
import polyglot.types.Context;
import polyglot.util.Position;
import polyglot.visit.CFGBuilder;
import polyglot.visit.NodeVisitor;

public class MSwitchGroup extends Term_c implements SwitchElement {

	public MSwitchGroup(ModeLit_c label, Expr expr, Position pos) {
		super(pos);
		this.label = label;
		this.expr = expr;
	}

	private ModeLit_c label;
	private Expr expr;

	public ModeLit_c getLabel() {
		return label;
	}

	public Expr getExpr() {
		return expr;
	}

	public Context enterScope(Context c) {
		// for default case
		if (label == null)
			return c;

		((ECMContext_c) c).setValues(EnergyFlags.createEType(ETTYPE.MODE,
				label.value()));
		return c;
	}

	protected MSwitchGroup reconstruct(ModeLit_c lit, Expr expr) {
		if (lit != this.label || expr != this.expr) {
			MSwitchGroup n = (MSwitchGroup) copy();
			n.label = lit;
			n.expr = expr;
			return n;
		}
		return this;
	}

	public Node visitChildren(NodeVisitor v) {
		ModeLit_c lit = (ModeLit_c) visitChild(this.label, v);
		Expr expr = (Expr) visitChild(this.expr, v);
		return reconstruct(lit, expr);
	}

	public Term firstChild() {
		return label;
	}

	public List acceptCFG(CFGBuilder v, List succs) {
		v.visitCFG(label, this, EXIT);
		v.visitCFG(expr, this, EXIT);
		return succs;
	}
}
