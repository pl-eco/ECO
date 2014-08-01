package eco.linearsolver;

import cs.graph.GraphElement;
import cs.linearsolver.ValueHolder;

public class EcoValueHolder extends ValueHolder<String, String> {
	@Override
	public String printValue(GraphElement elmt) {
		return "";
	}

	@Override
	protected boolean compatible(String objModifier, String classModifier) {
		return objModifier.equals(classModifier);
	}
}
