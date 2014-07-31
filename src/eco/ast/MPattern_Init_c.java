package eco.ast;

import java.util.List;

import eco.types.EcoTypeSystem_c;
import polyglot.ast.Expr_c;
import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import polyglot.ast.Term;
import polyglot.ast.TypeNode;
import polyglot.types.SemanticException;
import polyglot.types.TypeSystem;
import polyglot.util.CodeWriter;
import polyglot.util.CollectionUtil;
import polyglot.util.ListUtil;
import polyglot.util.Position;
import polyglot.visit.CFGBuilder;
import polyglot.visit.NodeVisitor;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.TypeChecker;

public class MPattern_Init_c extends Expr_c {
	private List<MPatternElement> list;
	private TypeNode typeNode;
	public MPattern_Init_c(Position pos, TypeNode typeNode, List<MPatternElement> list) {
		super(pos);
		this.typeNode = typeNode;
		this.list = ListUtil.copy(list, true);
	}

	// Add by steve 6/16/11
	public Node typeCheck(TypeChecker tc) throws SemanticException {
		TypeSystem ts = type().typeSystem();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (!ts.isSubtype(list.get(i).getExpr().type(), typeNode.type())) {
					throw new SemanticException(
							"Non-uniform types in pattern branches", position());
				}
			}
		} else {
			throw new SemanticException("The pattern cannot be empty",
					position());
		}
	
		return type(((EcoTypeSystem_c) ts).createPatternType(typeNode.type()));
		//return type(new PatternType(typeNode.type()));
	}

	public Node visitChildren(NodeVisitor v) {
		Node typeNode = visitChild(this.typeNode, v);
		List<MPatternElement> list = visitList(this.list, v);
		return reconstruct((TypeNode) typeNode, list);
	}

	/** Reconstruct the statement. */
	protected MPattern_Init_c reconstruct(TypeNode typeNode, List<MPatternElement> list) {
		if (!CollectionUtil.equals(list, this.list) || !typeNode.equals(this.typeNode)) {
			MPattern_Init_c n = (MPattern_Init_c) copy();
			n.typeNode = typeNode;
			n.list = ListUtil.copy(list, true);
			return n;
		}

		return this;
	}

	@Override
	public Term firstChild() {
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public <T> List<T> acceptCFG(CFGBuilder<?> v, List<T> succs) {
		v.visitCFGList(list, this, EXIT);
		return succs;
	}

	public void prettyPrint(CodeWriter w, PrettyPrinter pp) {
		/*
		 * {lofi: new A(), hifi: new A()} --> new $A_pattern(new A().setMode(1
		 * (value of lofi)),new A().setMode(2 (value of hifi)));
		 */

		StringBuilder sb = new StringBuilder("");
		//sb.append("new ").append(" $")
		//		.append(this.type().toString().toUpperCase()/* className */)
		//		.append(Names.MPTN_CLASS_DECL_POSTFIX)
		//		.append("("/* begin of args */);
		sb.append("new ").append(this.typeNode.name()).append("[] { ");
		w.write(sb.toString());
		sb.setLength(0);

		// print args
		for (int i = 0; i < list.size(); i++) {
			MPatternElement element = list.get(i);

			//this.print(element.getExpr(), w, pp);
			sb.append(element.getExpr()).append(", ");
			//sb.append(".").append(Names.SET_MODE_METHOD_NAME).append("(")
			//		.append(Names.MODE_DECL_CLASS_NAME).append(".")
			//		.append(element.getLabel()).append(")");

			// insert "," only when it is not the last parameter
			//sb.append(i == list.size() - 1 ? "" : ",");
			w.write(sb.toString());
			sb.setLength(0);
		}

		sb.append("}"/* end of args */);//.append(";");
		w.write(sb.toString());
	}

	@Override
	public Node copy(NodeFactory nf) {
		return ((EcoNodeFactory) nf).MPattern_Init(this.position, this.typeNode, this.list);
	}
}
