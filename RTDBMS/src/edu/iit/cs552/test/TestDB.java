package edu.iit.cs552.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.iit.cs552.entity.Transaction;
import edu.iit.cs552.scheduler.EDFScheduler;
import edu.iit.cs552.scheduler.NPScheduler;
import edu.iit.cs552.scheduler.RPScheduler;
import edu.iit.cs552.scheduler.TransactionScheduler;

public class TestDB {

	public static void main(String[] args) {
		Random randomGenerator = new Random();
		List<String> columns = new ArrayList<String>();
		columns.add("symbol");
		columns.add("company");
		columns.add("description");
		columns.add("price");

		TransactionScheduler edfscheduler = new EDFScheduler(columns,
				"stock_edf");
		TransactionScheduler rpscheduler = new RPScheduler(columns, "stock_rp");
		TransactionScheduler npscheduler = new NPScheduler(columns, "stock_np");

		columns.clear();
		columns.add("TCS");
		columns.add("Tata Consultancy Services");
		columns.add("IT Company");
		columns.add("$454");

		String arr[] = { "452", "456", "458", "462", "476" };
		for (int i = 0; i < 25; i++) {
			columns.remove(columns.size() - 1);
			columns.add(arr[i % 5]);
			int deadline = randomGenerator.nextInt(30);
			edfscheduler.queueTransaction(new Transaction(columns, deadline));
			rpscheduler.queueTransaction(new Transaction(columns, deadline));
			npscheduler.queueTransaction(new Transaction(columns, deadline));
		}

		edfscheduler.stop = true;
		rpscheduler.stop = true;
		npscheduler.stop = true;

	}

}
