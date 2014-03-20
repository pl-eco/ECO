/*
 * This file is part of the Polyglot extensible compiler framework.
 *
 * Copyright (c) 2000-2006 Polyglot project group, Cornell University
 * 
 */

package et.ast;

import java.util.List;

import cs.types.CSNonGenericType;
import et.types.etTypeSystem_c;
import polyglot.types.*;
import polyglot.visit.*;
import polyglot.util.*;
import polyglot.ast.*;
import polyglot.ext.jl5.types.JL5ParsedClassType_c;

/**
 * An <code>Expr</code> represents any Java expression. All expressions must be
 * subtypes of Expr.
 */
public class Adapt_c extends Expr_c {
	// protected Type type;

	protected Expr e;
	protected String phaseTo = null;

	protected Expr index;

	public Adapt_c(Position pos, String s, Expr e) {
		super(pos);
		this.e = e;
		this.phaseTo = s;
	}

	public String toString() {
		return "adapt " + e.toString();
	}

	public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
		printSubExpr(this.e, w, tr);
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

	// Add by steve 6/16/11
	public Node typeCheck(TypeChecker tc) {
		/*
		 * the typeCheck method only create a new ECMObject for Adapt
		 */
		CSNonGenericType eType = ((etTypeSystem_c) tc.typeSystem())
				.createDefaultObjectType((JL5ParsedClassType_c) ((CSNonGenericType) e
						.type()).getType());

		return type(eType); // (adapt)o
	}

	public Node visitChildren(NodeVisitor v) {
		Expr node = (Expr) visitChild(this.e, v);

		return reconstruct(node);
	}

	protected Expr reconstruct(Expr node) {
		if (node != this.e) {
			Adapt_c n = (Adapt_c) copy();
			n.e = node;
			return n;
		}
		return this;
	}

	@Override
	public boolean isDisambiguated() {
		return super.isDisambiguated() && e.isDisambiguated();
	}

	public Expr getExpr() {
		return e;
	}
}
