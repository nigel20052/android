package com.example.retrofit;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class StorageDAO {
	private SQLiteDatabase db;
    private RetrofitSQLiteHelper dbHelper;
    
    public StorageDAO(Context context) {
        dbHelper = new RetrofitSQLiteHelper(context, "retrofit", null, 1);
    }
 
    public void open() {
        db = dbHelper.getWritableDatabase();
    }
 
    public void close() {
        dbHelper.close();
    }
    
    public void deleteOldMechanics(){
    	db.execSQL("DELETE FROM mechanics WHERE 1");
    }
    public void deleteOldTools(){
    	db.execSQL("DELETE FROM tools WHERE 1");
    }
    
    public void insertMechanic(int id,String code, String name, int status){
    	db.execSQL("INSERT INTO mechanics (id,code,name,status ) " +
                "VALUES (" + id + ", '" + code +"', '"+ name +"', "+ status +")");
    }
    
    public void insertTool(int id,String code, String name){
    	db.execSQL("INSERT INTO tools (id,code,name ) " +
                "VALUES (" + id + ", '" + code +"', '"+ name +"')");
    }
    
    public List<Mechanic> getAllMechanics() {
		List<Mechanic> mechanics = new ArrayList<Mechanic>();

		String[] campos = new String[] {"id","code", "name","status"};
		 
		Cursor cursor = db.query(false, "mechanics", campos, null, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Mechanic mechanic = parseMechanic(cursor);
			mechanics.add(mechanic);
			cursor.moveToNext();
		}

		cursor.close();
		return mechanics;
	}
    
    private Mechanic parseMechanic(Cursor cursor) {
    	        Mechanic mechanic = new Mechanic();
    	        
    	        mechanic.setCode(cursor.getString(1));
    	        mechanic.setName(cursor.getString(2));
    	        mechanic.setStatus(cursor.getInt(3));
    	        return mechanic;
  	}
    
    public List<Tool> getAllTools() {
		List<Tool> tools = new ArrayList<Tool>();

		String[] campos = new String[] {"id","code", "name"};
		 
		Cursor cursor = db.query(false, "tools", campos, null, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Tool tool = parseTool(cursor);
			tools.add(tool);
			cursor.moveToNext();
		}

		cursor.close();
		return tools;
	}
    
    private Tool parseTool(Cursor cursor) {
        Tool tool = new Tool();
        
        tool.setCode(cursor.getString(1));
        tool.setName(cursor.getString(2));
 
        return tool;
}

}
