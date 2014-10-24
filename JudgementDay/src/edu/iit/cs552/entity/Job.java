package edu.iit.cs552.entity;

/**
 * This contains the job detail of each task 
 * It contains Six properties 
 * 1.Period - repeating time interval
 * 2.name - identifier for the task
 * 3.instance - unique identifier of the particular run of the task
 * 4.Arrival Time - The time at which job is available for execution
 * 5.Deadline - Absolute deadline for the job 
 * 6.Execution time - The time taken for completion of the job
 * 
 * @author Rajagopal
 *
 */
public class Job {

	public long period;

	public long getPeriod() {
		return period;
	}

	public void setPeriod(long period) {
		this.period = period;
	}

	public String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long executionTime;

	public long getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(long executionTime) {
		this.executionTime = executionTime;
	}

	public long deadline;

	public long arrivalTime;

	public long getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(long arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public long getDeadline() {
		return deadline;
	}

	public void setDeadline(long deadline) {
		this.deadline = deadline;
	}

	public long instance;

	public long getInstance() {
		return instance;
	}

	public void setInstance(long instance) {
		this.instance = instance;
	}

	public Job() {

	}

	public Job(long arrivalTime, long deadline, long period,
			long executionTime, long instance, String name) {
		this.deadline = deadline;
		this.instance = instance;
		this.executionTime = executionTime;
		this.arrivalTime = arrivalTime;
		this.name = name;
		this.period = period;
	}

	@Override
	public String toString() {
		return "Job [period=" + period + ", name=" + name + ", executionTime="
				+ executionTime + ", deadline=" + deadline + ", arrivalTime="
				+ arrivalTime + ", instance=" + instance + "]";
	}

	public void execute() {
		executionTime--;
	}

	public String pretty() {
		return "Job [Instance=" + instance + ",Name=" + name + "]";
	}

	public boolean equals(Job job) {
		return job != null && this.name.equals(job.name)
				&& this.instance == job.instance;
	}
}
