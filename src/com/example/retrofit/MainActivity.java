package com.example.retrofit;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.retrofit.APIConnector;
import com.example.retrofit.MainActivity;
import com.example.retrofit.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private EditText campo_usuario, campo_password;
	private Button iniciar_sesion;
	private String result;
	private StorageDAO db;
	JSONObject obj = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		db = new StorageDAO(this);
		campo_usuario = (EditText) findViewById(R.id.editUsuario);
		campo_password = (EditText) findViewById(R.id.editPassword);
		iniciar_sesion = (Button) findViewById(R.id.buttonIniciar);
		
		iniciar_sesion.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				String username=campo_usuario.getText().toString();
		        String password=campo_password.getText().toString();
		        
		        try {
					login(username,password);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		        if(result != "false"){
		        try {
					updateDataMechanics(username,password);
					updateDataTools(username,password);
				} catch (Exception e) {
					e.printStackTrace();
				}
		       
				Intent i = new Intent(MainActivity.this,SecondActivity.class);
				startActivity(i);
		        }
		        else{
		        	 Toast error = Toast.makeText(getApplicationContext(),"Usuario y/o Password incorrecto", Toast.LENGTH_LONG);
		        	 error.show();
		        }
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void login(String username,String password) throws Exception{
		
		String resultado = APIConnector.auth(username,password);
		JSONObject jObj = null;
		jObj = new JSONObject(resultado);
		result = jObj.getString("success");
	}

	public void updateDataMechanics(String username, String password) throws Exception{
		
		String mechanics = APIConnector.getMechanics(username,password);
		JSONArray jsonArray = new JSONArray(mechanics);
        
		db.open();
    	db.deleteOldMechanics();
    	
		for (int i=0;i<jsonArray.length();i++){ 
         obj = new JSONObject(jsonArray.get(i).toString());
         db.insertMechanic(Integer.parseInt(obj.get("id").toString()),obj.get("code").toString(), obj.get("name").toString(),Integer.parseInt(obj.get("status").toString()));
        }	
		
        db.close();
	}
	
public void updateDataTools(String username, String password) throws Exception{
		
		String tools = APIConnector.getTools(username,password);
		JSONArray jsonArray = new JSONArray(tools);
        
		db.open();
    	db.deleteOldTools();
    	
		for (int i=0;i<jsonArray.length();i++){ 
         obj = new JSONObject(jsonArray.get(i).toString());
         db.insertTool(Integer.parseInt(obj.get("id").toString()),obj.get("code").toString(), obj.get("name").toString());
        }	
		
        db.close();
	}
}
