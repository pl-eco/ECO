package eco.ast;

import polyglot.ast.*;
import polyglot.ext.jl5.ast.AnnotationElem;
import polyglot.types.Flags;
import polyglot.util.*;

import java.util.*;

import cs.ast.csNodeFactory_c;

/**
 * NodeFactory for eco extension.
 */
public class EcoNodeFactory_c extends csNodeFactory_c implements EcoNodeFactory {

	@Override
	public FieldDecl FieldDecl(Position pos, Flags flags,
			List<AnnotationElem> annotations, TypeNode type, Id name, Expr init) {
		FieldDecl n = new EcoFieldDecl_c(pos, flags, annotations, type, name,
				init);
		n = (FieldDecl) n.ext(extFactory().extFieldDecl());
		n = (FieldDecl) n.del(delFactory().delFieldDecl());
		return n;
	}

	@Override
    public LocalDecl LocalDecl(Position pos, Flags flags, TypeNode type,
            Id name, Expr init) {
        LocalDecl n = new EcoLocalDecl_c(pos, flags, type, name, init);
        return n;
    }
	
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

    @Override
    public Binary Binary(Position pos, Expr left, Binary.Operator op, Expr right) {
        Binary n = new EcoBinary_c(pos, left, op, right);
        //n = (Binary) n.ext(extFactory.extBinary());
        //n = (Binary) n.del(delFactory.delBinary());
        return n;
    }
	
	public Node Select(Expr pattern) {
		return new Select_c(pattern.position(), pattern);
	}

	@Override
	public Local Local(Position pos, Id name) {
		Local n = new EcoLocal_c(pos, name);
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
		Field n = new EcoField_c(pos, target, name);
		return n;
	}

	@Override
	public Return Return(Position pos, Expr expr) {
		Return n = new EcoReturn_c(pos, expr);
		return n;
	}
}
