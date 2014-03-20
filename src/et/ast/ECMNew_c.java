package et.ast;
//package et.ast;
//
//import java.util.List;
//
//import cs.ast.CSNew_c;
//import cs.types.CSObjectType;
//import cs.util.EasyDebugger;
//
//import et.linersolve.ETValueHolder;
//import et.types.ETTYPE;
//import et.types.etTypeSystem_c;
//
//import polyglot.ast.ClassBody;
//import polyglot.ast.Expr;
//import polyglot.ast.New_c;
//import polyglot.ast.Node;
//import polyglot.ast.TypeNode;
//import polyglot.types.SemanticException;
//import polyglot.util.Position;
//import polyglot.visit.TypeChecker;
//
//public class ECMNew_c extends CSNew_c {
//
//	public ECMNew_c(Position pos, Expr outer, List<TypeNode> typeArgs,
//			TypeNode objectType, List<Expr> args, ClassBody body) {
//		super(pos, outer, typeArgs, objectType, args, body);
//	}
//
//	@Override
//	/*
//	 * The code here is to set the class level value to object level
//	 */
//	public Node typeCheck(TypeChecker tc) throws SemanticException {
//		Node ret = super.typeCheck(tc);
//
//		if (!(type() instanceof CSObjectType))
//			return ret;
//
//		CSObjectType objType = (CSObjectType) type();
//		ETValueHolder vh = ((etTypeSystem_c) tc.typeSystem())
//				.getValueHolder();
//
//		for (ETTYPE type : ETTYPE.values()) {
//			vh.setClassLevelToObject(objType.getAliasName(),
//					objType.fullClassName(), type);
//		}
//
//		return ret;
//	}
// }
