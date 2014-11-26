package edu.iit.cs552.test;

import java.util.ArrayList;
import java.util.List;

import edu.iit.cs552.entity.Database;
import edu.iit.cs552.entity.Transaction;
import edu.iit.cs552.utility.DatabaseScheduler;

public class TestDB {

	public static void main(String[] args) {
		Database db = new Database();
		String table = "stock";
		List<String> columns = new ArrayList<String>();
		columns.add("symbol");
		columns.add("company");
		columns.add("description");
		columns.add("price");

		DatabaseScheduler scheduler = new DatabaseScheduler(columns, table);
		columns.clear();

		columns.add("TCS");
		columns.add("Tata Consultancy Services");
		columns.add("IT Company");
		columns.add("$454");
		
		scheduler.performTransaction(new Transaction(columns, 5));
		columns.clear();

		columns.add("TCS");
		columns.add("Tata Consultancy Services");
		columns.add("IT Company");
		columns.add("$456");

		scheduler.performTransaction(new Transaction(columns, 10));
		columns.clear();

		System.out.println(db.findByColumn(table, "symbol", "TCS"));

	}

}
