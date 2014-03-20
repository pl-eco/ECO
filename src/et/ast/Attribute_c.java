package et.ast;

import java.util.List;
import java.util.Stack;

import cs.types.CSNonGenericType;
import cs.types.csTypeSystem_c;
import et.types.PatternType;
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

public class Attribute_c extends Expr_c {
	private Expr obj;

	public Attribute_c(Position pos, Expr obj) {
		super(pos);
		this.obj = obj;

	}

	public Expr getExpr() {
		return obj;
	}

	public Node typeCheck(TypeChecker tc) {
		// the type of the attribute expression will be obj
		CSNonGenericType eType = ((etTypeSystem_c) tc.typeSystem())
				.createDefaultObjectType((JL5ParsedClassType_c) ((CSNonGenericType) obj
						.type()).getType());

		return type(eType); // attribute obj

		// return type(((etTypeSystem_c) tc.typeSystem()).modeVT());
	}

	public Node visitChildren(NodeVisitor v) {
		Expr obj = (Expr) visitChild(this.obj, v);
		return reconstruct(obj);
	}

	protected Expr reconstruct(Expr obj) {
		if (obj != this.obj) {
			Attribute_c n = (Attribute_c) copy();
			n.obj = obj;
			return n;
		}
		return this;
	}

	@Override
	public boolean isDisambiguated() {
		return super.isDisambiguated() && obj.isDisambiguated();
	}

	@Override
	public Term firstChild() {
		return obj;
	}

	@Override
	public <T> List<T> acceptCFG(CFGBuilder<?> v, List<T> succs) {
		v.visitCFG(obj, this, EXIT);
		return succs;
	}

	/*
	 * snapshot o --> (but also return o) map.put(o, o.c()) A global method
	 * should be added for the required operation.
	 * 
	 * T snapshot(T o, ModeV mode){ map.put(o, mode); return o; }
	 */
	@Override
	public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
		printSubExpr(this.obj, w, tr);
		w.write("." + Names.ATTRIBUTER_METHOD_NAME + "()");
	}

	@Override
	public String toString() {
		return "attribute " + obj.toString();
	}

}
