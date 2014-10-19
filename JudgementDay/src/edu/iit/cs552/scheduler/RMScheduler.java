package edu.iit.cs552.scheduler;

import java.util.Comparator;
import java.util.PriorityQueue;

import org.apache.log4j.Logger;

import edu.iit.cs552.entity.Job;

public class RMScheduler extends Scheduler implements Comparator<Job> {

	Logger log = Logger.getLogger(RMScheduler.class);

	@Override
	public int compare(Job j1, Job j2) {
		// TODO Auto-generated method stub
		long diff = j1.period - j2.period;
		if (diff == 0) {
			diff = j1.arrivalTime - j2.arrivalTime;
		}
		return (int) diff;

	}

	public RMScheduler() {
		pq = new PriorityQueue<Job>(10, this);
	}
}
