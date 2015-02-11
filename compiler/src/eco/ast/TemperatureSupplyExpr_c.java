package eco.ast;

import polyglot.util.Position;
import polyglot.util.CodeWriter;
import polyglot.visit.PrettyPrinter;

@SuppressWarnings("serial")
public class TemperatureSupplyExpr_c extends SupplyExpr_c implements TemperatureSupplyExpr {
  
  public TemperatureSupplyExpr_c(Position pos) {
    super(pos);
  }
  
  @Override
  public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
    w.write("ecor.tsupply.TemperatureSupply.sharedSupply().getCurrentTemperature()");
  }

}
