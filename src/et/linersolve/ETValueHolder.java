package et.linersolve;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import polyglot.util.Position;
import cs.data.id.ID;
import cs.data.id.InstID;
import cs.graph.CSGraph;
import cs.graph.GraphElement;
import cs.linearsolver.ValueHolder;
import cs.util.CSUtil;
import cs.util.EasyDebugger;
import et.ETValue;
import et.types.ETTYPE;
import et.types.EnergyFlags;

public class ETValueHolder extends ValueHolder<ETTYPE, String> {
	/*
	 * this method look up both the pre-set value and linear solvered result and
	 * return the value. The value should also be converted to the string
	 * representative
	 * 
	 * Note: because there can be "adapt" applied in flows, so trace back to the
	 * instance of the given ID is a must
	 */
	public String getFinalResult(String alias, ETTYPE type, CSGraph graph) {
		List<InstID> instList = graph.getInstance(alias);

		assert false;
		assert instList.size() > 0 : "The given id doesn't have any instance bind to it. The result cannot be got.";

		int value = 0;
		// this.getResultFromRefreshedName(instList.get(0).uniqueID(),
		// type);
		//
		// for (int i = 1; i < instList.size(); i++) {
		// int tmp = getResultFromRefreshedName(instList.get(i).uniqueID(),
		// type);
		// assert value == tmp :
		// "The given id has multiply value from its instances. The program will be rejected.";
		// }

		return convertIntToString(value, type);
	}

	private String convertIntToString(int value, ETTYPE type) {
		// TODO
		assert false;
		return null;
	}

	public ETValue createETValue(GraphElement elmt) {
		String var = elmt.uniqueID();
		
		String mode = getAliasTypeValue(elmt.uniqueID(), ETTYPE.MODE);
		if (mode == null)
			mode = var + ETTYPE.MODE;

		String modeFamily = getAliasTypeValue(elmt.uniqueID(), ETTYPE.MODE_FAMILY);
		if (modeFamily == null)
			modeFamily = var + ETTYPE.MODE_FAMILY;

		String phase = getAliasTypeValue(elmt.uniqueID(), ETTYPE.PHASE);
		if (phase == null)
			phase = var + ETTYPE.PHASE;

		String phaseFamily = getAliasTypeValue(elmt.uniqueID(), ETTYPE.PHASE_FAMILY);
		//String phaseFamily = getPreAliasModifiers(elmt.uniqueID(), ETTYPE.PHASE_FAMILY);
		if (phaseFamily == null)
			phaseFamily = var + ETTYPE.PHASE_FAMILY;

		return new ETValue(var, mode, modeFamily, phase, phaseFamily);
	}

	@Override
	// print the type info by the given aliasName
	public String printValue(GraphElement elmt) {
		StringBuilder sb = new StringBuilder("");

		String value = null;
		for (ETTYPE type : ETTYPE.values()) {
			if ((value = getAliasTypeValue(elmt.uniqueID(), type)) != null) {
			//if ((value = getPreAliasModifiers(elmt.uniqueID(), type)) != null) {
				sb.append(type).append(": ").append(value).append(" ");
			}
		}

		return sb.toString();
	}

	@Override
	protected boolean compatible(String objModifier, String classModifier) {
		return objModifier.equals(classModifier);
	}
}
