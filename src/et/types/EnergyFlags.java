package et.types;

import java.io.Serializable;
import java.util.*;

import cs.linearsolver.ValueHolder;

public class EnergyFlags implements Serializable {
	private Map<ETTYPE, String> flags;

	public static final String ANONYMOUS = "?";

	public static EnergyFlags createEType(ETTYPE eType, String value) {
		return new EnergyFlags(eType, value);
	}

	public static EnergyFlags createEType(ETTYPE eType1, String value1,
			ETTYPE eType2, String value2) {
		EnergyFlags ef1 = EnergyFlags.createEType(eType1, value1);
		EnergyFlags ef2 = EnergyFlags.createEType(eType2, value2);
		assert !ef1.intersects(ef2);
		return ef1.set(ef2);
	}

	public Map<ETTYPE, String> getFlags() {
		return flags;
	}

	public static EnergyFlags createDefault() {
		return new EnergyFlags();
	}

	private EnergyFlags() {
		flags = new TreeMap<ETTYPE, String>();
	}

	private EnergyFlags(ETTYPE eType, String value) {
		this();
		flags.put(eType, value);
	}

	public EnergyFlags set(EnergyFlags other) {
		EnergyFlags f = new EnergyFlags();
		f.flags.putAll(this.flags);
		f.flags.putAll(other.flags);
		return f;
	}

	/*
	 * check if "this" and "other" have the same ETYPE set
	 */
	public boolean intersects(EnergyFlags other) {
		for (ETTYPE type : other.flags.keySet())
			if (this.flags.containsKey(type))
				return true;
		return false;
	}

	private String getValue(ETTYPE type) {
		if (flags.containsKey(type))
			return flags.get(type);
		return null;
	}

	public String getMode() {
		return getValue(ETTYPE.MODE);
	}

	public String getPhase() {
		return getValue(ETTYPE.PHASE);
	}

	public String getModeFamily() {
		return getValue(ETTYPE.MODE_FAMILY);
	}

	public String getPhaseFamily() {
		return getValue(ETTYPE.PHASE_FAMILY);
	}
}
