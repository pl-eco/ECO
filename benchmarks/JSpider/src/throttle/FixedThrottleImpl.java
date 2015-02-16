package throttle;

import net.javacoding.jspider.core.throttle.impl.DistributedLoadThrottleImpl;

public class FixedThrottleImpl extends
		DistributedLoadThrottleImpl {

	private static final int loadMin = 10;
	private static final int loadMax = 30;

	// private int milliseLo = 5000;
	// private int milliseHi = 1000;

	public FixedThrottleImpl(int milliseconds) {
		super(milliseconds);
		// mpattern<Integer> milliseconds = {hi:milliseHi,
		// low:milliseLo - 500};
	}

	public synchronized void throttle() {

		long thisTime = System.currentTimeMillis();

		long scheduledTime = lastAllow + milliseconds;

		// if (scheduledTime > thisTime) {
		// try {
		// Thread.sleep(scheduledTime - thisTime);
		// } catch (InterruptedException e) {
		// Thread.currentThread().interrupt();
		// }
		// }

		lastAllow = System.currentTimeMillis();
	}
}
