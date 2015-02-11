package ecor.tsupply;

import ecor.util.OsUtil;

public class TemperatureSupply {
	private static TemperatureSupply sharedSupply = null;
	
	public static TemperatureSupply sharedSupply() {
		if (sharedSupply == null) {
			sharedSupply = new TemperatureSupply();
		}
		return sharedSupply;
	}
	
	private BareMetalTemperature bareMetalTemperature;
	
	protected TemperatureSupply() {
		// Load specific OS version for BareMetalBattery
		switch(OsUtil.getOsType()) {
			case WINDOWS:
				bareMetalTemperature = new WindowsBareMetalTemperature();
			case MACOS:
			case LINUX:
				bareMetalTemperature = new UnixBareMetalTemperature();
				break;
			case ANDROID:
				bareMetalTemperature = new AndroidBareMetalTemperature();
				break;
			case NONE:
				System.err.println("Encountered unsupported operating system. Exiting.");
				System.exit(1);
		}

	}
	
	public int getCurrentTemperature() {
		return bareMetalTemperature.getCurrentTemperature();
	}
	
}
