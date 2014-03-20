package et.ast;

import et.ast.mswitch.MSwitchGroup;
import et.ast.mswitch.MSwitch_c;
import et.types.EnergyFlags;
import et.types.PatternType;
import polyglot.ast.*;
import polyglot.ext.jl5.ast.AnnotationElem;
import polyglot.ext.jl5.ast.JL5Import;
import polyglot.ext.jl5.ast.JL5MethodDecl;
import polyglot.ext.jl5.ast.ParamTypeNode;
import polyglot.types.Flags;
import polyglot.util.*;

import java.util.*;

import cs.ast.csNodeFactory_c;

/**
 * NodeFactory for et extension.
 */
public class etNodeFactory_c extends csNodeFactory_c implements etNodeFactory {

    @Override
    public LocalAssign LocalAssign(Position pos, Local left,
            Assign.Operator op, Expr right) {
        LocalAssign n = new EcoLocalAssign_c(pos, left, op, right);
        return n;
    }
    
    @Override
    public FieldAssign FieldAssign(Position pos, Field left,
            Assign.Operator op, Expr right) {
    	FieldAssign n = new EcoFieldAssign_c(pos, left, op, right);
        return n;
    }
	
	public Node Select(Expr pattern) {
		return new Select_c(pattern.position(), pattern);
	}
	
	public ClassDecl ClassDecl(Position pos, Flags flags,
			List<AnnotationElem> annotations, Id name, TypeNode superType,
			List<TypeNode> interfaces, ClassBody body,
			List<ParamTypeNode> paramTypes, EnergyFlags eFlags) {
		if (pos == null) {
			pos = body.position();
		}

		ClassDecl n = new ECMClassDecl_c(pos, flags, annotations, name,
				superType, interfaces, body, paramTypes, eFlags);
		return n;
	}

	@Override
	public Local Local(Position pos, Id name) {
		Local n = new ETLocal_c(pos, name);
		return n;
	}

	public MPattern_Init_c MPattern_Init(Position pos, TypeNode type,
			List<MPatternElement> list) {
		MPattern_Init_c pattern = new MPattern_Init_c(pos, type, list);
		return pattern;
	}

	public MPatternElement MPatternElement(Position pos, String label, Expr expr) {
		MPatternElement n = new MPatternElement(pos, label, expr);
		return n;
	}

	public MPattern MPattern(Position pos, TypeNode innerType) {
		MPattern pattern = new MPattern(pos, innerType);
		return pattern;
	}

	public MPatternApp MPatternApp(Position pos, String modeV, Expr pattern) {
		MPatternApp app = new MPatternApp(pos, modeV, pattern);
		return app;
	}

	public Attribute_c Attribute(Position pos, Expr obj) {
		Attribute_c attribute = new Attribute_c(pos, obj);
		return attribute;
	}

	public AttributeDecl AttributeDecl(Position pos, TypeNode returnType,
			Id name) {
		AttributeDecl at = new AttributeDecl(pos, returnType, name);
		return at;
	}

	public Sustainable Sustainable(Position pos, Block block, BSupply supply, Demand demand) {
		Sustainable sust = new Sustainable(pos, block, supply, demand);
		return sust;
	}
	
	public UniformStmt Uniform(Position pos, Block block){
		UniformStmt uniform = new UniformStmt(pos, block);
		return uniform;
	}

	public Demand Demand(Position pos, Expr finish, Expr progress) {
		Demand demand = new Demand(pos, finish, progress);
		return demand;
	}

	public BSupply BSupply(Position pos, Expr binary) {
		BSupply supply = new BSupply(pos, binary);
		return supply;
	}

	@Override
	public Field Field(Position pos, Receiver target, Id name) {
		Field n = new ETField_c(pos, target, name);
		return n;
	}

	public Adapt_c Adapt(Position pos, String s, Expr e) {
		Adapt_c n = new Adapt_c(pos, s, e);
		return n;
	}

	public Reconstruct_c Reconstruct(Position pos, Expr e, Expr modeExpr) {
		Reconstruct_c n = new Reconstruct_c(pos, e, modeExpr);
		return n;
	}

	public ReconstructDecl ReconstructDecl(Position pos, TypeNode returnType,
			Id name) {
		ReconstructDecl r = new ReconstructDecl(pos, returnType, name);
		return r;
	}

	@Override
	public Return Return(Position pos, Expr expr) {
		Return n = new ECMReturn_c(pos, expr);
		return n;
	}

	public Call Call(Position pos, Receiver target, List<TypeNode> typeArgs,
			Id name, List<Expr> args) {
		Call n = new DynamicECMCall_c(pos, target,
				CollectionUtil.nonNullList(typeArgs), name,
				CollectionUtil.nonNullList(args));
		// n = (Call)n.ext(extFactory.extCall());
		// n = (Call)n.del(delFactory.delCall());
		return n;
	}

	public ModeLit_c ModeLit(Position pos, String value) {
		ModeLit_c n = new ModeLit_c(pos, value);
		// n = (StringLit)n.ext(extFactory.extStringLit());
		// n = (StringLit)n.del(delFactory.delStringLit());
		return n;
	}

	public MSwitch_c MSwitch(Position pos, Expr expr, List elements) {
		MSwitch_c n = new MSwitch_c(pos, expr,
				CollectionUtil.nonNullList(elements));
		// n = (Switch) n.ext(extFactory.extSwitch());
		// n = (Switch) n.del(delFactory.delSwitch());
		return n;
	}

	public MSwitchGroup MSwitchGroup(ModeLit_c label, Expr expr, Position pos) {
		MSwitchGroup n = new MSwitchGroup(label, expr, pos);
		// n = (Switch) n.ext(extFactory.extSwitch());
		// n = (Switch) n.del(delFactory.delSwitch());
		return n;
	}

	@Override
	public SourceFile SourceFile(Position pos, PackageNode packageName,
			List<Import> imports, List<TopLevelDecl> decls) {
		SourceFile n = new ECMSourceFile_c(pos, packageName,
				CollectionUtil.nonNullList(imports),
				CollectionUtil.nonNullList(decls));
		return n;
	}
}
