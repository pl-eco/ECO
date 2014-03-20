package et.ast;

import java.util.List;

import polyglot.ast.Expr;
import polyglot.ast.Node;
import polyglot.ast.Stmt_c;
import polyglot.ast.Term;
import polyglot.util.Position;
import polyglot.visit.CFGBuilder;
import polyglot.visit.NodeVisitor;

public class BSupply extends Stmt_c {
	private Expr numeric;

	public BSupply(Position pos, Expr expr) {
		super(pos);
		this.numeric = expr;
	}

	public Expr getNumeric() {
		return numeric;
	}
	
	@Override
	public Term firstChild() {
		return numeric;
	}

	@Override
	public <T> List<T> acceptCFG(CFGBuilder<?> v, List<T> succs) {
		v.visitCFG(numeric, this, EXIT);
		return succs;
	}

	@Override
	public Node visitChildren(NodeVisitor v) {
		Expr node = (Expr) visitChild(this.numeric, v);
		return reconstruct(node);
	}

	protected Stmt_c reconstruct(Expr node) {
		if (node != this.numeric) {
			BSupply n = (BSupply) copy();
			n.numeric = node;
			return n;
		}
		return this;
	}

	@Override
	public boolean isDisambiguated() {
		return super.isDisambiguated() && numeric.isDisambiguated();
	}
}
