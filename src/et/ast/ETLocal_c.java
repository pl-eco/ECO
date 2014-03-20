package et.ast;

import polyglot.ast.Id;
import polyglot.ast.Local_c;
import polyglot.ast.Node;
import polyglot.types.SemanticException;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.TypeChecker;

public class ETLocal_c extends Local_c {
	private String enclosingClassName;
	/*
	 * the short hand form of mpatter usage can only be applied on: 1. method
	 * invocation 2. field access
	 */
	private boolean isShortHandForm = false;

	public ETLocal_c(Position pos, Id name) {
		super(pos, name);
	}

	public void setShortHand() {
		isShortHandForm = true;
	}

	@Override
	public Node typeCheck(TypeChecker tc) throws SemanticException {
		Node ret = super.typeCheck(tc);

		enclosingClassName = tc.context().currentClass().fullName();
		
		return ret;
	}

	@Override
	public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
		super.prettyPrint(w, tr);
		if (isShortHandForm) {
			String mptApp = MPatternApp.printMptnApp(ECMClassDecl_c
					.getModeFieldNameOfClass(enclosingClassName));
			w.write(mptApp);
		}
	}
}
