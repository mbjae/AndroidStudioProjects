package com.example.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CustomersDataSource {

	// Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_CUST_NAME,
			MySQLiteHelper.COLUMN_PHONE };

	public CustomersDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Customer createCustomer(String customer, String phone) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_CUST_NAME, customer);
		values.put(MySQLiteHelper.COLUMN_PHONE, phone);
		long insertId = database.insert(MySQLiteHelper.TABLE_CUSTOMERS, null,
				values);
		// To show how to query
		Cursor cursor = database.query(MySQLiteHelper.TABLE_CUSTOMERS,
				allColumns, MySQLiteHelper.COLUMN_CUST_NAME + " = '" + customer + "'", null,
				null, null, null);
		cursor.moveToFirst();
		return cursorToCustomer(cursor);
	}

	public void deleteCustomer(Customer customer) {
		String name = customer.getCustName();
		System.out.println("Customer deleted with name: " + name);
		database.delete(MySQLiteHelper.TABLE_CUSTOMERS, MySQLiteHelper.COLUMN_CUST_NAME
				+ " = '" + name + "'", null);
	}

	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		Cursor cursor = database.query(MySQLiteHelper.TABLE_CUSTOMERS,
				allColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Customer customer = cursorToCustomer(cursor);
			customers.add(customer);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return customers;
	}

	private Customer cursorToCustomer(Cursor cursor) {
		Customer customer = new Customer();
		customer.setCustName(cursor.getString(0));
		customer.setPhone(cursor.getString(1));
		return customer;
	}
}