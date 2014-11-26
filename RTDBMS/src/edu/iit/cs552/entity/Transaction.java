package edu.iit.cs552.entity;

import java.util.List;

public class Transaction {
	private List<String> dataset;

	private long deadline = 0;

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

	public long getDeadline() {
		return deadline;
	}

	public void setDeadline(long deadline) {
		this.deadline = deadline;
	}
	
}
