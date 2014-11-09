package edu.iit.cs552.utility;
import java.io.Closeable;

import org.apache.log4j.Logger;

public class UtilityFunctions {
	public static Logger logger = Logger.getLogger(UtilityFunctions.class);

	public static void close(Closeable resource) {
		if (resource != null)
			try {
				resource.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("Exception in closing the resource", e);
			}
	}
}
