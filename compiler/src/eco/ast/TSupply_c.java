package eco.ast;

import polyglot.util.Position;
import polyglot.ast.Expr;

import polyglot.util.CodeWriter;
import polyglot.visit.PrettyPrinter;

public class TSupply_c extends Supply_c implements TSupply {

	public TSupply_c(Position pos, Expr supplyExpr) {
		super(pos, supplyExpr);
	}
	
	@Override
	public void printSupply(CodeWriter w, PrettyPrinter tr) {
    w.write("ecor.tsupply.TemperatureSupply.sharedSupply().getCurrentTemperature()");
	}

  @Override
  public void printCalibration(CodeWriter w, PrettyPrinter tr) {
    w.write("private double supplyLimit = ");
	  this.supplyExpr.prettyPrint(w, tr);
	  w.write(";"); w.newline();

		w.write("private int initialSupply = this.supply();"); w.newline();
    
		// Calibrator::calibrate()
		w.write("public Object calibrate(Object input) {"); w.newline();
		w.write("int currentTemperature = this.supply();"); w.newline();
		w.write("supplyRatio = (supplyLimit - (this.supply() - initialSupply))/supplyLimit;"); w.newline();
		w.write("return input;"); w.newline();
		w.write("}"); w.newline();
  }
  
}
