package et.ast;

import cs.util.EasyDebugger;
import et.types.etTypeSystem_c;
import et.util.Names;
import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import polyglot.ast.StringLit_c;
import polyglot.types.SemanticException;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.TypeChecker;

public class ModeLit_c extends StringLit_c {
	public ModeLit_c(Position pos, String value) {
		super(pos, value);
	}

	public Node typeCheck(TypeChecker tc) throws SemanticException {
		return type(((etTypeSystem_c) tc.typeSystem()).modeVT());
	}

	public Node copy(NodeFactory nf) {
		return ((etNodeFactory) nf).ModeLit(this.position, this.value);
	}

	public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
		w.write(this.toString());
	}

	public String toString() {
		return Names.MODE_DECL_CLASS_NAME + "." + value.substring(1);
	}

	public String value() {
		if (!this.value.startsWith("#"))
			EasyDebugger
					.exit("Error, the modeVT must start with \"#\". Current modeVT: "
							+ this.value);

		return value.substring(1, value.length());
	}
}
