package et.ast;

import java.util.List;

import cs.types.CSNonGenericType;
import et.types.PatternType;
import et.types.etTypeSystem_c;
import et.util.Names;
import polyglot.ast.Expr;
import polyglot.ast.Expr_c;
import polyglot.ast.Node;
import polyglot.ast.Term;
import polyglot.ast.TypeNode;
import polyglot.ext.jl5.types.JL5ParsedClassType_c;
import polyglot.types.SemanticException;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.visit.CFGBuilder;
import polyglot.visit.NodeVisitor;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.TypeChecker;

public class MPatternApp extends Expr_c {
	private String mode;
	private Expr pattern;

	public MPatternApp(Position pos, String modeV, Expr pattern) {
		super(pos);

		this.mode = modeV;
		this.pattern = pattern;
	}

	public Expr getExpr() {
		return pattern;
	}

	@Override
	public String toString() {
		return mode.toString() + "|>" + pattern.toString();
	}

	public static String printMptnApp(String mode) {
		StringBuilder sb = new StringBuilder("");
		sb.append(".").append(Names.MPTN_GET_OBJECT_METHOD_NAME);
		sb.append("(");
		sb.append(mode);
		sb.append(")");
		return sb.toString();
	}

	public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
		// m |> pattern --> pattern.getObject(m);
		printSubExpr(this.pattern, w, tr);
		w.write("[$UTILMODES." + mode + "]");
		
		//w.write(printMptnApp(mode));
	}

	@Override
	public Term firstChild() {
		return pattern;
	}

	@Override
	public <T> List<T> acceptCFG(CFGBuilder<?> v, List<T> succs) {
		v.visitCFG(pattern, this, EXIT);
		return succs;
	}

	public Node typeCheck(TypeChecker tc) throws SemanticException {
		//CSNonGenericType eType = ((etTypeSystem_c) tc.typeSystem())
		//		.createDefaultObjectType(((CSNonGenericType) pattern.type())
		//				.getType());
		if (!(pattern.type() instanceof PatternType)) {
			throw new SemanticException("not pattern type");
		}
		return type(((PatternType) pattern.type()).base());
		//return type(eType);
	}

	public Node visitChildren(NodeVisitor v) {
		Expr node = (Expr) visitChild(this.pattern, v);
		return reconstruct(node);
	}

	protected Expr reconstruct(Expr node) {
		if (node != this.pattern) {
			MPatternApp n = (MPatternApp) copy();
			n.pattern = node;
			return n;
		}
		return this;
	}

	@Override
	public boolean isDisambiguated() {
		return super.isDisambiguated() && pattern.isDisambiguated();
	}

	public Expr getPattern() {
		return pattern;
	}

}
