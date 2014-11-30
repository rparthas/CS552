package edu.iit.cs552.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.iit.cs552.entity.Database;
import edu.iit.cs552.entity.Transaction;
import edu.iit.cs552.scheduler.EDFScheduler;
import edu.iit.cs552.scheduler.TransactionScheduler;

public class TestDB {

	public static void main(String[] args) {
		Database db = new Database();
		Random randomGenerator = new Random();
		String table = "stock";
		List<String> columns = new ArrayList<String>();
		columns.add("symbol");
		columns.add("company");
		columns.add("description");
		columns.add("price");

		TransactionScheduler scheduler = new EDFScheduler(columns, table);

		columns.clear();
		columns.add("TCS");
		columns.add("Tata Consultancy Services");
		columns.add("IT Company");
		columns.add("$454");
		scheduler.queueTransaction(new Transaction(columns, 10));

		String arr[] = { "452", "456", "458", "462", "476" };
		for (int i = 0; i < 25; i++) {
			columns.remove(columns.size() - 1);
			columns.add(arr[i % 5]);
			scheduler.queueTransaction(new Transaction(columns, randomGenerator
					.nextInt(30)));
		}

		scheduler.stop = true;
		System.out.println(db.findByColumn(table, "symbol", "TCS"));

	}

}
