package throttle;

import tools.SysInfo;
import net.javacoding.jspider.core.throttle.impl.DistributedLoadThrottleImpl;

public class ModerateThrottleImpl extends DistributedLoadThrottleImpl {

	private static final int tempMin = 45;// 44;
	private static final int tempMax = 53;// 50;
	private static final int tempCrit = 60;// 53;

	private static final int lowTempInter = 0;
	private static final int mediTempInter = 1500;
	private static final int hiTempInter = 3000;
	private static final int critTempInter = 5000;
	private static int curInter = 0;
	private static Object o = new Object();

	public static int getInterval() {
		return curInter;
	}

	public ModerateThrottleImpl(int milliseconds) {
		super(milliseconds);
	}

	private int getInterval(int milli) {
		int temp = SysInfo.getCPUTemp();
		if (temp <= tempMin) {
			milliseconds = lowTempInter;
		} else if (temp >= tempCrit) {
			milliseconds = critTempInter;
		} else if (temp >= tempMax) {
			milliseconds = hiTempInter;
		} else {
			milliseconds = mediTempInter;
		}
		synchronized (o) {
			curInter = milliseconds;
		}
		
		return milliseconds;
	}

	public synchronized void throttle() {
		long thisTime = System.currentTimeMillis();

		milliseconds = this.getInterval(milliseconds);

		long scheduledTime = lastAllow + milliseconds;

		if (scheduledTime > thisTime) {
			try {
				Thread.sleep(scheduledTime - thisTime);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		lastAllow = System.currentTimeMillis();
	}

}
