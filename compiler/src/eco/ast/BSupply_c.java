package eco.ast;

import polyglot.util.Position;
import polyglot.ast.Expr;

import polyglot.visit.PrettyPrinter;
import polyglot.util.CodeWriter;

public class BSupply_c extends Supply_c implements BSupply {

  public BSupply_c(Position pos, Expr supplyExpr) {
	  super(pos, supplyExpr);
  }
  
  @Override
  public void printSupply(CodeWriter w, PrettyPrinter tr) {
	  w.write("ecor.bsupply.BatterySupply.sharedSupply().getRemainingCapacity()");
  }

  @Override
  public void printCalibration(CodeWriter w, PrettyPrinter tr) {
    w.write("private double supplyLimit = ");
	  this.supplyExpr.prettyPrint(w, tr);
	  w.write(";"); w.newline();

		w.write("private int initialSupply = this.supply();"); w.newline();
    
		// Calibrator::calibrate()
		w.write("public Object calibrate(Object input) {"); w.newline();
		w.write("supplyRatio = (supplyLimit - (initialSupply - this.supply()))/supplyLimit;"); w.newline(); 
		w.write("return input;"); w.newline();
		w.write("}"); w.newline();
  }
  
}
