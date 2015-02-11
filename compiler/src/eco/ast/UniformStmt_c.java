package eco.ast;

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

public class UniformStmt_c extends Stmt_c implements UniformStmt {

	private Block block;

	public UniformStmt_c(Position pos, Block block) {
		super(pos);
		this.block = block;
	}

	@Override
	public Term firstChild() {
		return block;
	}

  @Override
	public Node visitChildren(NodeVisitor v) {
		Block block = (Block) visitChild(this.block, v);
		return reconstruct(block);
	}

	protected UniformStmt_c reconstruct(Block block) {
		if (block != this.block) {
			UniformStmt_c n = (UniformStmt_c) copy();
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
    w.begin(4);
		w.write("ecor.CalibratorStack.push(new ecor.Calibrator() {"); w.newline();
		w.write("private ecor.Calibrator original = ecor.CalibratorStack.peek();"); w.newline();
		w.write("public int getMode(int max) {"); w.newline();
		w.write("return original.getMode($UTILMODES.$MAX);"); w.newline();
		w.write("}"); w.newline();
		w.write("public Object calibrate(Object input) {"); w.newline();
		w.write("return input;"); w.newline();
		w.write("}"); w.newline();
		w.write("public int supply() {"); w.newline();
		w.write("return 0;"); w.newline();
		w.write("}"); w.newline();
    w.write("public void adjust() { }"); w.newline();
    w.end();
		w.write("});"); w.newline();
    w.begin(0);
    print(block, w, tr);
    w.end();
    w.newline();
		w.write("ecor.CalibratorStack.pop();");
	}

}
