package et.ast;

import java.util.List;

import polyglot.ast.Expr;
import polyglot.ast.Expr_c;
import polyglot.ast.Node;
import polyglot.ast.Term;
import polyglot.ext.jl5.types.JL5ParsedClassType_c;
import polyglot.types.SemanticException;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.visit.CFGBuilder;
import polyglot.visit.NodeVisitor;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.TypeChecker;
import cs.types.CSNonGenericType;
import et.types.PatternType;
import et.types.etTypeSystem_c;

public class Select_c extends Expr_c {

	// protected Type type;

	protected Expr e;

	public Select_c(Position pos, Expr e) {
		super(pos);
		this.e = e;
		type = ((PatternType) e.type()).base();
	}

	public String toString() {
		return "select " + e.toString();
	}

	public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
		printSubExpr(this.e, w, tr);
		w.write("[tools.CalibratorStack.getMode($UTILMODES.$MAX)]");
	}

	@Override
	public List acceptCFG(CFGBuilder v, List succs) {
		v.visitCFG(e, this, EXIT);
		return succs;
	};

	/**
	 * Return the first direct subterm performed when evaluating this term. If
	 * this term has no subterms, this should return null.
	 * 
	 * This method is similar to the deprecated entry(), but it should *not*
	 * recursively drill down to the innermost subterm. The direct child visited
	 * first in this term's dataflow should be returned.
	 */
	@Override
	public Term firstChild() {
		return (Term) e;
	};

	public Node visitChildren(NodeVisitor v) {
		Expr node = (Expr) visitChild(this.e, v);

		return reconstruct(node);
	}

	protected Expr reconstruct(Expr node) {
		if (node != this.e) {
			Select_c n = (Select_c) copy();
			n.e = node;
			return n;
		}
		return this;
	}

	public Expr getExpr() {
		return e;
	}
}
