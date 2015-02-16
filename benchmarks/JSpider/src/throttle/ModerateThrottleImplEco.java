package throttle;

import tools.SysInfo;
import net.javacoding.jspider.core.throttle.impl.DistributedLoadThrottleImpl;

modes {
	high <: full;
	medium <: high;
	low <: medium;
}

public class ModerateThrottleImplEco extends DistributedLoadThrottleImpl {

	private static final int tempMin = 45;// 44;
	private static final int tempMax = 53;// 50;
	private static final int tempCrit = 60;// 53;

	private mcase<Integer> tempInter = {low: 5000; medium: 3000; high: 1500; full: 0};
	
	private static Object o = new Object();

	public ModerateThrottleImplEco(int milliseconds) {
		super(milliseconds);
	}

	private int getInterval(int milli) {
		int temp = SysInfo.getCPUTemp();
		
		sustainable{
			milliseconds = tempInter;
		}
		bsupply (65)
		demand (65) -> (temp);
		
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
