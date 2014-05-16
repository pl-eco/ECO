package et.ast;

import et.types.PatternType;
import polyglot.ast.Expr;
import polyglot.ast.Id;
import polyglot.ast.LocalDecl_c;
import polyglot.ast.Node;
import polyglot.ast.TypeNode;
import polyglot.types.Flags;
import polyglot.types.SemanticException;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.TypeChecker;

public class EcoLocalDecl_c extends LocalDecl_c {

	boolean calibrate = false;
	
	public EcoLocalDecl_c(Position pos, Flags flags, TypeNode type, Id name,
			Expr init) {
		super(pos, flags, type, name, init);
	}
	
	public void markCalibrate() {
		calibrate = true;
	}

	@Override
	public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
        boolean printSemi = tr.appendSemicolon(true);
        boolean printType = tr.printType(true);

        if (calibrate)
        	flags = flags.Final();
        w.write(flags.translate());
        if (printType) {
            print(type, w, tr);
            if (calibrate)
            	w.write("[]");
            w.write(" ");
        }
        tr.print(this, name, w);

        if (init != null) {
            w.write(" =");
            if (calibrate) w.write(" {");
            w.allowBreak(2, " ");
            print(init, w, tr);
            if (calibrate) w.write(" }");
        } else if (calibrate) {
        	w.write(" = new ");
            print(type, w, tr);
            w.write("[1]");
        }

        if (printSemi) {
            w.write(";");
        }

        tr.printType(printType);
        tr.appendSemicolon(printSemi);
	}
	
	@Override
	public Node typeCheck(TypeChecker tc) throws SemanticException {
		if (!(type.type() instanceof PatternType) &&
				init != null && init.type() instanceof PatternType) {
			Expr newInit = (Expr) ((etNodeFactory_c) tc.nodeFactory()).Select(init);
			return reconstruct(type, name, newInit).typeCheck(tc);
		} else {
			return super.typeCheck(tc);
		}
	}
}
