package edu.iit.cs552.test;

import java.util.ArrayList;
import java.util.List;

import edu.iit.cs552.entity.Database;

public class TestDB {

	public static void main(String[] args) {
		Database db = new Database();
		String table = "stock";
		List<String> columns = new ArrayList<String>();
		columns.add("symbol");
		columns.add("company");
		columns.add("description");
		columns.add("price");
		columns.add("time");
		db.createTable(table, columns);
		columns.clear();
		columns.add("TCS");
		columns.add("Tata Consultancy Services");
		columns.add("IT Company");
		columns.add("$454");
		columns.add("08-11-14 15:48:23");
		db.addData(columns, table);
		columns.clear();
		columns.add("TCS");
		columns.add("Tata Consultancy Services");
		columns.add("IT Company");
		columns.add("$456");
		columns.add("08-11-14 15:48:23");
		db.updateData(columns, table, "symbol", "TCS");
		System.out.println(db.findByColumn(table, "symbol", "TCS"));

	}

}
