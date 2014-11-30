package edu.iit.cs552.scheduler;

import java.util.List;
import java.util.PriorityQueue;

import org.apache.log4j.Logger;

import edu.iit.cs552.entity.Database;
import edu.iit.cs552.entity.Transaction;

public abstract class TransactionScheduler implements Runnable {

  Logger log = Logger.getLogger(TransactionScheduler.class);

	PriorityQueue<Transaction> pq = null;
	public boolean stop = false;
	Database db = null;
	String table = null;
	int miss = 0;
	int hit = 0;

	public TransactionScheduler(List<String> columns, String table) {
		db = new Database();
		db.createTable(table, columns);
		this.table = table;
		pq = new PriorityQueue<Transaction>();
		new Thread(this).start();
	}

	public void queueTransaction(Transaction transaction) {
		pq.add(transaction);
	}

	public void run() {
		log.info("Running");
		while (!(stop && pq.isEmpty())) {
			Transaction transaction = pq.poll();
			if (transaction == null)
				continue;
			if (transaction.madeDeadline()) {
				log.debug("Transaction " + transaction + " made its deadline");
				db.addData(transaction.getDataset(), table);
				hit++;
			} else {
				log.debug("Transaction " + transaction + " missed its deadline");
				miss++;
			}
		}
		log.info("Hit:[" + hit + "]Miss[" + miss + "]");
	}

}
