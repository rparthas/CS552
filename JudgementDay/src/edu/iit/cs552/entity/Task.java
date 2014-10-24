package edu.iit.cs552.entity;

/**
 * This contains the task detail to be scheduled
 * It contains four properties 
 * 1.Period - repeating time interval
 * 2.name - identifier for the task
 * 3.Deadline - Relative deadline for the job 
 * 4.Execution time - The time taken for completion of the job
 * 
 * @author Rajagopal
 * 
 *
 */
public class Task {

	public String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int period;
	public int executionTime;
	public int deadline;

	public int getDeadline() {
		return deadline;
	}

	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public int getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(int executionTime) {
		this.executionTime = executionTime;
	}

	

	@Override
	public String toString() {
		return "Task [name=" + name + ", period=" + period + ", executionTime="
				+ executionTime + ", deadline=" + deadline + "]";
	}

	public boolean equals(Task task) {
		return task.executionTime == executionTime && task.period == period;
	}

}
