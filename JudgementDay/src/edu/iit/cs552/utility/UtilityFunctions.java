package edu.iit.cs552.utility;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class UtilityFunctions {

	static Logger logger = Logger.getLogger(UtilityFunctions.class);

	public static Integer computeLCM(List<Integer> numbers) {

		Integer LCM = 0;
		if (numbers.size() == 1)
			LCM = numbers.get(0);
		else {
			List<Integer> lcms = new ArrayList<Integer>();
			for (int i = 0; i < numbers.size(); i = i + 2) {
				if (i == numbers.size() - 1) {
					lcms.add(numbers.get(i));
					continue;
				}
				lcms.add(lcm2(numbers.get(i), numbers.get(i + 1)));

			}
			LCM = computeLCM(lcms);
		}

		return LCM;
	}

	private static int gcd2(int num1, int num2) {
		while (num1 != num2) {

			if (num1 > num2)
				num1 = num1 - num2;
			else
				num2 = num2 - num1;
		}
		return num1;
	}

	private static int lcm2(int num1, int num2) {
		return num1 * num2 / gcd2(num1, num2);
	}

	public static void printStat(List<String> stats) {
		Level level = Logger.getRootLogger().getLevel();
		Logger.getRootLogger().setLevel(Level.INFO);
		logger.info("--------------Stats of the taskset begins---------");
		for (String stat : stats) {
			logger.fatal(stat);
		}
		logger.info("--------------Stats of the taskset ends---------");
		Logger.getRootLogger().setLevel(level);
	}

}
