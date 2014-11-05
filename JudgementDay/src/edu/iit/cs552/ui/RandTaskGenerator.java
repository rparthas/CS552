package edu.iit.cs552.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import edu.iit.cs552.entity.Task;
import edu.iit.cs552.scheduler.Dispatcher;

/**
 * This generates random tasks and evaluates the scheduler performance This is
 * scheduled to make sure that scheduler runs at overload condition
 * 
 * @author Rajagopal
 *
 */
public class RandTaskGenerator {
	static Logger logger = Logger.getLogger(RandTaskGenerator.class);

	public static void main(String[] args) {

		prepare(Integer.parseInt(args[0]),Double.parseDouble(args[1]),Integer.parseInt(args[2]));

	}

	private static void prepare(int n,double percent,int seed) {
		List<Task> tasks = new ArrayList<Task>();
		Random randomGenerator = new Random();
		double multiplier = percent/n;
		for (int i = 0; i < n; i++) {
			int period = randomGenerator.nextInt(seed);
			int executionTime = (int) Math.ceil(period * multiplier);
			if (executionTime == 0 || period == 0 || executionTime == period) {
				continue;
			}
			Task task = new Task();
			task.name = "J" + (i + 1);
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
