package edu.iit.cs552.utility;

import static edu.iit.cs552.utility.Timer.*;

import java.util.List;

import edu.iit.cs552.entity.Database;
import edu.iit.cs552.entity.Transaction;

public class DatabaseScheduler {

	Database db = null;
	String table = null;

	public DatabaseScheduler(List<String> columns, String table) {
		db = new Database();
		db.createTable(table, columns);
		this.table = table;
		start();
	}

	public void performTransaction(Transaction transaction) {
		if (getElapsed() < transaction.getDeadline()) {
			db.addData(transaction.getDataset(), table);
		}
	}
}
