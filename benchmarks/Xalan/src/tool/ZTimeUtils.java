package tool;

import java.util.Calendar;

public class ZTimeUtils {
	static private Calendar cal;

	static {
		cal = Calendar.getInstance();
	}

	private static void getYear(StringBuffer sb) {
		sb.append(cal.get(Calendar.YEAR));
	}

	private static void getMonth(StringBuffer sb) {
		if (cal.get(Calendar.MONTH) < 9)
			sb.append("0"); // month
		sb.append(cal.get(Calendar.MONTH) + 1);
	}

	private static void getDay(StringBuffer sb) {
		if (cal.get(Calendar.DAY_OF_MONTH) < 10)
			sb.append("0"); // day of month
		sb.append(cal.get(Calendar.DAY_OF_MONTH));
	}

	private static void getHour(StringBuffer sb) {
		if (cal.get(Calendar.HOUR_OF_DAY) < 10)
			sb.append("0"); // hour of day
		sb.append(cal.get(Calendar.HOUR_OF_DAY));
	}

	private static void getMinute(StringBuffer sb) {
		if (cal.get(Calendar.MINUTE) < 10)
			sb.append("0"); // minute
		sb.append(cal.get(Calendar.MINUTE));
	}

	private static void getSecond(StringBuffer sb) {
		if (cal.get(Calendar.SECOND) < 10)
			sb.append("0");
		sb.append(cal.get(Calendar.SECOND));
	}

	public static String MMDD(){
		StringBuffer sb = new StringBuffer();
		sb.setLength(0);

		getMonth(sb);
		getDay(sb);
		
		return sb.toString();
	}
	
	public static String YYMMDD() {
		StringBuffer sb = new StringBuffer();
		sb.setLength(0);

		getYear(sb);
		
		sb.delete(0, 2);
		getMonth(sb);
		getDay(sb);
		
		return sb.toString();
	}
	
	
	/*
	 * 解析时间方法 （Calendar） 将当前时间以YYYYMMDD的形式返回
	 */
	public static String YYYYMMDD() {
		StringBuffer sb = new StringBuffer();
		sb.setLength(0);

		getYear(sb);
		getMonth(sb);
		getDay(sb);
		
		return sb.toString();
	}
	
	public static String YYYYMMDDHHMMSS() {
		StringBuffer sb = new StringBuffer();
		sb.setLength(0);

		getYear(sb);
		getMonth(sb);
		getDay(sb);
		getHour(sb);
		getMinute(sb);
		getSecond(sb);
		
		return sb.toString();
	}
	
	public static String MMDDHHMMSS() {
		StringBuffer sb = new StringBuffer();
		sb.setLength(0);
		
		cal = Calendar.getInstance();
		getMonth(sb);
		getDay(sb);
		getHour(sb);
		getMinute(sb);
		getSecond(sb);
		
		return sb.toString();
	}
}
