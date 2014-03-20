package et.types;

import cs.linearsolver.CSTYPE;

public enum ETTYPE implements CSTYPE {
	MODE("#mo"), PHASE("#ph"), MODE_FAMILY("#mf"), PHASE_FAMILY("#pf");

	private String value;

	private ETTYPE(String s) {
		value = s;
	}

	public String toString() {
		return value;
	}
}
