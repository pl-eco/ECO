package eco.ast;

import polyglot.ast.*;
import polyglot.types.Flags;
import polyglot.types.Package;
import polyglot.types.Type;
import polyglot.types.Qualifier;
import polyglot.util.*;

import polyglot.ext.jl7.ast.JL7NodeFactory;

import java.util.*;

public interface EcoNodeFactory extends JL7NodeFactory { 
	BatterySupplyExpr BatterySupplyExpr(Position pos);

	BSupply BSupply(Position pos, Expr supplyExpr);

	Demand Demand(Position pos, Expr finish, Expr progress);

  ModesDecl ModesDecl(Position pos, 
                      Flags flags, 
                      Id id, 
                      TypeNode superClass,
			                List interfaces, 
                      ClassBody body); 

	MPattern MPattern(Position pos, TypeNode innerType);

	MPatternElement MPatternElement(Position pos, String label, Expr expr);

	MPatternInit MPatternInit(Position pos, 
                            TypeNode type,
			                      List<MPatternElement> list);

  Select Select(Expr pattern);

	Sustainable Sustainable(Position pos, 
                          Block block, 
                          Supply supply, 
                          Demand demand);

	TemperatureSupplyExpr TemperatureSupplyExpr(Position pos);

	TSupply TSupply(Position pos, Expr supplyExpr);

	UniformStmt UniformStmt(Position pos, Block block);


}
