package edu.iit.cs552.scheduler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import org.apache.log4j.Logger;

import edu.iit.cs552.entity.Transaction;
import edu.iit.cs552.utility.Constants;

public class AEDScheduler extends TransactionScheduler implements
		Comparator<Transaction> {

	Random random = new Random();
	List<Transaction> missList = new ArrayList<Transaction>();
	Logger log = Logger.getLogger(AEDScheduler.class);

	@Override
	public int compare(Transaction t1, Transaction t2) {
		// TODO Auto-generated method stub
		long diff = t1.getAbsoluteDeadline() - t2.getAbsoluteDeadline();
		if (diff == 0) {
			diff = t1.getAedMapping() - t2.getAedMapping();
		}
		return (int) diff;

	}

	int miss = 0;
	int hit = 0;
	int total = 0;
	int hitCapacity = 1;
	double hitRatio = 1.0;
	double hitAllRatio = 1.0;
	PriorityQueue<Transaction> hitQueue = new PriorityQueue<Transaction>(
			Constants.CAPACITY, this);

	public AEDScheduler(List<String> columns, String table) {
		super(columns, table);
		new Thread(this).start();
	}

	public void queueTransaction(Transaction transaction) {
		transaction.schedule();
		transaction.setAedMapping(random.nextInt(Constants.CAPACITY));
		int currIndex = hitQueue.size() + 1;
		if (currIndex <= hitCapacity)
			hitQueue.offer(transaction);
		else
			missList.add(transaction);
	}

	public void run() {
		boolean canContinue = true;
		while (canContinue) {
			canContinue = !(stop && hitQueue.isEmpty() && missList.isEmpty());
			Transaction transaction = null;
			if (!hitQueue.isEmpty()) {
				transaction = hitQueue.poll();
			} else if (!missList.isEmpty()) {
				int index = new Random().nextInt(missList.size());
				transaction = missList.get(index);
				missList.remove(index);
			}

			if (transaction != null) {
				total++;
				if (processTransaction(transaction, log)) {
					hit++;
				} else {
					miss++;
				}
			}
			calculate();
		}
		log.info("Hit:[" + hit + "]Miss[" + miss + "]Total[" + total + "]");
	}

	private void calculate() {
		hitCapacity = (int) Math.floor(hitCapacity * 1.05 * hitRatio);
		if (hitAllRatio < 0.95) {
			int size = hitQueue.size() + missList.size();
			double computed = hitAllRatio * size * 1.25;
			hitCapacity = hitCapacity > computed ? (int) computed : hitCapacity;
		}

	}
}
