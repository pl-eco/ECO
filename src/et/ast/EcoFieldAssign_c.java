package eco.ast;

import cs.graph.CSGraph;
import cs.types.CSObjectType;
import cs.types.csTypeSystem_c;
import eco.types.PatternType;
import eco.visit.MarkPass;
import polyglot.ast.Expr;
import polyglot.ast.Field;
import polyglot.ast.FieldAssign_c;
import polyglot.ast.Local;
import polyglot.ast.LocalAssign_c;
import polyglot.ast.Node;
import polyglot.ast.Receiver;
import polyglot.types.SemanticException;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.TypeChecker;

public class EcoFieldAssign_c extends FieldAssign_c {
	
	boolean calibrate = false;

	public EcoFieldAssign_c(Position pos, Field left, Operator op, Expr right) {
		super(pos, left, op, right);
	}

	public void markCalibrate() {
		calibrate = true;
	}

	@Override
	public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
		// check alias against demandFields, if match calibrate
		CSGraph graph = ((csTypeSystem_c) this.type().typeSystem()).getGraph();

		Field field = (Field) this.left();
		String fieldAlias = ((CSObjectType) field.target().type()).getAliasName();
		
		if (calibrate) {
			calibrate = false;
			for (Field demand : MarkPass.demandFields) {
				if (!demand.name().equals(field.name())) continue;
				String demandAlias = ((CSObjectType) demand.target().type()).getAliasName();
				if (graph.checkAlias(fieldAlias, demandAlias)) {
					calibrate = true;
					break;
				}
			}
		}
		
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
			Expr newRight = (Expr) ((EcoNodeFactory_c) tc.nodeFactory()).Select(right);
			return reconstruct(left, newRight).typeCheck(tc);
		} else {
			return super.typeCheck(tc);
		}
	}
}
