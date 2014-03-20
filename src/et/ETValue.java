package et;

import cs.linearsolver.CSTypeValue;
import cs.util.EasyDebugger;
import et.types.ETTYPE;

public class ETValue extends CSTypeValue {
	// public static String NOT_ASSIGNED = "default";
	public static String DYNAMIC = "dyn";

	private String mode;
	private String modeFamily;
	private String phase;
	private String phaseFamily;

	public ETValue(String var, String mode, String modeFamily, String phase,
			String phaseFamily) {
		super(var);
		this.mode = mode;
		this.modeFamily = modeFamily;
		this.phase = phase;
		this.phaseFamily = phaseFamily;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append(var).append(" ");
		
		String value = null;
		for(ETTYPE type:ETTYPE.values()){
			if((value = getValue(type)) != null)
				sb.append(type).append("(").append(value).append("), ");
		}
		return sb.toString();
	}

	public String getValue(ETTYPE type) {
		switch (type) {
		case MODE:
			return mode;
		case MODE_FAMILY:
			return modeFamily;
		case PHASE:
			return phase;
		case PHASE_FAMILY:
			return phaseFamily;
		default:
			assert false;
			return null;
		}
	}

	public String getMode() {
		return mode;
	}

	public String getPhase() {
		return phase;
	}

	public String getModeFamily() {
		return modeFamily;
	}

	public String getPhaseFamily() {
		return phaseFamily;
	}
}
