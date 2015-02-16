package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import net.javacoding.jspider.core.storage.impl.ResourceDAOImpl;
import throttle.ModerateThrottleImpl;

public class SysInfo {
	private static final String tempFile = "C:\\Program Files\\Core Temp";
	private static final String output = "D:\\jspider-src-0.5.0-dev\\readings"
			+ ZTimeUtils.MMDDHHMMSS() + ".csv";
	private static int CPUtemp = 0;
	public static int CPULoad;

	private static int fileWriteInterval = 60000;
	private static int tempUpdateInterval = 10000;

	// cpu temp update
	// update every 5 seconds

	public static int getCPUTemp() {
		return CPUtemp;
	}

	public static int getCPULoad() {
		return CPULoad;
	}

	public static void run(int count) {

	}

	// file writer
	// write every 5 seconds, and flush every 1 min
	static {
		// CPU temp update
		new Thread() {
			@Override
			public void run() {
				File dir = new File(tempFile);
				String fileName = "";
				for (String name : dir.list()) {
					if (name.startsWith("CT-Log ")
							&& fileName.compareTo(name) < 0) {
						fileName = name;
					}
				}

				File file = new File(tempFile + "/" + fileName);
				try {
					String str = null;
					long lastModi = 0;
					BufferedReader br = null;
					while (true) {
						if (file.lastModified() != lastModi) {
							br = new BufferedReader(new FileReader(file));
							lastModi = file.lastModified();
							int temp1 = 0;
							int temp2 = 0;

							while ((str = br.readLine()) != null) {
								String[] s = str.split(",");

								if (s.length > 10 && str.length() != 168) {
									temp1 = Integer.parseInt(str.split(",")[1]);
									temp2 = Integer.parseInt(str.split(",")[2]);
								}
							}
							// System.out.println("last: " + str + "  " + temp1
							// + "  " + temp2);
							CPUtemp = (temp1 + temp2) / 2;
							br.close();
						}
						Thread.sleep(1000);
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();

		// cpu utilization updater
		new Thread() {
			@Override
			public void run() {
				try {
					Process p = Runtime
							.getRuntime()
							.exec("typeperf \"\\Processor(_Total)\\% Processor Time\"");
					BufferedReader br = new BufferedReader(
							new InputStreamReader(p.getInputStream()));
					String s;

					// jump off the description line
					br.readLine();
					br.readLine();
					while ((s = br.readLine()) != null) {
						CPULoad = Integer.parseInt(s.substring(
								(s.indexOf(",") + 2), s.lastIndexOf('.')));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();

		// file writer
		new Thread() {
			public void run() {
				File file = new File(output);
				PrintWriter pw = null;
				try {
					pw = new PrintWriter(new FileOutputStream(file));
					int count = 1;
					while (true) {
						String print = count + "," + CPUtemp + "," + CPULoad
								+ "," + ResourceDAOImpl.newC + ","
								+ ModerateThrottleImpl.getInterval() + "\n";
						pw.write(print);
						pw.flush();
						System.out.print(print);
						Thread.sleep(5000);
						count += 5;
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}
}