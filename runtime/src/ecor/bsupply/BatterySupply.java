package ecor.bsupply;

import ecor.util.OsUtil;

public class BatterySupply {
	private static BatterySupply sharedSupply = null;
	
	public static BatterySupply sharedSupply() {
		if (sharedSupply == null) {
			sharedSupply = new BatterySupply();
		}
		return sharedSupply;
	}
	
	private BareMetalBattery bareMetalBattery;
	
	protected BatterySupply() {
		// Load specific OS version for BareMetalBattery
		switch(OsUtil.getOsType()) {
			case WINDOWS:
				bareMetalBattery = new WindowsBareMetalBattery();
			case MACOS:
			case LINUX:
				bareMetalBattery = new UnixBareMetalBattery();
				break;
			case ANDROID:
				bareMetalBattery = new AndroidBareMetalBattery();
				break;
			case NONE:
				System.err.println("Encountered unsupported operating system. Exiting.");
				System.exit(1);
		}

	}
	
	public int getRemainingCapacity() {
		return bareMetalBattery.getRemainingCapacity();
	}
	
}
