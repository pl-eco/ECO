package et.linersolve;

import java.util.List;

import ilog.concert.IloConstraint;
import ilog.concert.IloException;
import ilog.concert.IloNumExpr;
import ilog.concert.IloRange;
import cs.data.constraint.CallConstraint;
import cs.graph.GraphElement;
import cs.linearsolver.CPlexWrapper;
import cs.linearsolver.ValueHolder;
import cs.util.EasyDebugger;
import cs.visit.AnalysisContext;
import et.ETValue;
import et.ast.ModesDecl;
import et.ast.PhasesDecl;
import et.ast.mswitch.ECMContext_c;
import et.data.AdaptID;
import et.types.ETTYPE;

public class ETCplexSolver extends CPlexWrapper {

	public ETCplexSolver(ValueHolder<ETTYPE, String> valueHolder) {
		super(valueHolder);
	}

	@Override
	public void addFlow(GraphElement from, GraphElement to) {
		ETValue fromValue = ((ETValueHolder) valueHolder).createETValue(from);
		ETValue toValue = ((ETValueHolder) valueHolder).createETValue(to);

		// phaseL >= phaseR
		this.addEquation(toValue.getPhase(), Operator.GE, fromValue.getPhase());

		// modeL >= modeR
		this.addEquation(toValue.getMode(), Operator.GE, fromValue.getMode());
	}

	@Override
	public void addCall(CallConstraint call) {
		// context'phase = target's phase
		// context'mode >= target's mode
		ETValue targetValue = ((ETValueHolder) valueHolder)
				.createETValue((GraphElement) call.target());

		// context'phase = target's phase
		String contextPh = getValueFromContext(
				(ECMContext_c) call.getStaticContext(),
				call.getAnalysisContext(), ETTYPE.PHASE);

		this.addEquation(contextPh, Operator.EQ, targetValue.getPhase());

		// context'mode >= target's mode
		String contextMo = getValueFromContext(
				(ECMContext_c) call.getStaticContext(),
				call.getAnalysisContext(), ETTYPE.MODE);
		this.addEquation(contextMo, Operator.GE, targetValue.getMode());
	}

	private String getValueFromContext(ECMContext_c staticContext,
			AnalysisContext context, ETTYPE type) {
		assert context != null;

		String ret = null;

		/*
		 * static context comes with higher priority
		 * 
		 * It can be set either in class level or in mswitch. Otherwise, the
		 * energy value of the context will be empty
		 */
		if (staticContext != null)
			ret = staticContext.getValue(type);
		// if value is null
		if (ret == null) {
			// get value from analysisContext
			ETValue value = ((ETValueHolder) valueHolder)
					.createETValue((GraphElement) context.getInstID());
			ret = value.getValue(type);
		}
		return ret;
	}

	public void addAdapt(AdaptID adaptor) {
		ETValue adaptorET = ((ETValueHolder) valueHolder)
				.createETValue(adaptor);
		ETValue adapteeET = ((ETValueHolder) valueHolder)
				.createETValue((GraphElement) adaptor.getAdaptee());

		// adaptor's mode == adaptee's mode;
		this.addEquation(adaptorET.getMode(), Operator.EQ, adapteeET.getMode());

		// if phase is explicit
		// adaptor's phase == explicit phase
		if (adaptor.getToPhase() != null) {
			addEquation(adaptorET.getPhase(), Operator.EQ, adaptor.getToPhase());
		}
	}

	/*
	 * This method add declared phase and mode value to the constraint set
	 */
	@Override
	public void addExtraEq() {
		// add partial order mode and phase values
		addOrderedValue(ModesDecl.getModesOrder());
		addOrderedValue(PhasesDecl.getPhasesOrder());
	}

	private void addOrderedValue(List<String> orderList) {
		try {
			for (int i = 0; i < orderList.size(); i++) {
				if (i + 1 < orderList.size()) {
					String low = orderList.get(i);
					String high = orderList.get(i + 1);

					// low + 1 <= high
					IloNumExpr ls = cplex.sum(this.getExpr(low), 1);
					equations
							.add((IloRange) cplex.addLe(ls, this.getExpr(high)));
				}
			}
		} catch (IloException e) {
			e.printStackTrace();
		}
	}

}
