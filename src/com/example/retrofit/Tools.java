package com.example.retrofit;

import java.util.List;

import android.os.Bundle;
import android.app.ListActivity;
import android.widget.ArrayAdapter;

public class Tools extends ListActivity {
	private StorageDAO db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tools);
		
		db = new StorageDAO(this);
		db.open();
		List<Tool> values = db.getAllTools();
		
		ArrayAdapter<Tool> adapter = new ArrayAdapter<Tool>(this,android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
	}

}