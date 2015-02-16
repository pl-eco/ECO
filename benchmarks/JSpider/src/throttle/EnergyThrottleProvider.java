package throttle;

import net.javacoding.jspider.core.logging.Log;
import net.javacoding.jspider.core.logging.LogFactory;
import net.javacoding.jspider.core.throttle.Throttle;
import net.javacoding.jspider.core.throttle.impl.DistributedLoadThrottleImpl;
import net.javacoding.jspider.core.throttle.impl.DistributedLoadThrottleProvider;
import net.javacoding.jspider.core.util.config.PropertySet;

public class EnergyThrottleProvider extends DistributedLoadThrottleProvider {

	public Throttle createThrottle(PropertySet props) {

		/* get the interval from the configuration. */
		int interval = props.getInteger(INTERVAL, INTERVAL_DEFAULT);

		Log log = LogFactory.getLog(DistributedLoadThrottleProvider.class);

		if (interval < INTERVAL_MIN) {
			log.warn("Throttle interval < " + INTERVAL_MIN
					+ " ms is dangereous - set to minimum allowed of "
					+ INTERVAL_MIN + " ms");
			interval = INTERVAL_MIN;
		}

		log.debug("throttle interval set to " + interval + " ms.");

//		return new MaximumThrottle(interval);
		return new ModerateThrottleImpl(interval);
	}
}
