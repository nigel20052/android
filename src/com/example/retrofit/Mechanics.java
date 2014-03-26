package com.example.retrofit;

import java.util.List;
import com.example.retrofit.R;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.app.ListActivity;

public class Mechanics extends ListActivity {
	private StorageDAO db;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mechanics);     
	    
		db = new StorageDAO(this);
		db.open();
		List<Mechanic> values = db.getAllMechanics();
		
		ArrayAdapter<Mechanic> adapter = new ArrayAdapter<Mechanic>(this,android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);

	}

	
}
