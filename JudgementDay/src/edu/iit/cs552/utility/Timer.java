package edu.iit.cs552.utility;

/**
 * Static class for tracking time 
 * @author Rajagopal
 *
 */
public class Timer {

	static long time =0;
	
	
	private Timer(){
		
	}
	
	public static long getTime(){
		return time;
	}
	
	public static long addTime(){
		time=time+1;
		return time;
	}
	
	public static void reset(){
		time=0;
	}
}
