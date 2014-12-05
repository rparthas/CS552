package edu.iit.cs552.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import edu.iit.cs552.entity.Transaction;
import edu.iit.cs552.scheduler.AEDScheduler;
import edu.iit.cs552.scheduler.EDFScheduler;
import edu.iit.cs552.scheduler.FIFOAEDScheduler;
import edu.iit.cs552.scheduler.NPScheduler;
import edu.iit.cs552.scheduler.RPScheduler;
import edu.iit.cs552.scheduler.TransactionScheduler;
import edu.iit.cs552.utility.Constants;
import edu.iit.cs552.utility.PropertyLoader;

public class TestDBOperations {

	static Logger log = Logger.getLogger(TestDBOperations.class);

	public static void main(String[] args) {

		
		
		//Integer[] deadlines = { 50, 70, 80, 200, 300, 300, 400, 500, 600, 800,
			//	1000, 2000, 3000, 4000, 5000 };
		String [] values = PropertyLoader.getProperty("deadlines").split(",");
		Integer[] deadlines = new Integer[values.length];
		Integer[] numTrans = new Integer[values.length];
		for(int i=0;i<values.length;i++){
			deadlines[i]=Integer.parseInt(values[i]);
		}
		//Integer[] numTrans = { 10, 20, 25, 50, 100, 125, 150, 200, 300, 500,
			//	700, 850, 1000, 1500, 2000 };
		values = PropertyLoader.getProperty("numTrans").split(",");
		for(int i=0;i<values.length;i++){
			numTrans[i]=Integer.parseInt(values[i]);
		}

		Integer[][] results = new Integer[numTrans.length][5];

		for (int cnt = 0; cnt < numTrans.length; cnt++) {
			Constants.CAPACITY = numTrans[cnt];
			Constants.DEADLINE = deadlines[cnt];
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
						deadlineGenerator.nextInt(Constants.DEADLINE));
				int arrivalTime = 0;
				while (arrivalTime == 0) {
					arrivalTime = arrivalTimeGenerator
							.nextInt(Constants.ARRIVAL);
				}
				List<Transaction> transactions = workload.get(arrivalTime);
				if (transactions == null)
					transactions = new ArrayList<Transaction>();
				transactions.add(transaction);
				workload.put(arrivalTime, transactions);
			}

			printLoad(workload);

			TransactionScheduler scheduler = new EDFScheduler(columns,
					"stock_edf");
			executeTransaction(workload, scheduler);
			results[cnt][0] = scheduler.miss;
			scheduler = new RPScheduler(columns, "stock_rp");
			executeTransaction(workload, scheduler);
			results[cnt][1] = scheduler.miss;
			scheduler = new NPScheduler(columns, "stock_np");
			executeTransaction(workload, scheduler);
			results[cnt][2] = scheduler.miss;
			scheduler = new AEDScheduler(columns, "stock_aed");
			executeTransaction(workload, scheduler);
			results[cnt][3] = scheduler.miss;
			scheduler = new FIFOAEDScheduler(columns, "stock_faed");
			executeTransaction(workload, scheduler);
			results[cnt][4] = scheduler.miss;

		}

		for (int i = 0; i < deadlines.length; i++) {
			for (int j = 0; j < 5; j++) {
				log.info(results[i][j] + "\t");
			}
			log.info("\n");
		}

	}

	private static void sleep(int time) {
		try {
			if (time == -1)
				time = Constants.DEADLINE;
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			log.error("Error", e);
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
