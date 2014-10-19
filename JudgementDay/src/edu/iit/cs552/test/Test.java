package edu.iit.cs552.test;

import java.util.ArrayList;
import java.util.List;

import edu.iit.cs552.entity.Task;
import edu.iit.cs552.scheduler.Dispatcher;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Task> tasks = new ArrayList<Task>();
		Task task = new Task();
		
		task.executionTime = 2;
		task.period = 3;
		task.name = "J1";
		tasks.add(task);
		task = new Task();
		task.executionTime = 1;
		task.period = 2;
		task.name = "J2";
		tasks.add(task);
		task = new Task();
		task.executionTime = 3;
		task.period = 6;
		task.name = "J3";
		tasks.add(task);
		new Dispatcher().dispatchTasks(tasks, "RM");
		new Dispatcher().dispatchTasks(tasks, "EDF");
	}

}
