package eco.ast;

import eco.types.PatternType;
import eco.types.EcoTypeSystem;

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

public class MPattern_c extends TypeNode_c implements MPattern {
	private TypeNode inner;

	public MPattern_c(Position pos, TypeNode node) {
		super(pos);
		this.inner = node;
	}

	public TypeNode getInnerNode() {
		return inner;
	}

	protected MPattern_c reconstruct(TypeNode node) {
    MPattern_c n = (MPattern_c) copy();
    n.inner = node;
    return n;
	}

	@Override
	public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
		w.write(inner.name() + "[]");
	}

  @Override
	public Node buildTypes(polyglot.visit.TypeBuilder tb)
			throws SemanticException {
		TypeNode node = (TypeNode) super.buildTypes(tb);
		//Type t = ((EcoTypeSystem) tb.typeSystem()).createPatternType(
		//		tb.typeSystem().unknownType(node.position()));
		Type t = ((EcoTypeSystem) tb.typeSystem()).createPatternType(this.inner.type());
		return node.type(t);
	}

  @Override
	public Node visitChildren(NodeVisitor v) {
		TypeNode node = (TypeNode) visitChild(this.inner, v);
		return reconstruct(node);
	}

  @Override
	public Node disambiguate(AmbiguityRemover ar) throws SemanticException {
		((PatternType) this.type()).base(this.inner.type());
		return this;
	}

	@Override
	public boolean isDisambiguated() {
		return !(this instanceof Ambiguous) && type != null
				&& inner.type().isCanonical();
	}

}
