package et.ast;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import et.util.Names;
import polyglot.ast.Block;
import polyglot.ast.Formal;
import polyglot.ast.Id;
import polyglot.ast.Node;
import polyglot.ast.TypeNode;
import polyglot.ext.jl5.ast.AnnotationElem;
import polyglot.ext.jl5.ast.JL5MethodDecl_c;
import polyglot.ext.jl5.ast.ParamTypeNode;
import polyglot.ext.jl5.types.JL5Flags;
import polyglot.ext.jl5.visit.JL5Translator;
import polyglot.types.Flags;
import polyglot.types.SemanticException;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.TypeChecker;

public class ReconstructDecl extends JL5MethodDecl_c {
	public ReconstructDecl(Position pos, TypeNode returnType, Id name) {
		super(pos, Flags.PUBLIC, new LinkedList<AnnotationElem>(), returnType,
				name, new LinkedList<Formal>(), new LinkedList<TypeNode>(),
				null);
	}

	@Override
	/*
	 * Override from JL5MethodDecl_c. The only modification is to make the
	 * return type as the pattern class of the container class
	 */
	public void prettyPrintHeader(Flags flags, CodeWriter w, PrettyPrinter tr) {
		w.begin(0);

		w.begin(0);
		for (AnnotationElem ae : annotations) {
			ae.prettyPrint(w, tr);
			w.newline();
		}
		w.end();

		w.write(JL5Flags.clearVarArgs(flags).translate());

		// type params
		boolean printTypeVars = true;
		if (tr instanceof JL5Translator) {
			JL5Translator jl5tr = (JL5Translator) tr;
			printTypeVars = !jl5tr.removeJava5isms();
		}
		if (printTypeVars && !this.typeParams().isEmpty()) {
			w.write("<");
			for (Iterator<ParamTypeNode> iter = this.typeParams().iterator(); iter
					.hasNext();) {
				ParamTypeNode ptn = iter.next();
				ptn.prettyPrint(w, tr);
				if (iter.hasNext()) {
					w.write(",");
					w.allowBreak(0, " ");
				}
			}
			w.write("> ");
		}

		// modification
		w.write("$" + this.mi.container().toString().toUpperCase()
				+ Names.MPTN_CLASS_DECL_POSTFIX);

		w.write(" " + name + "(");
		w.begin(0);

		for (Iterator<Formal> i = formals.iterator(); i.hasNext();) {
			Formal f = i.next();
			print(f, w, tr);

			if (i.hasNext()) {
				w.write(",");
				w.allowBreak(0, " ");
			}
		}

		w.end();
		w.write(")");

		if (!throwTypes().isEmpty()) {
			w.allowBreak(6);
			w.write("throws ");

			for (Iterator<TypeNode> i = throwTypes().iterator(); i.hasNext();) {
				TypeNode tn = i.next();
				print(tn, w, tr);

				if (i.hasNext()) {
					w.write(",");
					w.allowBreak(4, " ");
				}
			}
		}

		w.end();
	}
}
