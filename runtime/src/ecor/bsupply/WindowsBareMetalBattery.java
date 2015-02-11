package ecor.bsupply;

import ecor.Mode; 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class WindowsBareMetalBattery extends Thread implements BareMetalBattery {
	private int remainCap;
	
	private int budget;

	private int startLabel;
	private boolean done;

	private final double offset = 0.1;

	private int lastReading;
	
	public WindowsBareMetalBattery() {
		remainCap = 0;
		budget = 40000;
		done = false;
	}
	
	public void run() {
        try {
        	Process typeperfProcess = 
        			Runtime.getRuntime().exec("typeperf \"\\BatteryStatus(*)\\RemainingCapacity\"");

        	BufferedReader bufferedReader = 
        			new BufferedReader(new InputStreamReader(typeperfProcess.getInputStream()));
        	bufferedReader.readLine();
        	bufferedReader.readLine();

        	String line;
        	while (!done) {
                if ((line = bufferedReader.readLine()) != null) {
                	if (line.length() > 25 && line.lastIndexOf(".") > line.indexOf(","))
                		remainCap = Integer.parseInt(line.substring((line.indexOf(",") + 2), line.lastIndexOf(".")));
                }
        	}

        	typeperfProcess.destroy();

        } catch (IOException e) {

        	e.printStackTrace();
        }
	}

	public int getRemainingCapacity() {
		try {
			while (remainCap == 0)
				Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return remainCap;
	}

	public Mode startWork(int bud) {
		budget = bud;
		// label the start point
		startLabel = getRemainingCapacity();
		lastReading = getRemainingCapacity();
		return Mode.hifi;
	}

	public void done() {
		done = true;

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public double getNextMode(double progress) {
		if (lastReading == getRemainingCapacity())
			return 0;

		lastReading = getRemainingCapacity();

		// compare the progress and the energy consumption
		int leftEnergy = getRemainingCapacity();
		double energy = 1 - (double) (startLabel - leftEnergy)
				/ (double) budget;
		double leftWork = 1 - progress;
		double upBound = leftWork * (1 + offset);

		System.out.println("leftWork: " + leftWork + "  upbound: " + upBound
				+ "  leftEnergy: " + energy);

		if (energy > upBound)
			return energy - upBound; // increase 0--15%
		if (energy * 1.05 < leftWork)
			return energy - leftWork * 1.1;

		return 0;
	}

	public Mode promote(Mode mode) {
		if (mode == Mode.lofi) {
			return Mode.hifi;

		} else if (mode == Mode.hifi) {
			return Mode.full;

		} else {
			return mode;
		}
	}

	public Mode demote(Mode mode) {
		if (mode.equals(Mode.full)) {
			return Mode.hifi;

		} else if (mode.equals(Mode.hifi)) {
			return Mode.lofi;

		} else {
			return mode;
		}
	}

}
