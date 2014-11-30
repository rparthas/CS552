package edu.iit.cs552.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import edu.iit.cs552.entity.Transaction;
import edu.iit.cs552.scheduler.EDFScheduler;
import edu.iit.cs552.scheduler.NPScheduler;
import edu.iit.cs552.scheduler.RPScheduler;
import edu.iit.cs552.scheduler.TransactionScheduler;
import edu.iit.cs552.utility.Constants;

public class TestDBOperations {

	static Logger log = Logger.getLogger(TestDBOperations.class);

	public static void main(String[] args) {
		Random deadlineGenerator = new Random();
		Random arrivalTimeGenerator = new Random();
		List<String> columns = new ArrayList<String>();
		columns.add("symbol");
		columns.add("company");
		columns.add("description");
		columns.add("price");
		Map<Integer, List<Transaction>> workload = new TreeMap<Integer, List<Transaction>>();

		String arr[] = { "452", "456", "458", "462", "476" };
		for (int i = 0; i < Constants.CAPACITY; i++) {
			List<String> data = new ArrayList<String>();
			data.add("TCS");
			data.add("Tata Consultancy Services");
			data.add("IT Company");
			int index = i % 5;
			data.add(arr[index]);
			Transaction transaction = new Transaction(data,
					deadlineGenerator.nextInt(60));
			int arrivalTime = 0;
			while(arrivalTime == 0){
				arrivalTime = arrivalTimeGenerator.nextInt(15);
			}
			List<Transaction> transactions = workload.get(arrivalTime);
			if (transactions == null)
				transactions = new ArrayList<Transaction>();
			transactions.add(transaction);
			workload.put(arrivalTime, transactions);
		}

		printLoad(workload);

		TransactionScheduler edfscheduler = new EDFScheduler(columns,
				"stock_edf");
		TransactionScheduler rpscheduler = new RPScheduler(columns, "stock_rp");
		TransactionScheduler npscheduler = new NPScheduler(columns, "stock_np");

		
		executeTransaction(workload, edfscheduler);
		executeTransaction(workload, rpscheduler);
		executeTransaction(workload, npscheduler);

	}

	private static void sleep(int time) {
		try {
			if (time == -1)
				time = 2000;
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void executeTransaction(
			Map<Integer, List<Transaction>> workload,
			TransactionScheduler scheduler) {
		for (Integer time : workload.keySet()) {
			sleep(time);
			for (Transaction transaction : workload.get(time)) {
				scheduler.queueTransaction(transaction);
			}
		}
		sleep(-1);
		scheduler.stop = true;
	}

	private static void printLoad(Map<Integer, List<Transaction>> workload) {
		for (Integer key : workload.keySet()) {
			for (Transaction transaction : workload.get(key)) {
				log.debug("Transaction[" + transaction + "]");
			}
		}

	}

}
