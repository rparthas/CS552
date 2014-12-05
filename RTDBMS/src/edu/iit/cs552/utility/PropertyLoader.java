package edu.iit.cs552.utility;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;

import edu.iit.cs552.test.TestDBOperations;

public class PropertyLoader {
	static Logger log = Logger.getLogger(TestDBOperations.class);

	public static String getProperty(String name) {
		String value = "";
		Properties properties = new Properties();
		URL url = ClassLoader.getSystemResource("dataset.properties");
		try {
			properties.load(url.openStream());
			value = properties.getProperty(name);
		} catch (IOException e) {
			log.error("Error", e);
		}
		return value;
	}
}
