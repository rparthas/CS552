package edu.iit.cs552.entity;

import static edu.iit.cs552.entity.Constants.*;
import static edu.iit.cs552.utility.UtilityFunctions.close;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

public class Database {
	public static Logger log = Logger.getLogger(Database.class);

	public void createTable(String name, List<String> columns) {
		try {

			StringBuffer buffer = new StringBuffer();
			boolean first = true;
			for (String col : columns) {
				if (!first)
					buffer.append(DELIMITER);
				first = false;
				buffer.append(col);
			}
			if (!writeToFile(buffer.toString(), name)) {
				throw new Exception("Write failed");
			}
		} catch (Exception e) {
			log.error("Table creation failed", e);
		}
	}

	public void addData(List<String> data, String table) {
		BufferedReader reader = null;
		try {
			String result = findByColumn(table, PRIMARY, data.get(0));
			if (result.isEmpty()) {
				createTable(table, data);
			} else {
				updateData(data, table, PRIMARY, data.get(0));
			}
		} catch (Exception e) {
			log.error("Insert data failed", e);
		}

	}

	private void modifyData(List<String> values, String table, String column,
			String value) {

		BufferedReader reader = null;
		try {
			boolean first = true;
			int index = -1;
			reader = new BufferedReader(new FileReader(table));
			String data = null;
			while ((data = reader.readLine()) != null) {
				String rowCols[] = data.split(DELIMITER);
				if (first) {
					index = Arrays.asList(rowCols).indexOf(column);
					if (index == -1)
						throw new Exception("Column does not exist");
				} else if (value.equals(rowCols[index])) {
					StringBuffer buffer = new StringBuffer();
					boolean initial = true;
					for (String col : values) {
						if (!initial)
							buffer.append(DELIMITER);
						initial = false;
						buffer.append(col);
					}
					data = buffer.toString();
				}
				writeToFile(data, table + "1");
				first = false;
			}

		} catch (Exception e) {
			log.error("findByPrimaryKey failed", e);
		} finally {
			close(reader);
		}
	}

	public void updateData(List<String> values, String table, String column,
			String value) {

		modifyData(values, table, column, value);
		File file = new File(table);
		if (file.delete()) {
			File newFile = new File(table + "1");
			newFile.renameTo(file);
		}
	}

	public String findByColumn(String table, String column, String value) {
		String result = "";
		BufferedReader reader = null;
		try {
			boolean first = true;
			int index = -1;
			reader = new BufferedReader(new FileReader(table));
			String data = null;
			while ((data = reader.readLine()) != null) {
				String columns[] = data.split(DELIMITER);
				if (first) {
					index = Arrays.asList(columns).indexOf(column);
					if (index == -1)
						throw new Exception("Column does not exist");
				} else {
					if (value.equals(columns[index])) {
						result = data.replace(DELIMITER, "   ");
						break;
					}
				}
				first = false;
			}
		} catch (Exception e) {
			log.error("findByPrimaryKey failed", e);
		} finally {
			close(reader);
		}
		return result;
	}

	public boolean writeToFile(String content, String fname) throws Exception {
		boolean wrote = false;

		FileWriter writer = null;
		try {
			File file = new File(fname);
			if (file.exists()) {
				writer = new FileWriter(fname, true);
			} else {
				writer = new FileWriter(fname);
			}
			writer.write(content + "\n");
			wrote = true;
		} finally {
			close(writer);
		}

		return wrote;
	}

}
