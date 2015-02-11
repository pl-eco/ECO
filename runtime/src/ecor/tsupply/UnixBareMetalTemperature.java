package ecor.tsupply;

import ecor.tsupply.BareMetalTemperature;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class UnixBareMetalTemperature implements BareMetalTemperature {

  private static final String TEMPERATURE_ZONE0_PATH =
    "/sys/class/thermal/thermal_zone0/temp";

  private static final int TEMPERATURE_ZONE0_CONVERSION = 1000;

  public UnixBareMetalTemperature() {
  }
	
  public int getCurrentTemperature() {
    int currentTemperature = 0;
    try {
      currentTemperature = 
        this.getBareMetalZoneTemperature() / TEMPERATURE_ZONE0_CONVERSION;
    } catch (IOException e) {
      // TODO: How to handle?
    }

    return currentTemperature;
  }

  private int getBareMetalZoneTemperature() throws IOException {
    FileReader fileReader = new FileReader(TEMPERATURE_ZONE0_PATH);
    BufferedReader bufferedReader = new BufferedReader(fileReader);

    int currentTemperature = 
      Integer.parseInt(bufferedReader.readLine());

    return currentTemperature;

  }

}
