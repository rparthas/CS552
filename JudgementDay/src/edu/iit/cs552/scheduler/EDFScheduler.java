package edu.iit.cs552.scheduler;

import java.util.Comparator;
import java.util.PriorityQueue;

import org.apache.log4j.Logger;

import edu.iit.cs552.entity.Job;
/**
 * Dynamic priority scheduler schedules based on the absolute deadline of the task
 * @author Rajagopal
 *
 */
public class EDFScheduler extends Scheduler implements Comparator<Job> {

	Logger log = Logger.getLogger(EDFScheduler.class);

	@Override
	public int compare(Job j1, Job j2) {
		// TODO Auto-generated method stub
		long diff = j1.deadline - j2.deadline;
		if (diff == 0) {
			diff = j1.arrivalTime - j2.arrivalTime;
		}
		return (int) diff;

	}

	public EDFScheduler() {
		pq = new PriorityQueue<Job>(10, this);
	}
}
