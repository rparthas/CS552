package edu.iit.cs552.utility;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UtilityFunctions {

	
	public static final int INCREMENT=10;
	
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
	
	public static long getTime(){
		return Calendar.getInstance().getTimeInMillis();
	}
}
