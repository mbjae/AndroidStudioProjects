package com.example.database;

import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class AndroidDatabaseExample2Activity extends ListActivity {
	private CustomersDataSource datasource;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		datasource = new CustomersDataSource(this);
		datasource.open();

		List<Customer> values = datasource.getAllCustomers();

		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		ArrayAdapter<Customer> adapter = new ArrayAdapter<Customer>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
	}

	// Will be called via the onClick attribute
	// of the buttons in main.xml
	public void onClick(View view) {
		@SuppressWarnings("unchecked")
		ArrayAdapter<Customer> adapter = (ArrayAdapter<Customer>) getListAdapter();
		Customer customer = null;
		switch (view.getId()) {
		case R.id.add:
			EditText name = (EditText)(findViewById(R.id.editText1));
			EditText phone = (EditText)findViewById(R.id.editText2);
			customer = datasource.createCustomer(name.getText().toString(), phone.getText().toString());
			adapter.add(customer);
			break;
		case R.id.delete:
			if (getListAdapter().getCount() > 0) {
				customer = (Customer) getListAdapter().getItem(0);
				datasource.deleteCustomer(customer);
				adapter.remove(customer);
			}
			break;
		}
		adapter.notifyDataSetChanged();
	}

	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}

}
