package eco.ast;

import polyglot.ast.*;
import polyglot.ext.jl5.ast.AnnotationElem;
import polyglot.ext.jl5.ast.JL5NodeFactory;
import polyglot.ext.jl5.ast.ParamTypeNode;
import polyglot.types.Flags;
import polyglot.types.Package;
import polyglot.types.Type;
import polyglot.types.Qualifier;
import polyglot.util.*;

import java.util.*;

/**
 * NodeFactory for eco extension.
 */
public interface EcoNodeFactory extends JL5NodeFactory {
	public MPattern_Init_c MPattern_Init(Position pos, TypeNode type,
			List<MPatternElement> list);

	public MPatternElement MPatternElement(Position pos, String label, Expr expr);

	public MPattern MPattern(Position pos, TypeNode innerType);

	public Sustainable Sustainable(Position pos, Block block, BSupply supply,
			Demand demand);

	public UniformStmt Uniform(Position pos, Block block);
	public BSupply BSupply(Position pos, Expr binary);

	public Demand Demand(Position pos, Expr finish, Expr progress);
}
