package throttle;

import net.javacoding.jspider.core.throttle.impl.DistributedLoadThrottleImpl;

public class MaximumThrottle extends DistributedLoadThrottleImpl {

	public MaximumThrottle(int milliseconds) {
		super(milliseconds);
	}

	public synchronized void throttle() {
		// no sleep is needed for a maximum throttle
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
