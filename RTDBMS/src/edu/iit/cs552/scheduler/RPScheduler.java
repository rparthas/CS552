package edu.iit.cs552.scheduler;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import org.apache.log4j.Logger;

import edu.iit.cs552.entity.Transaction;

public class RPScheduler extends TransactionScheduler implements
		Comparator<Transaction> {

	Random random = new Random();

	@Override
	public int compare(Transaction t1, Transaction t2) {
		// TODO Auto-generated method stub
		if (t2 == null)
			return -1;
		if (t1 == null)
			return 1;
		long diff = random.nextInt(2) == 0 ? 1 : -1;
		log.debug("t1" + t1 + "t2" + t2);
		log.debug(t1.getAbsoluteDeadline() + "==" + t2.getAbsoluteDeadline());
		return (int) diff;

	}

	public RPScheduler(List<String> columns, String table) {
		super(columns, table);
		pq = new PriorityQueue<Transaction>(10, this);
		log = Logger.getLogger(RPScheduler.class);
	}
}
