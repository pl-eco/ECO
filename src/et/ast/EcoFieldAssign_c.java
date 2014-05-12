package et.ast;

import cs.graph.CSGraph;
import cs.types.CSObjectType;
import cs.types.csTypeSystem_c;
import et.types.PatternType;
import et.visit.MarkPass;
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
		
		for (Field demand : MarkPass.demandFields) {
			String demandAlias = ((CSObjectType) demand.target().type()).getAliasName();
			System.out.println("field: " + field + " | demand: " + demand);
			if (graph.checkAlias(fieldAlias, demandAlias)) {
				//System.out.println("match!");
				
			} else {
				calibrate = false;
			}
		}
		
		if (calibrate) {
			w.write("tools.CalibratorStack.stack.peek().calibrate(");
		}
		super.prettyPrint(w, tr);
		if (calibrate) {
			w.write(")");
		}
	}
	
	@Override
	public Node typeCheck(TypeChecker tc) throws SemanticException {
		if (!(left.type() instanceof PatternType) && right.type() instanceof PatternType) {
			Expr newright = (Expr) ((etNodeFactory_c) tc.nodeFactory()).Select(right);
			return reconstruct(left, newright).typeCheck(tc);
		} else {
			return super.typeCheck(tc);
		}
	}
}
