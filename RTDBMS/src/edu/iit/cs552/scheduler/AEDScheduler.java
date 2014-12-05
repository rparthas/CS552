package edu.iit.cs552.scheduler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

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

	int hitCapacity = 1;
	int hitQueuehit = 0;
	int hitQueueTran = 0;
	double hitRatio = 1.0;
	double hitAllRatio = 1.0;
	PriorityBlockingQueue<Transaction> hitQueue = new PriorityBlockingQueue<Transaction>(
			Constants.CAPACITY, this);

	public AEDScheduler(List<String> columns, String table) {
		super(columns, table);
		new Thread(this).start();
	}

	public void queueTransaction(Transaction transaction) {
		transaction.schedule();
		transaction.setAedMapping(random.nextInt(Constants.CAPACITY));
		int currIndex = hitQueue.size() + 1;
		if (currIndex <= hitCapacity) {
			hitQueueTran++;
			hitQueue.offer(transaction);
		} else
			missList.add(transaction);
	}

	public void run() {
		boolean canContinue = true;
		while (canContinue) {
			boolean hitGrp = false;
			canContinue = !(stop && hitQueue.isEmpty() && missList.isEmpty());
			Transaction transaction = null;
			if (!hitQueue.isEmpty()) {
				transaction = hitQueue.poll();
				hitGrp = true;
			} else if (!missList.isEmpty()) {
				int index = new Random().nextInt(missList.size());
				transaction = missList.get(index);
				missList.remove(index);
			}

			if (transaction != null) {
				total++;
				if (processTransaction(transaction, log)) {
					hit++;
					if (hitGrp)
						hitQueuehit++;
				} else {
					miss++;
				}
			}
			calculate();
		}
		log.info("Hit:[" + hit + "]Miss[" + miss + "]Total[" + total + "]");
	}

	private void calculate() {
		if (hitQueueTran == 0 || total == 0)
			return;
		hitRatio = hitQueuehit / hitQueueTran;
		hitAllRatio = hit / total;
		hitCapacity = (int) Math.floor(hitCapacity * 1.05 * hitRatio);
		if (hitAllRatio < 0.95) {
			int size = hitQueue.size() + missList.size();
			double computed = hitAllRatio * size * 1.25;
			hitCapacity = hitCapacity > computed ? (int) computed : hitCapacity;
		}

	}
}
