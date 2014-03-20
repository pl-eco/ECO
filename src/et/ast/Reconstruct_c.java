package et.ast;

import java.util.List;

import cs.types.CSNonGenericType;
import et.types.etTypeSystem_c;
import et.util.Names;
import polyglot.ast.Expr;
import polyglot.ast.Expr_c;
import polyglot.ast.Node;
import polyglot.ast.Term;
import polyglot.ext.jl5.types.JL5ParsedClassType_c;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.visit.CFGBuilder;
import polyglot.visit.NodeVisitor;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.TypeChecker;

// reconstruct e as modeExpr 
public class Reconstruct_c extends Expr_c {
	private Expr e;
	private Expr modeExpr;

	public Reconstruct_c(Position pos, Expr e, Expr modeExpr) {
		super(pos);
		this.e = e;
		this.modeExpr = modeExpr;
	}

	public Expr getExpr() {
		return e;
	}

	@Override
	public String toString() {
		return "reconstruct " + e.toString() + " with " + modeExpr.toString();
	}

	@Override
	public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
		// reconstruct obj as m --> obj.$reconstruct().getObject(m);
		printSubExpr(this.e, w, tr);

		StringBuilder sb = new StringBuilder("");
		sb.append(".").append(Names.RECONSTRUCT_METHOD_NAME).append("()");
		sb.append(".").append(Names.MPTN_GET_OBJECT_METHOD_NAME);
		sb.append("(");
		w.write(sb.toString());
		sb.setLength(0);

		printSubExpr(this.modeExpr, w, tr);

		w.write(")");
	}

	public Node typeCheck(TypeChecker tc) {
		// to implement
		CSNonGenericType eType = ((etTypeSystem_c) tc.typeSystem())
				.createDefaultObjectType((JL5ParsedClassType_c) ((CSNonGenericType) e
						.type()).getType());

		return type(eType); // reconstruct e with modeExpr
	}

	public Node visitChildren(NodeVisitor v) {
		Expr nodeE = (Expr) visitChild(this.e, v);
		Expr nodeModeE = (Expr) visitChild(this.modeExpr, v);
		return reconstruct(nodeE, nodeModeE);
	}

	protected Expr reconstruct(Expr nodeE, Expr nodeMode) {
		if (nodeE != this.e || nodeMode != this.modeExpr) {
			Reconstruct_c n = (Reconstruct_c) copy();
			n.e = nodeE;
			n.modeExpr = nodeMode;
			return n;
		}
		return this;
	}

	@Override
	public boolean isDisambiguated() {
		return super.isDisambiguated() && e.isDisambiguated()
				&& modeExpr.isDisambiguated();
	}

	@Override
	public Term firstChild() {
		return e;
	}

	@Override
	public <T> List<T> acceptCFG(CFGBuilder<?> v, List<T> succs) {
		v.visitCFG(e, this, EXIT);
		v.visitCFG(modeExpr, this, EXIT);
		return succs;
	}
}
