package eco.ast;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import polyglot.ast.Block;
import polyglot.ast.Node;
import polyglot.ast.Stmt_c;
import polyglot.ast.Term;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.visit.CFGBuilder;
import polyglot.visit.NodeVisitor;
import polyglot.visit.PrettyPrinter;

public class Sustainable_c extends Stmt_c implements Sustainable {

	private Block block;

	private Supply supply;

	private Demand demand;

	private Set<String> fieldTriggers = new HashSet<String>();

	public Sustainable_c(Position pos, Block block, Supply supply, Demand demand) {
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

  @Override
	public Node visitChildren(NodeVisitor v) {
		Block block = (Block) visitChild(this.block, v);
		Supply supply = (Supply) visitChild(this.supply, v);
		Demand demand = (Demand) visitChild(this.demand, v);
		return reconstruct(block, supply, demand);
	}

	protected Sustainable_c reconstruct(Block block, Supply supply, Demand demand) {
		if (block != this.block || 
        supply != this.supply || 
        demand != this.demand) {
			Sustainable_c n = (Sustainable_c) copy();
			n.block = block;
			n.supply = supply;
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
		// new Calibrator()

		w.write("ecor.CalibratorStack.push(new ecor.Calibrator() {"); w.newline();

		w.write("double supplyRatio = 0;"); w.newline();
		
		// Calibrator() instance variables
		w.write("public int mode = $UTILMODES.$MAX;"); w.newline();

		// Calibrator::getMode()
		w.write("public int getMode(int max) {"); w.newline();
		w.write("return mode;"); w.newline();
		w.write("}"); w.newline();
		
		// Calibrator::supply()
		w.write("public int supply() {"); w.newline();
		w.write("return ");
		this.supply.printSupply(w, tr);
		w.write(";");
		w.write("}"); w.newline();
		
		// Calibrator::calibrate
		this.supply.printCalibration(w, tr);
		
		w.write("public void adjust() {"); w.newline();
    // Calibrator supplyRation/demandRatio
		w.write("double demandRatio = (double)(");
		print(demand.getRest(), w, tr);
		w.write(")/(");
		print(demand.getOverall(), w, tr);
		w.write(");"); w.newline();

		// sratio/dration common
    w.write("System.out.println(supplyRatio + \" \" + demandRatio);"); w.newline();
		w.write("if (supplyRatio > demandRatio * 1.1 && mode < $UTILMODES.$MAX) ++mode;"); w.newline();
		w.write("else if (demandRatio > supplyRatio * 1.1 && mode > 0) --mode;"); w.newline();
		w.write("}"); w.newline();
		
		// End new Calibrator()
		w.write("});"); w.newline();
		block.prettyPrint(w, tr); w.newline();
		w.write("ecor.CalibratorStack.pop();");
	}
}
