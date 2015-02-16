package tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import tools.BatteryInfo;
import tools.ZTimeUtils;

public class FileWriter extends Thread {
	private int interval;
	private boolean done = false;
	private StringBuilder sb = new StringBuilder("");

	public FileWriter(int interval) {
		this.interval = interval;
	}

	public void done() {
		done = true;
	}

	public void addComment(String comment) {
		sb.append(comment).append("\n");
	}

	public void run() {
		File file = new File("./output/record" + ZTimeUtils.MMDDHHMMSS()
				+ ".txt");
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileOutputStream(file));
			while (!done) {
				String print = ZTimeUtils.MMDDHHMMSS() + ","
						+ BatteryInfo.getRemainingCap() + ","
						+ (100 - Main.getProgress() * 100) + "\n";
				System.out.print(print);
				pw.write(print);
				pw.flush();
				Thread.sleep(interval);
			}
			pw.write("Job done.");
			pw.write("comment: \n");
			pw.write(sb.toString());
			pw.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
