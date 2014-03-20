package et.util;

public class utilScale {

	native public static int scale(String freq);

	native public static String[] freqAvailable();

	static {
		System.loadLibrary("scalelib");
	}
}
