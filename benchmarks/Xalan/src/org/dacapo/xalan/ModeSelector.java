package org.dacapo.xalan;

public class ModeSelector {
	private static final int tempMin = 48;// 44;
	private static final int tempMax = 50;// 50;
	private static final int tempCrit = 55;// 53;

	private static final int hiMode = 0;
	private static final int MedMode = 4;
	private static final int loMode = 10;

	public static int interval = hiMode;

	public static int getInterval(int temp) {

		if (temp <= tempMin) {
			interval = hiMode;
		} else if (temp >= tempCrit) {
			interval = loMode;
		} else {
			interval = MedMode;
		}
		return interval;
	}
}
