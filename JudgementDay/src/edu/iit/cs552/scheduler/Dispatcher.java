package edu.iit.cs552.scheduler;

import static edu.iit.cs552.utility.UtilityFunctions.getTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import edu.iit.cs552.entity.Job;
import edu.iit.cs552.entity.Task;
import edu.iit.cs552.utility.UtilityFunctions;

public class Dispatcher {

	Logger logger = Logger.getLogger(Dispatcher.class);

	public void dispatchTasks(List<Task> tasks) {
		Scheduler scheduler = new RMScheduler();
		Map<Integer, Job> jobMap = new TreeMap<Integer, Job>();

		List<Integer> periods = new ArrayList<Integer>();
		for (Task task : tasks) {
			periods.add(task.period);
		}
		int hyperPeriod = UtilityFunctions.computeLCM(periods);
		List<Job> startJobs = new ArrayList<Job>();
		for (Task task : tasks) {
			int noOfinstances = hyperPeriod / task.period;
			if (hyperPeriod % task.period != 0)
				noOfinstances++;
			for (int i = 0; i < noOfinstances; i++) {
				if (i == 0) {
					startJobs.add(new Job(0,task.period * 1, task.executionTime,
							1));
					continue;
				}
				int j = i + 1;
				jobMap.put(task.period * i, new Job(task.period*i,task.period * j,
						task.executionTime, j));
			}
		}
		logger.debug("Start jobs" + startJobs);
		logger.debug("Job Map" + jobMap);
		logger.info("Clock Starts ticking");
		for (Job job : startJobs) {
			scheduler.schedule(job);
			logger.debug("Start Job:" + job);
		}
		long startTime = getTime();
		int i = 1;
		ExecutorService es = Executors.newSingleThreadExecutor();
		es.execute(scheduler);
		while (!jobMap.isEmpty()) {
			long currTime = getTime();
			while (currTime - startTime < UtilityFunctions.INCREMENT) {
				currTime = getTime();
			}
			startTime = getTime();
			Job job = jobMap.get(i);
			if (job != null) {
				logger.debug("Job:" + job);
				scheduler.schedule(job);
				jobMap.remove(i);
			}
			i++;
		}
		
		try {
			es.awaitTermination(1000, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			logger.error("Error in waiting");
		}
		logger.info("Clock has ended");
		
	}

}
