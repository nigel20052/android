package com.example.retrofit;

import com.example.retrofit.R;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.Activity;
import android.content.Intent;

public class SecondActivity extends Activity {
	private String[] lista = { "Mechanics", "Tools"};
	private ListView lv1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		lv1 =(ListView)findViewById(R.id.lv1);
		
		ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, lista);
		lv1.setAdapter(adapter);
		lv1.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
              Object elegido = lv1.getItemAtPosition(posicion);
              Class<?> activity = null;
              
              if(elegido.equals("Mechanics")){
            	  activity = Mechanics.class;
              }
              if(elegido.equals("Tools")){
            	  activity = Tools.class;
              }
              Intent i = new Intent(SecondActivity.this,activity);
              startActivity(i);
            }
        });

	}
}
