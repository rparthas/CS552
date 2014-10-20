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

		prepare(Integer.parseInt(args[0]));

	}

	private static void prepare(int n) {
		List<Task> tasks = new ArrayList<Task>();
		for (int i = 1; i <= n; i++) {
			int period = ((int) (Math.random() * n));
			int executionTime = (int) (period * Math.random() * 0.9);
			if (executionTime == 0 || period == 0 || executionTime == period) {
				continue;
			}
			Task task = new Task();
			task.name = "J" + i;
			task.period = period;
			task.executionTime = executionTime;
			task.deadline = period;
			if (!tasks.contains(task))
				tasks.add(task);
		}
		for (Task task : tasks) {
			logger.fatal(task);
		}
		Logger.getRootLogger().setLevel(Level.FATAL);
		logger.fatal("RM Scheduling starts");
		new Dispatcher().dispatchTasks(tasks, "RM");
		logger.fatal("EDF Scheduling starts");
		new Dispatcher().dispatchTasks(tasks, "EDF");
	}
}
