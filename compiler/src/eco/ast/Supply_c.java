package eco.ast;

import java.util.List;

import polyglot.ast.Expr;
import polyglot.ast.Node;
import polyglot.ast.Stmt_c;
import polyglot.ast.Term;
import polyglot.util.Position;
import polyglot.visit.CFGBuilder;
import polyglot.visit.NodeVisitor;
import polyglot.visit.PrettyPrinter;
import polyglot.util.CodeWriter;

public class Supply_c extends Stmt_c implements Supply {
	protected Expr supplyExpr;

	public Supply_c(Position pos, Expr supplyExpr) {
		super(pos);
		this.supplyExpr = supplyExpr;
	}
	
  @Override 
	public Expr getNumeric() {
		return null;
	}
	
  @Override 
	public Expr getSupplyExpr() {
	  return this.supplyExpr;
	}

	@Override
	public Term firstChild() {
		return this.supplyExpr;
	}

	@Override
	public <T> List<T> acceptCFG(CFGBuilder<?> v, List<T> succs) {
		v.visitCFG(this.supplyExpr, this, EXIT);
		return succs;
	}

	@Override
	public Node visitChildren(NodeVisitor v) {
		Expr node = (Expr) visitChild(this.supplyExpr, v);
		return reconstruct(node);
	}

	protected Stmt_c reconstruct(Expr node) {
		if (node != this.supplyExpr) {
			Supply_c n = (Supply_c) copy();
			n.supplyExpr = node;
			return n;
		}
		return this;
	}

	@Override
	public boolean isDisambiguated() {
		return super.isDisambiguated() && this.supplyExpr.isDisambiguated();
	}
	
  @Override
	public void printSupply(CodeWriter w, PrettyPrinter tr) {
	  // Template method
	}
	
  @Override
  public void printCalibration(CodeWriter w, PrettyPrinter tr) {
	  // Template method
	}
}
