package edu.iit.cs552.scheduler;

import static edu.iit.cs552.utility.UtilityFunctions.getTime;

import java.util.Comparator;
import java.util.PriorityQueue;

import org.apache.log4j.Logger;

import edu.iit.cs552.entity.Job;
import edu.iit.cs552.utility.UtilityFunctions;

public class RMScheduler implements Scheduler {

	PriorityQueue<Job> pq = new PriorityQueue<Job>(10, new RMComparator());
	Logger log = Logger.getLogger(RMScheduler.class);

	@Override
	public void schedule(Job job) {
		// TODO Auto-generated method stub
		pq.add(job);

	}

	public void run() {
		long startTime = getTime();
		while (true) {
			Job job = pq.peek();
			if (job != null) {
				long time = startTime - getTime() / UtilityFunctions.INCREMENT;
				if (time >= job.arrivalTime && time < job.deadline) {
					log.info("Executing job[" + job + "]");
					try {
						Thread.sleep(job.executionTime
								* UtilityFunctions.INCREMENT);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						log.error("Error in executing the job" + job);
					}
				}
			}
		}
	}


}

class RMComparator implements Comparator<Job> {

	@Override
	public int compare(Job j1, Job j2) {
		// TODO Auto-generated method stub
		if (j1.deadline < j2.deadline) {
			return 1;
		} else if (j1.deadline > j2.deadline) {
			return -1;
		} else {
			return 0;
		}

	}

}
