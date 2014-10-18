package edu.iit.cs552.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.iit.cs552.entity.Task;
import edu.iit.cs552.scheduler.Dispatcher;
import edu.iit.cs552.utility.UtilityFunctions;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int a = 4;
		if (a % 2 == 0)
			System.out.println(a / 2);
		else
			System.out.println(a / 2 + 1);

		
		List<Task> tasks = new ArrayList<Task>();
		Task task = new Task();
		task.executionTime = 2;
		task.period = 3;
		tasks.add(task);
		task = new Task();
		task.executionTime = 1;
		task.period = 2;
		tasks.add(task);
		new Dispatcher().dispatchTasks(tasks);
	}
	
	

}
