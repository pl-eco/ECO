package et.ast;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import polyglot.ast.Block;
import polyglot.ast.Block_c;
import polyglot.ast.Eval_c;
import polyglot.ast.Expr;
import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import polyglot.ast.Stmt;
import polyglot.ast.Stmt_c;
import polyglot.ast.Term;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.visit.CFGBuilder;
import polyglot.visit.NodeVisitor;
import polyglot.visit.PrettyPrinter;
//import tools.BatteryInfo;

public class Sustainable extends Stmt_c {
	private Block block;
	private BSupply supply;
	private Demand demand;
	private Set<String> fieldTriggers = new HashSet<String>();

	public Sustainable(Position pos, Block block, BSupply supply, Demand demand) {
		super(pos);
		this.block = block;
		this.demand = demand;
		this.supply = supply;
	}

	public void addFieldTrigger(String string) {
		fieldTriggers.add(string);
	}
	
	public Set<String> getFieldTriggers() {
		return fieldTriggers;
	}
	
	@Override
	public Term firstChild() {
		return block;
	}

	@Override
	public <T> List<T> acceptCFG(CFGBuilder<?> v, List<T> succs) {
		v.visitCFG(block, supply, ENTRY);
		v.visitCFG(supply, demand, ENTRY);
		v.visitCFG(demand, this, EXIT);
		return succs;
	}

	public Node visitChildren(NodeVisitor v) {
		Block block = (Block) visitChild(this.block, v);
		BSupply supply = (BSupply) visitChild(this.supply, v);
		Demand demand = (Demand) visitChild(this.demand, v);

		return reconstruct(block, supply, demand);
	}

	protected Stmt_c reconstruct(Block block, BSupply bsupply, Demand demand) {
		if (block != this.block || bsupply != this.supply
				|| demand != this.demand) {
			Sustainable n = (Sustainable) copy();
			n.block = block;
			n.supply = bsupply;
			n.demand = demand;
			return n;
		}
		return this;
	}

	@Override
	public boolean isDisambiguated() {
		return super.isDisambiguated() && block.isDisambiguated()
				&& supply.isDisambiguated() && demand.isDisambiguated();
	}
	@Override
	public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
		w.begin(0);
		w.begin(4);
		w.write("tools.CalibratorStack.push(new tools.Calibrator() {"); w.newline();
		w.write("public int mode = $UTILMODES.$MAX;"); w.newline();
		w.write("private double budget = " + supply.getNumeric() + ";"); w.newline();
		w.write("private int bInitial = tools.BatteryInfo.getRemainingCap();"); w.newline();
		w.begin(8);
		w.write("public int getMode(int max) {"); w.newline();
		w.end();
		w.write("return mode;"); w.newline();
		w.write("}"); w.newline();
		w.begin(8);
		w.write("public Object calibrate(Object input) {"); w.newline();
		w.write("int bLeft = tools.BatteryInfo.getRemainingCap();"); w.newline();
		w.write("double sratio = (budget - (bInitial - bLeft))/budget;"); w.newline();
		w.write("double dratio = (double)(");
		print(demand.getRest(), w, tr);
		w.write(")/(");
		print(demand.getOverall(), w, tr);
		w.write(");"); w.newline();
		w.write("if (sratio > dratio * 1.1 && mode < $UTILMODES.$MAX) ++mode;"); w.newline();
		w.write("else if (dratio > sratio * 1.1 && mode > 0) --mode;"); w.newline();
		w.end();
		w.write("return input;"); w.newline();
		w.end();
		w.write("}"); w.newline();
		w.end();
		w.write("});"); w.newline();
		block.prettyPrint(w, tr); w.newline();
		w.write("tools.CalibratorStack.pop();");
	}
}
