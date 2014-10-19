package edu.iit.cs552.entity;

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
