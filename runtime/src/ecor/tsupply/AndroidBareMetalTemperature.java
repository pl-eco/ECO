package ecor.tsupply;

import ecor.util.AndroidApp;

public class AndroidBareMetalTemperature implements BareMetalTemperature {

	public AndroidBareMetalTemperature() {
	}
	
	public int getCurrentTemperature() {
    return AndroidApp.temperature;
	}

}
