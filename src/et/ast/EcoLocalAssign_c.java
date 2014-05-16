package et.ast;

import et.types.PatternType;
import polyglot.ast.Expr;
import polyglot.ast.Local;
import polyglot.ast.LocalAssign_c;
import polyglot.ast.Node;
import polyglot.ast.Receiver;
import polyglot.types.SemanticException;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.TypeChecker;

public class EcoLocalAssign_c extends LocalAssign_c {

	boolean calibrate = false;
	
	public EcoLocalAssign_c(Position pos, Local left, Operator op, Expr right) {
		super(pos, left, op, right);
	}

	public void markCalibrate() {
		calibrate = true;
	}

	@Override
	public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
		if (calibrate) {
			w.write("tools.CalibratorStack.calibrate(");
		}
		super.prettyPrint(w, tr);
		if (calibrate) {
			w.write(")");
		}
	}
	
	@Override
	public Node typeCheck(TypeChecker tc) throws SemanticException {
		if (!(left.type() instanceof PatternType) && right.type() instanceof PatternType) {
			Expr newRight = (Expr) ((etNodeFactory_c) tc.nodeFactory()).Select(right);
			return reconstruct(left, newRight).typeCheck(tc);
		} else {
			return super.typeCheck(tc);
		}
	}
}
