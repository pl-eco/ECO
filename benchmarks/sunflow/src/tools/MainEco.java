package tools;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import org.sunflow.SunflowGUI;

import work.Work;

modes {
	high <: full;
	low <: high;
}

/*
 * driver class for sunflow experiment
 */
public class MainEco {
	private String rootDir = "./examples/wireframes/";
	private String output = "./output/output";

	private Queue<String> workList = new LinkedList<String>();
	public int done = 0;
	public int todo = 0;

	public MainEco() {
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
		int id = 0;

		mcase<Integer> mode = {low: 0; high: 1; full: 2 };

		String[] args = { "-nogui", "-mode", "_3_mode level%", "-o",
				"_5_output", "_6_inputFile" };

		sustainable{
			while (!workList.isEmpty()) {
				uniform{
					String fileName = workList.poll();
					// fill in file
					args[2] = String.valueOf(mode);
					args[4] = output + id + ".png";
					args[5] = fileName;
					SunflowGUI.main(args);
				}
				// update finished work
				done += Work.getWorkAmount(new File(fileName), mode);
				// do the battery Check
				todo = Work.getWorkAmount(new File(fileName), mode)
						* workList.size();
			}
		}
		bsupply(18860)
		demand(done + todo) -> (todo);
	}

	public double getProgress() {
		return (double) done / (double) (todo + done);
	}

	public static void main(String[] args) {

		try {
			BatteryInfo.go();
			new MainEco().doWork();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
