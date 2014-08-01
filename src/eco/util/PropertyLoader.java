package eco.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {
	public static String mainClass;

	public static String entryName;

	public static String[] extraMethod;

	public static String[] onDemandClasses;

	static {
		System.out.println("Loading Property File...");
		Properties prop = new Properties();
		FileInputStream is = null;
		try {
			is = new FileInputStream("contextSen.prop");
			prop.load(is);

			mainClass = prop.getProperty("mainClass").trim();
			if (prop.containsKey("entryName")) {
				entryName = prop.getProperty("entryName").trim();
			}
			
			if (prop.containsKey("extraMethod")) {
				extraMethod = prop.getProperty("extraMethod").split(";");
			}

			if (prop.containsKey("onDemandClasses")) {
				onDemandClasses = prop.getProperty("onDemandClasses")
						.split(";");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Loading ECM File...");
		prop = new Properties();
		is = null;
		try {
			is = new FileInputStream("ecm.prop");
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
