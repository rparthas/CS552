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

	public long period;
	public long executionTime;
	public long deadline;

	public long getDeadline() {
		return deadline;
	}

	public void setDeadline(long deadline) {
		this.deadline = deadline;
	}

	public long getPeriod() {
		return period;
	}

	public void setPeriod(long period) {
		this.period = period;
	}

	public long getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(long executionTime) {
		this.executionTime = executionTime;
	}

	

	@Override
	public String toString() {
		return "Task [name=" + name + ", period=" + period + ", executionTime="
				+ executionTime + ", deadline=" + deadline + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (executionTime ^ (executionTime >>> 32));
		result = prime * result + (int) (period ^ (period >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (executionTime != other.executionTime)
			return false;
		if (period != other.period)
			return false;
		return true;
	}
	
	
}
