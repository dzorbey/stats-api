package nl.advidi.camel.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties {

	public static Properties getProperties() {

		Properties prop = new Properties();
		InputStream input = null;
		try {
			ClassLoader sysClassLoader = ClassLoader.getSystemClassLoader();
			InputStream is = sysClassLoader.getResourceAsStream("api.properties");
			prop.load(is);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}
}
