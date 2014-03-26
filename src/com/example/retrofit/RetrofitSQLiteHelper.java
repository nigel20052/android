package com.example.retrofit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class RetrofitSQLiteHelper extends SQLiteOpenHelper {
	
	//Sentencia SQL para crear las tablas de Usuarios
    String mechanics = "CREATE TABLE mechanics (id INT PRIMARY KEY, code TEXT,name TEXT, status INT)";
    String tools = "CREATE TABLE tools (id INT PRIMARY KEY, code TEXT,name TEXT)";
    
	public RetrofitSQLiteHelper(Context context, String name,CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(mechanics);
		db.execSQL(tools);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		 //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS mechanics");
        db.execSQL("DROP TABLE IF EXISTS tools");
 
        //Se crea la nueva versión de la tabla
        db.execSQL(mechanics);
        db.execSQL(tools);
		
	}

}
