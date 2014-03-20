package et.ast;

import java.util.List;

import polyglot.ast.Block;
import polyglot.ast.Node;
import polyglot.ast.Stmt_c;
import polyglot.ast.Term;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.visit.CFGBuilder;
import polyglot.visit.NodeVisitor;
import polyglot.visit.PrettyPrinter;

public class UniformStmt extends Stmt_c{
	private Block block;
	public UniformStmt(Position pos, Block block) {
		super(pos);
		this.block = block;
	}

	@Override
	public Term firstChild() {
		return block;
	}

	public Node visitChildren(NodeVisitor v) {
		Block block = (Block) visitChild(this.block, v);
		return reconstruct(block);
	}

	protected Stmt_c reconstruct(Block block) {
		if (block != this.block) {
			UniformStmt n = (UniformStmt) copy();
			n.block = block;
			return n;
		}
		return this;
	}

	@Override
	public <T> List<T> acceptCFG(CFGBuilder<?> v, List<T> succs) {
		v.visitCFG(block, this, EXIT);
		return succs;
	}
	
	@Override
	public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
		block.prettyPrint(w, tr);
	}

}
