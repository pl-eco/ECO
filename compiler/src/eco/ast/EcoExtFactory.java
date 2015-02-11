package eco.ast;

import polyglot.ast.Ext;
import polyglot.ast.ExtFactory;
import polyglot.ext.jl7.ast.JL7ExtFactory;

public interface EcoExtFactory extends JL7ExtFactory {

  Ext extModesDecl();

  Ext extMPattern();

  Ext extMPatternInit();

  Ext extMPatternElement();

  Ext extSustainable();

  Ext extSupply();

  Ext extBSupply();

  Ext extTSupply();

  Ext extSupplyExpr();

  Ext extBatterySupplyExpr();

  Ext extTemperatureSupplyExpr();

  Ext extDemand();

  Ext extSelect();

  Ext extUniformStmt();

}
