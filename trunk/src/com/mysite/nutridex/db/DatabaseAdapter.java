package com.mysite.nutridex.db;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseAdapter extends SQLiteOpenHelper{
	private static final String TAG = DatabaseAdapter.class.getSimpleName();
	
	private static final String DATABASE_NAME = "Nutridex";
	private static final int DATABASE_VERSION = 4;
	
	ArrayList<AbstractTable> categories = new ArrayList<AbstractTable>();

	private SQLiteDatabase database;

	
	public DatabaseAdapter(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}


	public void open() {
		categories.add(new MilkAndEgg());
		categories.add(new Meat());
		categories.add(new Vegetables());
	
		database = getWritableDatabase();
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d(TAG, "Creating database. ");
		
		for (AbstractTable table: categories) {
			Log.d(TAG, table.createTable());
			db.execSQL(table.createTable());
			
			table.populateTable(db);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.d(TAG, "Upgrading (dropping) database. ");
		
		for (AbstractTable table: categories) {
			Log.d(TAG, table.dropTable());
			db.execSQL(table.dropTable());
		}
		
		onCreate(db);
	}
	
	public ArrayList<String> tableList() {
		ArrayList<String> result = new ArrayList<String>();

		for (AbstractTable table: categories) {
			result.add(table.getName());
		}
		return result;
	}


	public Cursor fetchTable(int tableNr) {
		Cursor c = database.query(categories.get(tableNr).getName(),
//				AbstractTable.getTableColumns(),
				null,
				null,
				null,
				null,
				null,
				null);
		return c;
	}
}





