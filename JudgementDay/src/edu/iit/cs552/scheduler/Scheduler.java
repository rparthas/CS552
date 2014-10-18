package edu.iit.cs552.scheduler;

import edu.iit.cs552.entity.Job;

public interface Scheduler extends Runnable {

	public void schedule(Job job);
}
