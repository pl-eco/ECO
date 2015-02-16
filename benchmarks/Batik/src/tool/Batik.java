package tool;

import java.io.File;
import java.io.IOException;

import tools.BatteryInfo;

public class Batik {
	public static int quality = 12000;
	public static int[] pattern = { 8000, 10000, 12000 };
	public static String fileName = null;

	private static int todo = 0;
	private static int finished = 0;

	public Batik() {
		todo = WorkCounter.getTotal() * pattern[2];
	}

	public void doWork() throws IOException, InterruptedException {
		Process p1 = null;
		int index = 2;

		File dir = new File("./samples/");
		int countFiles = 0;
		for (String name : dir.list()) {
			quality = pattern[index];
			fileName = name;
			if (!WorkCounter.workCount.containsKey(name))
				continue;
			countFiles++;
			p1 = Runtime.getRuntime().exec(
					"java -jar -Xmx8G batik-rasterizer.jar -h " + quality
							+ " ./samples/" + name);
			p1.waitFor();

			int workLoad = WorkCounter.workCount.get(name) * quality;
			finished += workLoad;
			WorkCounter.workCount.remove(name);
			todo = WorkCounter.getTotal() * quality;
			
			// battery strategy
			double level = BatteryInfo.getNextMode(getProgress());
			if (level > 0 && index != pattern.length - 1)
				index++;
			if (level < 0 && index != 0)
				index--;
		}
	}

	public static double getProgress() {
		return (double) finished / (double) (todo + finished);
	}

	public static void main(String[] args) throws IOException,
			InterruptedException {
		// Battery start
		BatteryInfo.startWork(4100);
		// FileWriter
		FileWriter fw = new FileWriter(10000);
		fw.start();
		// END

		new Batik().doWork();

		// battery
		fw.done();
		BatteryInfo.done();
	}
}
