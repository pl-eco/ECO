package et.ast;

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

import et.ast.mswitch.MSwitchGroup;
import et.ast.mswitch.MSwitch_c;
import et.types.EnergyFlags;

/**
 * NodeFactory for et extension.
 */
public interface etNodeFactory extends JL5NodeFactory {
	public Adapt_c Adapt(Position pos, String s, Expr e);

	public Reconstruct_c Reconstruct(Position pos, Expr e, Expr mode);

	public ReconstructDecl ReconstructDecl(Position pos, TypeNode returnType,
			Id name);

	public MPattern_Init_c MPattern_Init(Position pos, TypeNode type,
			List<MPatternElement> list);

	public MPatternElement MPatternElement(Position pos, String label, Expr expr);

	public MPattern MPattern(Position pos, TypeNode innerType);

	public MPatternApp MPatternApp(Position pos, String modeV, Expr pattern);

	public Attribute_c Attribute(Position pos, Expr obj);

	public AttributeDecl AttributeDecl(Position pos, TypeNode returnType,
			Id name);

	public Sustainable Sustainable(Position pos, Block block, BSupply supply,
			Demand demand);

	public UniformStmt Uniform(Position pos, Block block);
	public BSupply BSupply(Position pos, Expr binary);

	public Demand Demand(Position pos, Expr finish, Expr progress);

	public ClassDecl ClassDecl(Position pos, Flags flags,
			List<AnnotationElem> annotations, Id name, TypeNode superType,
			List<TypeNode> interfaces, ClassBody body,
			List<ParamTypeNode> paramTypes, EnergyFlags eFlags);

	public ModeLit_c ModeLit(Position pos, String value);

	public MSwitch_c MSwitch(Position pos, Expr expr, List elements);

	public MSwitchGroup MSwitchGroup(ModeLit_c label, Expr expr, Position pos);
}
