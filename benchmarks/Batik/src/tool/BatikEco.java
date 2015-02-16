package tool;

import java.io.File;
import java.io.IOException;

//import tools.BatteryInfo;


modes {
	high <: full;
	low <: high;
}

public class BatikEco {
	public static String fileName = null;

	//eco info
	private mcase<Integer> pattern = {low: 8000; high: 10000; full: 12000};
	private int todo = 0;
	private int finished = 0;

	public void doWork() throws IOException, InterruptedException {
		Process p1 = null;
		int index = 2;

		File dir = new File("./samples/");
		
		todo = WorkCounter.getTotal() * pattern[2];
		//sustainable block
		sustainable{
			for (String name : dir.list()) {
				uniform{
					fileName = name;
					if (!WorkCounter.workCount.containsKey(name))
						continue;
					p1 = Runtime.getRuntime().exec(
							"java -jar -Xmx8G batik-rasterizer.jar -h " + pattern.intValue();
									+ " ./samples/" + name);
					p1.waitFor();
		
					WorkCounter.workCount.remove(name);
				}
				//eco statements
				int workLoad = WorkCounter.workCount.get(name) * pattern.intValue();
				finished += workLoad;
				todo = WorkCounter.getTotal() * pattern.intValue();
			}
		}
		bsupply (12000)
		demand(todo + finished) -> (todo);
	}

	public double getProgress() {
		return (double) finished / (double) (todo + finished);
	}

	public static void main(String[] args) throws IOException,
			InterruptedException {
		// Battery start
//		BatteryInfo.startWork(4100);
		// FileWriter
//		FileWriter fw = new FileWriter(10000);
//		fw.start();
		// END

		new BatikEco().doWork();

		// battery
//		fw.done();
//		BatteryInfo.done();
	}
}
