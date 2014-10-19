package edu.iit.cs552.scheduler;

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.apache.log4j.Logger;

import edu.iit.cs552.entity.Job;
import edu.iit.cs552.utility.Timer;

public abstract class Scheduler {
	Logger log = Logger.getLogger(Scheduler.class);

	public boolean end = false;
	PriorityQueue<Job> pq = null;

	public boolean isEmpty() {
		return pq.isEmpty();
	}

	public void printJobs() {
		for (Job job : pq) {
			log.info("Job in queue:" + job);
		}
	}

	public void schedule(long totalPeriod, Map<Long, List<Job>> jobMap) {
		log.info("--------------Start of Scheduler---------");
		Timer.reset();
		long time = 0;
		while (time != totalPeriod) {
			List<Job> jobs = jobMap.get(time);
			if (jobs != null) {
				for (Job job : jobs) {
					pq.add(job);
				}
				jobMap.remove(time);
			}
			Job job = pq.peek();
			if (job != null) {
				if (time >= job.arrivalTime) {
					if (time + job.executionTime <= job.deadline) {
						log.info("Executing job[" + job + "]");
						job.execute();
						if (job.executionTime == 0) {
							pq.remove(job);
						}
					} else {
						log.fatal("Job[" + job
								+ "] missed its deadline at time ["
								+ Timer.getTime() + "]");
						pq.remove(job);
					}
				}
			}
			time = Timer.addTime();
		}
		log.info("--------------End of Scheduler---------");
	}

}
