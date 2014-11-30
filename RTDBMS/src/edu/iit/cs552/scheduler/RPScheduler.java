package edu.iit.cs552.scheduler;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import org.apache.log4j.Logger;

import edu.iit.cs552.entity.Transaction;
import edu.iit.cs552.utility.Constants;

public class RPScheduler extends TransactionScheduler implements
		Comparator<Transaction> {

	Random random = new Random();
	public Logger log = Logger.getLogger(RPScheduler.class);

	@Override
	public int compare(Transaction t1, Transaction t2) {
		// TODO Auto-generated method stub
		long diff = random.nextInt(2) == 0 ? 1 : -1;
		return (int) diff;

	}

	PriorityQueue<Transaction> pq = null;
	int miss = 0;
	int hit = 0;
	int total = 0;

	public RPScheduler(List<String> columns, String table) {
		super(columns, table);
		pq = new PriorityQueue<Transaction>(Constants.CAPACITY, this);
		new Thread(this).start();
	}

	public void queueTransaction(Transaction transaction) {
		transaction.schedule();
		pq.offer(transaction);
	}

	public void run() {
		while (!(stop && pq.isEmpty())) {
			Transaction transaction = pq.poll();
			if (transaction != null) {
				total++;
				if (processTransaction(transaction, log)) {
					hit++;
				} else {
					miss++;
				}
			}
		}
		log.info("Hit:[" + hit + "]Miss[" + miss + "]Total[" + total + "]");
	}
}
