package edu.iit.cs552.entity;

/**
 * This contains the job detail of each task It contains five properties 1.
 * priority column 2. ascending if true 3. instance 4. execution time
 * 
 * @author Rajagopal
 *
 */
public class Job {

	public int executionTime;

	public int getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(int executionTime) {
		this.executionTime = executionTime;
	}

	public int deadline;
	
	public int arrivalTime;

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getDeadline() {
		return deadline;
	}

	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}

	public int instance;

	public int getInstance() {
		return instance;
	}

	public void setInstance(int instance) {
		this.instance = instance;
	}

	public Job() {

	}

	public Job(int arrivalTime,int deadline, int executionTime, int instance) {
		this.deadline = deadline;
		this.instance = instance;
		this.executionTime = executionTime;
		this.arrivalTime = arrivalTime;
	}

	public String toString() {
		return "Instance:[" + instance + "] Execution Time[" + executionTime
				+ "]Deadline[" + deadline + "]Arrival time["+arrivalTime+"]";
	}
}
