package com.example.retrofit;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.retrofit.APIConnector;
import com.example.retrofit.MainActivity;
import com.example.retrofit.R;

import android.os.AsyncTask;
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
	private StorageDAO db;
	JSONObject obj = null;
	private String username;
	private String password;
	
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
		        new APIConnectorLoginTask().execute(username,password);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void updateDataMechanics(String username, String password) {
			String mechanics;
			try {
				mechanics = APIConnector.getMechanics(username,password);
				JSONArray jsonArray = new JSONArray(mechanics);
				db.open();
				db.deleteOldMechanics();
				for (int i=0;i<jsonArray.length();i++){ 
					obj = new JSONObject(jsonArray.get(i).toString());
					db.insertMechanic(Integer.parseInt(obj.get("id").toString()),obj.get("code").toString(), obj.get("name").toString(),Integer.parseInt(obj.get("status").toString()));
        		}	
				db.close();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public void updateDataTools(String username, String password){
			try {
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
			catch (Exception e) {
				e.printStackTrace();
			}
	}

	private class APIConnectorLoginTask extends AsyncTask<String,String,String> {
	
		@Override
		protected String doInBackground(String... params) {
			String result;
			try {
				username = params[0];
				password = params[1];
				String resultado = APIConnector.auth(params[0], params[1]);
				JSONObject jObj = null;
				jObj = new JSONObject(resultado);
				result = jObj.getString("success");
			} catch (Exception e) {
				e.printStackTrace();
				result = "false";
			}
			return result;
		}
	
		protected void onPostExecute(String result) {
			if(result.equals("true")){
				new APIConnectorCatalogsTask().execute("mechanics");
				new APIConnectorCatalogsTask().execute("tools");
        		Intent i = new Intent(MainActivity.this,SecondActivity.class);
        		startActivity(i);
			} else {
				Toast error = Toast.makeText(getApplicationContext(),"Usuario y/o Password incorrecto", Toast.LENGTH_LONG);
            	error.show();
			}
    	}//end post

	}//end inner class
	
	private class APIConnectorCatalogsTask extends AsyncTask<String,String,Void> {
		
		@Override
		protected Void doInBackground(String... params) {
				if ("mechanics".equals(params[0])){
					updateDataMechanics(username,password);
				}
				if ("tools".equals(params[0])){
					updateDataTools(username,password);
				}
			return null;
		}
	}
	
	

}
