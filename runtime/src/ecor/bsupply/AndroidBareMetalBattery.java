package ecor.bsupply;

import ecor.util.AndroidApp;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

public class AndroidBareMetalBattery implements BareMetalBattery {
  private Intent batteryStatus;

	public AndroidBareMetalBattery() {
    IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED); 
    this.batteryStatus = AndroidApp.context.registerReceiver(null, intentFilter);
	}
	
	public int getRemainingCapacity() {
    int level = this.batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
    int scale = this.batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

		return level;
	}

}
