package edu.iit.cs552.entity;

import java.util.Calendar;
import java.util.List;

public class Transaction {
	private List<String> dataset;

	private long start = 0;

	private long deadline = 0;

	private long absoluteDeadline = 0;

	public List<String> getDataset() {
		return dataset;
	}

	public void setDataset(List<String> dataset) {
		this.dataset = dataset;
	}

	public Transaction(List<String> dataset, long deadline) {
		super();
		this.dataset = dataset;
		this.deadline = deadline;
		this.start = getTime();
		absoluteDeadline = deadline + start;
	}

	public long getAbsoluteDeadline() {
		return absoluteDeadline;
	}

	public long getDeadline() {
		return deadline;
	}

	public boolean madeDeadline() {
		return getTime() <= absoluteDeadline;
	}

	private long getTime() {
		return Calendar.getInstance().getTimeInMillis();
	}

	private long getElapsed() {
		return getTime() - start;
	}

	public String toString() {
		return dataset + "start[" + start + "]Deadline[" + absoluteDeadline
				+ "]Relative[" + deadline + "] Elapsed[" + getElapsed() + "]";
	}

}
