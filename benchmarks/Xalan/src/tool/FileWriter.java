package tool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import org.dacapo.xalan.ModeSelector;
import org.dacapo.xalan.TempXSLTBench;
import org.dacapo.xalan.XSLTBench;

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
		File file = new File("./output/temp" + ZTimeUtils.MMDDHHMMSS() + ".txt");
		PrintWriter pw = null;
		int time = 1;
		try {
			pw = new PrintWriter(new FileOutputStream(file));
			while (!done) {
				String print = time + "," + SysInfo.getCPUTemp() + ","
						+ ModeSelector.interval + "," + SysInfo.getCPULoad()
						+ "," + (1 - TempXSLTBench.getProgress()) + "\n";
				System.out.print(print);
				pw.write(print);
				pw.flush();
				Thread.sleep(interval);
				time += interval / 1000;
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
