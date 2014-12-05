package edu.iit.cs552.scheduler;

import java.util.List;

import org.apache.log4j.Logger;

import edu.iit.cs552.entity.Database;
import edu.iit.cs552.entity.Transaction;

public abstract class TransactionScheduler implements Runnable {

	Database db = null;
	String table = null;
	public boolean stop = false;
	public int miss = 0;
	public int hit = 0;
	public int total = 0;

	public TransactionScheduler(List<String> columns, String table) {
		db = new Database();
		db.createTable(table, columns);
		this.table = table;
	}

	public abstract void queueTransaction(Transaction transaction);

	public boolean processTransaction(Transaction transaction, Logger log) {
		boolean hit = true;
		if (transaction.madeDeadline()) {
			log.debug("Transaction " + transaction + " made its deadline");
			db.addData(transaction.getDataset(), table);

		} else {
			log.debug("Transaction " + transaction + " missed its deadline");
			hit = false;

		}
		return hit;
	}

}