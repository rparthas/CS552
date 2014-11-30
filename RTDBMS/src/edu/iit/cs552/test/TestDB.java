package edu.iit.cs552.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import edu.iit.cs552.entity.Transaction;
import edu.iit.cs552.scheduler.AEDScheduler;
import edu.iit.cs552.scheduler.EDFScheduler;
import edu.iit.cs552.scheduler.FIFOAEDScheduler;
import edu.iit.cs552.scheduler.NPScheduler;
import edu.iit.cs552.scheduler.RPScheduler;
import edu.iit.cs552.scheduler.TransactionScheduler;
import edu.iit.cs552.utility.Constants;

public class TestDB {

	static Logger log = Logger.getLogger(TestDB.class);

	public static void main(String[] args) {
		Random deadlineGenerator = new Random();
		Random arrivalTimeGenerator = new Random();
		List<String> columns = new ArrayList<String>();
		columns.add("symbol");
		columns.add("company");
		columns.add("description");
		columns.add("price");

		String arr[] = { "452", "456", "458", "462", "476" };
		TransactionScheduler edfscheduler = new EDFScheduler(columns,
				"stock_edf");
		TransactionScheduler rpscheduler = new RPScheduler(columns, "stock_rp");
		TransactionScheduler npscheduler = new NPScheduler(columns, "stock_np");
		TransactionScheduler aedscheduler = new AEDScheduler(columns,
				"stock_aed");
		TransactionScheduler faedscheduler = new FIFOAEDScheduler(columns,
				"stock_faed");

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
			while (arrivalTime != 0) {
				arrivalTime = arrivalTimeGenerator.nextInt(15);
				sleep(arrivalTime);
			}

			edfscheduler.queueTransaction(transaction);
			rpscheduler.queueTransaction(transaction);
			npscheduler.queueTransaction(transaction);
			aedscheduler.queueTransaction(transaction);
			faedscheduler.queueTransaction(transaction);
		}
		sleep(-1);
		edfscheduler.stop = true;
		rpscheduler.stop = true;
		npscheduler.stop = true;
		aedscheduler.stop = true;
		faedscheduler.stop = true;

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

}
