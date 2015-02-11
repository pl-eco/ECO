package eco.ast;

import polyglot.util.Position;
import polyglot.util.CodeWriter;
import polyglot.visit.PrettyPrinter;

@SuppressWarnings("serial")
public class BatterySupplyExpr_c extends SupplyExpr_c implements BatterySupplyExpr {
  
  public BatterySupplyExpr_c(Position pos) {
    super(pos);
  }

  @Override
  public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
    w.write("ecor.bsupply.BatterySupply.sharedSupply().getRemainingCapacity()");
  } 
  
}
