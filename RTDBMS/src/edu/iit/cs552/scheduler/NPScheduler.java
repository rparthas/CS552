package edu.iit.cs552.scheduler;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import org.apache.log4j.Logger;

import edu.iit.cs552.entity.Transaction;

public class NPScheduler extends TransactionScheduler implements
		Comparator<Transaction> {

	

	@Override
	public int compare(Transaction t1, Transaction t2) {
		// TODO Auto-generated method stub
		if (t2 == null)
			return -1;
		if (t1 == null)
			return 1;
		long diff = t1.getStart() - t2.getStart();
		log.debug("t1" + t1 + "t2" + t2);
		log.debug(t1.getAbsoluteDeadline() + "==" + t2.getAbsoluteDeadline());
		return (int) diff;

	}

	public NPScheduler(List<String> columns, String table) {
		super(columns, table);
		pq = new PriorityQueue<Transaction>(10, this);
		log = Logger.getLogger(NPScheduler.class);
	}
}
