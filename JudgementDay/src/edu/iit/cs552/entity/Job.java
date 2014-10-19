package edu.iit.cs552.entity;


/**
 * This contains the job detail of each task It contains five properties 1.
 * priority column 2. ascending if true 3. instance 4. execution time
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

	public String toString() {
		//return "Instance:[" + instance + "] Execution Time[" + executionTime
			//	+ "]Period[" + period + "]Deadline[" + deadline
				//+ "]Arrival time[" + arrivalTime + "]Name[" + name + "]";
		return pretty();
	}

	public void execute() {
		executionTime--;
	}

	public String pretty() {
		return "Instance:[" + instance + "] Name[" + name + "]";
	}
}
