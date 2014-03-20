package et.ast.mswitch;

import cs.types.CSContext_c;
import cs.util.EasyDebugger;
import et.types.ETTYPE;
import et.types.EnergyFlags;
import polyglot.types.Context_c;
import polyglot.types.TypeSystem;

public class ECMContext_c extends CSContext_c {
	private String mode = null;
	private String modeFamily = null;
	private String phase = null;
	private String phaseFamily = null;

	public ECMContext_c(TypeSystem ts) {
		super(ts);
	}

	// right now, this method is only for the purpose of class decl
	protected Context_c push() {
		ECMContext_c c = (ECMContext_c) super.push();

		c.mode = mode;
		c.modeFamily = modeFamily;
		c.phase = phase;
		c.phaseFamily = phase;

		return c;
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

	public void setValues(EnergyFlags eFlags) {
		this.mode = eFlags.getMode();
		this.modeFamily = eFlags.getModeFamily();
		this.phase = eFlags.getPhase();
		this.phaseFamily = eFlags.getPhaseFamily();
	}
}
