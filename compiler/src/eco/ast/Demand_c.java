package eco.ast;

import java.util.List;

import polyglot.ast.Block;
import polyglot.ast.Expr;
import polyglot.ast.Node;
import polyglot.ast.Stmt_c;
import polyglot.ast.Term;
import polyglot.util.Position;
import polyglot.visit.CFGBuilder;
import polyglot.visit.NodeVisitor;

public class Demand_c extends Stmt_c implements Demand {
	private Expr finishLine;
	private Expr progress;

	public Demand_c(Position pos, Expr finish, Expr progress) {
		super(pos);
		this.finishLine = finish;
		this.progress = progress;
	}
	
  @Override
	public Expr getOverall() {
		return finishLine;
	}
	
  @Override
	public Expr getRest() {
		return progress;
	}

	@Override
	public Term firstChild() {
		return finishLine;
	}

	@Override
	public <T> List<T> acceptCFG(CFGBuilder<?> v, List<T> succs) {
		v.visitCFG(finishLine, progress, ENTRY);
		v.visitCFG(progress, this, EXIT);
		return succs;
	}

	public Node visitChildren(NodeVisitor v) {
		Expr finishLine = (Expr) visitChild(this.finishLine, v);
		Expr progress = (Expr) visitChild(this.progress, v);

		return reconstruct(finishLine, progress);
	}

	protected Stmt_c reconstruct(Expr finish, Expr progress) {
		if (finish != this.finishLine || progress != this.progress) {
			Demand_c n = (Demand_c) copy();
			n.finishLine = finish;
			n.progress = progress;
			return n;
		}
		return this;
	}

	@Override
	public boolean isDisambiguated() {
		return super.isDisambiguated() && finishLine.isDisambiguated()
				&& progress.isDisambiguated();
	}
}
