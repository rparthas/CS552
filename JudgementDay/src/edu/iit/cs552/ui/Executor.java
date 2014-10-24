package edu.iit.cs552.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import edu.iit.cs552.entity.Task;
import edu.iit.cs552.scheduler.Dispatcher;
/**
 * Entry point for testing via files
 */
public class Executor {
	static Logger logger = Logger.getLogger(Executor.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File file = null;
		BufferedReader br = null;
		List<Task> tasks = new ArrayList<Task>();
		try {
			file = new File(args[0]);
			br = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = br.readLine()) != null) {
				if(line.startsWith("#")){
					continue;
				}
				Task task = new Task();
				String[] taskDescr = line.split(",");
				task.executionTime = Integer.parseInt(taskDescr[2]);
				task.period = Integer.parseInt(taskDescr[1]);
				task.name = taskDescr[0];
				task.deadline=Integer.parseInt(taskDescr[3]);
				tasks.add(task);
			}

			if (args.length > 1 && args[1] != null) {

				switch (args[1]) {
				case "FATAL":
					Logger.getRootLogger().setLevel(Level.FATAL);
					break;
				case "ERROR":
					Logger.getRootLogger().setLevel(Level.ERROR);
					break;
				case "DEBUG":
					Logger.getRootLogger().setLevel(Level.DEBUG);
					break;
				default:
					Logger.getRootLogger().setLevel(Level.INFO);
					break;
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error in parsing the tasks", e);
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					logger.error("Error in closing the buffer", e);
				}
		}
		logger.info("Tasks read[" + tasks + "]");
		if (tasks.size() > 0) {
			logger.info("RM Scheduling starts");
			new Dispatcher().dispatchTasks(tasks, "RM");
			logger.info("EDF Scheduling starts");
			new Dispatcher().dispatchTasks(tasks, "EDF");
		}

	}

}
