package eco.ast;

import polyglot.ast.*;
import polyglot.types.Flags;
import polyglot.util.*;

import polyglot.ext.jl5.ast.JL5Ext;
import polyglot.ext.jl5.ast.ParamTypeNode;
import polyglot.ext.jl5.ast.JL5ClassDeclExt;
import polyglot.ext.jl7.ast.JL7NodeFactory_c;

import java.util.*;

public class EcoNodeFactory_c extends JL7NodeFactory_c implements EcoNodeFactory {
  public EcoNodeFactory_c(EcoLang lang, EcoExtFactory extFactory) {
    super(lang, extFactory);
  }

  @Override
  public EcoExtFactory extFactory() {
    return (EcoExtFactory) super.extFactory();
  }

	@Override
	public BatterySupplyExpr BatterySupplyExpr(Position pos) {
    BatterySupplyExpr n = new BatterySupplyExpr_c(pos);
    n = ext(n, extFactory().extBatterySupplyExpr());
    return n;
	}

  @Override
	public BSupply BSupply(Position pos, Expr supplyExpr) {
		BSupply n = new BSupply_c(pos, supplyExpr);
    n = ext(n, extFactory().extBSupply());
    return n;
	}

  @Override
	public Demand Demand(Position pos, Expr finish, Expr progress) {
    Demand n = new Demand_c(pos, finish, progress);
    n = ext(n, extFactory().extDemand());
    return n;
	}

  @Override
  public ModesDecl ModesDecl(Position pos, 
                             Flags flags, 
                             Id id, 
                             TypeNode superClass,
			                       List interfaces, 
                             ClassBody body) {
    ModesDecl n = new ModesDecl_c(pos,
                                  flags,
                                  id,
                                  superClass,
                                  interfaces,
                                  body);
    n = ext(n, extFactory().extModesDecl());
    return n;
  }

  @Override
	public MPattern MPattern(Position pos, TypeNode innerType) {
		MPattern n = new MPattern_c(pos, innerType);
    n = ext(n, extFactory().extMPattern());
    return n;
	}

  @Override
  public MPatternElement MPatternElement(Position pos, 
                                         String label, 
                                         Expr expr) {
		MPatternElement n = new MPatternElement_c(pos, label, expr);
    n = ext(n, extFactory().extMPatternElement());
    return n;
	}

  @Override
  public MPatternInit MPatternInit(Position pos, 
                                   TypeNode type,
			                             List<MPatternElement> list) {
		MPatternInit n = new MPatternInit_c(pos, type, list);
    n = ext(n, extFactory().extMPatternInit());
    return n;
  }

	@Override
	public TemperatureSupplyExpr TemperatureSupplyExpr(Position pos) {
    TemperatureSupplyExpr n = new TemperatureSupplyExpr_c(pos);
    n = ext(n, extFactory().extTemperatureSupplyExpr());
    return n;
	}

  @Override
	public TSupply TSupply(Position pos, Expr supplyExpr) {
    TSupply n = new TSupply_c(pos, supplyExpr);
    n = ext(n, extFactory().extTSupply());
		return n;
	}

  @Override
	public Select Select(Expr pattern) {
    Select n = new Select_c(pattern.position(), pattern);
    n = ext(n, extFactory().extSelect());
    return n;
	}

  @Override
	public Sustainable Sustainable(Position pos, 
                                 Block block, 
                                 Supply supply, 
                                 Demand demand) {
    Sustainable n = new Sustainable_c(pos, block, supply, demand);
    n = ext(n, extFactory().extSustainable());
    return n;
	}

  @Override
	public UniformStmt UniformStmt(Position pos, Block block){
    UniformStmt n = new UniformStmt_c(pos, block);
    n = ext(n, extFactory().extUniformStmt());
    return n;
	}

}
