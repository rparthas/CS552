package edu.iit.cs552.utility;

import java.util.Calendar;

public class Timer {

	private static long time = 0;

	public static void start() {
		time = getCurrTime();
	}

	private static long getCurrTime() {
		return Calendar.getInstance().getTimeInMillis();
	}

	public static long getElapsed() {
		return getCurrTime() - time;
	}

}
