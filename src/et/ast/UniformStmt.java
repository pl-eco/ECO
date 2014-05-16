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
		w.begin(0);
		w.begin(4);
		w.write("tools.CalibratorStack.push(new tools.Calibrator() {"); w.newline();
		w.begin(8);
		w.write("private tools.Calibrator original = tools.CalibratorStack.peek();"); w.newline();
		w.write("public int getMode(int max) {"); w.newline();
		w.end();
		w.write("return original.getMode($UTILMODES.$MAX);"); w.newline();
		w.write("}"); w.newline();
		w.begin(8);
		w.write("public Object calibrate(Object input) {"); w.newline();
		w.end();
		w.write("return input;"); w.newline();
		w.end();
		w.write("}"); w.newline();
		w.write("});"); w.newline();
		w.end();
		block.prettyPrint(w, tr); w.newline();
		w.write("tools.CalibratorStack.pop();");
	}

}
