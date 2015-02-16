package tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CPULoad {
	static {
		new Thread(new CPUMonitor()).start();
	}

	private static int cpuload = 0;

	// the return will be between 0 -- 100
	public static int getCPULoad() {
		return cpuload;
	}

	public static void main(String[] args) {
		try {
			while (true) {
				System.out.println(CPULoad.getCPULoad());
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	static class CPUMonitor implements Runnable {
		@Override
		public void run() {
			try {
				Process p = Runtime.getRuntime().exec(
						"typeperf \"\\Processor(_Total)\\% Processor Time\"");
				BufferedReader br = new BufferedReader(new InputStreamReader(
						p.getInputStream()));
				String s;

				// jump off the description line
				br.readLine();
				br.readLine();
				while ((s = br.readLine()) != null) {
					cpuload = Integer.parseInt(s.substring(
							(s.indexOf(",") + 2), s.lastIndexOf('.')));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
