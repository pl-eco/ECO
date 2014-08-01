package eco.ast;

/*
 * This is a type node representing the mpattern type
 */

import cs.ast.CSCanonicalTypeNode_c;
import cs.types.CSNonGenericType;
import cs.types.csTypeSystem_c;
import eco.types.PatternType;
import eco.types.EcoTypeSystem_c;
import eco.util.Names;
import polyglot.ast.Ambiguous;
import polyglot.ast.CanonicalTypeNode_c;
import polyglot.ast.Node;
import polyglot.ast.TypeNode;
import polyglot.ast.TypeNode_c;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.ClassType;
import polyglot.types.PrimitiveType;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.visit.AmbiguityRemover;
import polyglot.visit.NodeVisitor;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.TypeChecker;

public class MPattern extends TypeNode_c {
	private TypeNode inner;

	public MPattern(Position pos, TypeNode node) {
		super(pos);
		this.inner = node;
	}

	public TypeNode getInnerNode() {
		return inner;
	}

	protected MPattern reconstruct(TypeNode node) {
		if (node != this.inner) {
			MPattern n = (MPattern) copy();
			n.inner = node;
			return n;
		}
		return this;
	}

	@Override
	public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
		// mpattern<type> --> $%TYPE%_PATTERN
		//w.write(node.type().toString().toUpperCase());
				//+ Names.MPTN_CLASS_DECL_POSTFIX);
		w.write(inner.name() + "[]");
		// if(isShortHandForm)
	}

	public Node buildTypes(polyglot.visit.TypeBuilder tb)
			throws SemanticException {
		TypeNode node = (TypeNode) super.buildTypes(tb);

		//Type type = ((etTypeSystem_c) tb.typeSystem()).createDefaultObjectType(
		//		tb.typeSystem().unknownType(node.position()), "mpattern");
		//		tb.typeSystem().unknownType(node.position()), "default");
		Type t = ((EcoTypeSystem_c) tb.typeSystem()).createPatternType(
				tb.typeSystem().unknownType(node.position()));
		//return node.type(type);
		//return node.type(tb.typeSystem().unknownType(node.position()));
		return node.type(t);
	}

	public Node visitChildren(NodeVisitor v) {
		TypeNode node = (TypeNode) visitChild(this.inner, v);
		return reconstruct(node);
	}

	public Node disambiguate(AmbiguityRemover ar) throws SemanticException {
		((PatternType) this.type()).base(this.inner.type());

		return this;//.type(this.type());
	}

	/*
	@Override
	public Node typeCheck(TypeChecker tc) throws SemanticException {
		return this;
		//MPattern ret = (MPattern) super.typeCheck(tc);
		
		//return type(new PatternType(ret.getInnerNode().type()));
	}
	*/

	@Override
	public boolean isDisambiguated() {
		return !(this instanceof Ambiguous) && type != null
				&& inner.type().isCanonical();
	}
}
