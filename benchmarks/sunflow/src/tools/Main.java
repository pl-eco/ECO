package tools;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import org.sunflow.SunflowGUI;

import work.Work;

/*
 * driver class for sunflow experiment
 */
public class Main {
	private String rootDir = "./examples/wireframes/";
	private String output = "./output/output";

	private Queue<String> workList = new LinkedList<String>();
	public static int done = 0;
	public static int todo = 0;

	public Main() {
		// init the workList
		// for (int i = 0; i < 1; i++) {
		workList.add(rootDir + "wireframe_demo_1.java");
		workList.add(rootDir + "wireframe_demo_2.java");
		workList.add(rootDir + "wireframe_demo_3.java");
		workList.add(rootDir + "wireframe_demo_4.java");
		workList.add(rootDir + "wireframe_demo_4.java");
		workList.add(rootDir + "wireframe_demo_5.java");
		workList.add(rootDir + "wireframe_demo_6.java");
		workList.add(rootDir + "wireframe_demo_7.java");
		workList.add(rootDir + "wireframe_demo_8.java");
		// }
	}

	public void doWork() throws IOException, InterruptedException {
		// Battery start
		BatteryInfo.startWork(18860);
		// FileWriter
		FileWriter fw = new FileWriter(10000);
		fw.start();
		// END

		int id = 0;

		Mode mode = Mode.full;

		String[] args = { "-nogui", "-mode", "_3_mode level%", "-o",
				"_5_output", "_6_inputFile" };
		while (!workList.isEmpty()) {
			String fileName = workList.poll();
			fw.addComment(fileName + ": " + (mode.ordinal() + 1));

			// fill in file
			args[2] = String.valueOf(mode.ordinal());
			args[4] = output + id + ".png";
			args[5] = fileName;

			SunflowGUI.main(args);

			// update finished work
			done += Work.getWorkAmount(new File(fileName), mode);

			// do the battery Check
			// while (true) {
			todo = Work.getWorkAmount(new File(fileName), mode)
					* workList.size();
			double level = BatteryInfo.getNextMode(getProgress());
			if (level > 0)
				mode = BatteryInfo.promote(mode);
			else if (level < 0)
				mode = BatteryInfo.demote(mode);
			
			System.out.println("Mode: " + mode.toString());
			// }
		}
		// battery
		fw.done();
		BatteryInfo.done();

	}

	public static double getProgress() {
		return (double) done / (double) (todo + done);
	}

	public static void main(String[] args) {

		try {
			BatteryInfo.go();
			new Main().doWork();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
