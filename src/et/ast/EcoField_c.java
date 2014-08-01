package eco.ast;

import polyglot.ast.Field_c;
import polyglot.ast.Id;
import polyglot.ast.Node;
import polyglot.ast.Receiver;
import polyglot.ast.Special;
import polyglot.ext.jl5.ast.JL5Field_c;
import polyglot.types.SemanticException;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.TypeChecker;

public class EcoField_c extends JL5Field_c {
	private String enclosingClassName;
	/*
	 * the short hand form of mpatter usage can only be applied on: 1. method
	 * invocation 2. field access
	 */
	private boolean isShortHandForm = false;

	public EcoField_c(Position pos, Receiver target, Id name) {
		super(pos, target, name);
	}

	public void setShortHand() {
		isShortHandForm = true;
	}

	@Override
	public Node typeCheck(TypeChecker tc) throws SemanticException {
		Node ret = super.typeCheck(tc);

		// current class can be null for field decl in annotations
		if (tc.context().currentClass() != null) {
			enclosingClassName = tc.context().currentClass().fullName();
		}

		return ret;
	}

	@Override
	public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
		
		super.prettyPrint(w, tr);
		/*
		if (isShortHandForm) {
			String mptApp = MPatternApp.printMptnApp(ECMClassDecl_c
					.getModeFieldNameOfClass(enclosingClassName));
			w.write(mptApp);
		}
		*/
	}

}
