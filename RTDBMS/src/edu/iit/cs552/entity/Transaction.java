package edu.iit.cs552.entity;

import java.util.Calendar;
import java.util.List;

public class Transaction {
	public long getStart() {
		return start;
	}

	private List<String> dataset;

	private long start = 0;

	private long deadline = 0;

	private long absoluteDeadline = 0;
	
	private int aedMapping;

	public int getAedMapping() {
		return aedMapping;
	}

	public void setAedMapping(int aedMapping) {
		this.aedMapping = aedMapping;
	}

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

	public String toString() {
		// return dataset + "start[" + start + "]Deadline[" + absoluteDeadline
		// + "]Relative[" + deadline + "]";
		return dataset + "Relative[" + deadline + "]";

	}

	public void schedule() {
		start = getTime();
		absoluteDeadline = deadline + start;
	}

}
