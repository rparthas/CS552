package edu.iit.cs552.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import edu.iit.cs552.entity.Task;
import edu.iit.cs552.scheduler.Dispatcher;

public class RandTaskGenerator {
	static Logger logger = Logger.getLogger(RandTaskGenerator.class);

	public static void main(String[] args) {
		List<Task> tasks = new ArrayList<Task>();
		int n = 5;
		for (int i = 1; i <= n; i++) {
			int period = ((int) (Math.random() * n));
			int executionTime = (int) (period * 0.9);
			if (executionTime == 0) {
				executionTime = 1;
			}
			if (period == executionTime) {
				period = period + 1;
			}
			Task task = new Task();
			task.name = "J" + i;
			task.period = period;
			task.executionTime = executionTime;
			tasks.add(task);
		}
		Logger.getRootLogger().setLevel(Level.INFO);
		logger.fatal("RM Scheduling starts");
		new Dispatcher().dispatchTasks(tasks, "RM");
		logger.fatal("EDF Scheduling starts");
		new Dispatcher().dispatchTasks(tasks, "EDF");
	}
}
