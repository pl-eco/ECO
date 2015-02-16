package tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//ps -o pcpu -p 24648  get cpu utilization
//%CPU
//67.9

//pgrep -f java   (get process id)

public class CPUTemp {
	static {
		new Thread(new CPUTempMonitor()).start();
	}
	private static int updataInterval = 4000;
	private static int cpuTemp = 0;

	// the return will be between 0 -- 100
	public static int getCPUTemp() {
		return cpuTemp;
	}

	public static void main(String[] args) {
		try {
			while (true) {
				// System.out.println(CPUTemp.getCPUTemp());
				Thread.sleep(4000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	static class CPUTempMonitor implements Runnable {
		@Override
		public void run() {
			try {
				while (true) {
					Process p = Runtime
							.getRuntime()
							.exec("cat /sys/devices/pci0000:00/0000:00:18.3/temp1_input /sys/devices/pci0000:00/0000:00:19.3/temp1_input /sys/devices/pci0000:00/0000:00:1a.3/temp1_input /sys/devices/pci0000:00/0000:00:1b.3/temp1_input");
					BufferedReader br = new BufferedReader(
							new InputStreamReader(p.getInputStream()));
					int t1 = Integer.parseInt(br.readLine());
					int t2 = Integer.parseInt(br.readLine());
					int t3 = Integer.parseInt(br.readLine());
					int t4 = Integer.parseInt(br.readLine());

					System.out.println(t1 + "  " + t2 + "  " + t3 + "  " + t4);
					Thread.sleep(updataInterval);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
