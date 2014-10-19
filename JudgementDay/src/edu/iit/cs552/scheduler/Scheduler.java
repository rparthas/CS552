package edu.iit.cs552.scheduler;

import java.util.ArrayList;
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

	public List<String> schedule(long totalPeriod, Map<Long, List<Job>> jobMap) {
		log.info("--------------Start of Scheduler---------");
		List<String> stats = new ArrayList<String>();
		Timer.reset();
		long time = 0;
		int preEmptions = 0;
		int miss = 0;
		Job prevJob = null;
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
						if (prevJob != null && job.equals(prevJob)) {
							preEmptions++;
						}
						job.execute();
						prevJob = job;
						if (job.executionTime == 0) {
							pq.remove(job);
						}
					} else {
						log.fatal("Job[" + job
								+ "] missed its deadline at time ["
								+ Timer.getTime() + "]");
						pq.remove(job);
						miss++;
					}
				}
			}
			time = Timer.addTime();
		}
		stats.add("No of Preemptions[" + preEmptions + "]");
		stats.add("No of Deadline Misses[" + miss + "]");
		log.info("--------------End of Scheduler---------");
		return stats;
	}

}
