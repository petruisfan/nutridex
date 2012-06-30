package com.mysite.nutridex.db;

import java.io.BufferedReader;
import java.io.IOException;

import com.mysite.nutridex.exceptions.FileFormatException;
import com.mysite.nutridex.util.ResourceUtil;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public abstract class AbstractTable {
	String TABLE_NAME;
	String LABEL_NAME="";
	
	static final String INDEX = "_id";
	static final String NAME_EN = "Name_en";
	static final String KCAL = "Calories";
	static final String PROTEIN = "Protein";
	static final String LIPID = "Lipid";
	static final String CARB = "Carbohidrates";
	
	String TABLE_CREATE;
	String TABLE_DROP;
	static final String TABLE_COLUMNS = NAME_EN + "," + KCAL + "," + PROTEIN + "," + LIPID + "," + CARB;
	
	final int TABLE_COLUMNS_NR = 5;
	
	
	public AbstractTable() {
		TABLE_NAME = this.getClass().getSimpleName();
		
		TABLE_CREATE = "create table " + TABLE_NAME +
				" ( "+ INDEX + " integer primary key autoincrement, " + 
				NAME_EN + " text, " +
				KCAL + " text, " +
				PROTEIN + " text, " +
				LIPID + " text, " +
				CARB + " text) ";
		
		TABLE_DROP  = "drop table if exists " + TABLE_NAME;
	}

	public String createTable() { return TABLE_CREATE; }
	public String dropTable() { return TABLE_DROP; }
	public String getName() { return TABLE_NAME; }
	
	public boolean populateTable(SQLiteDatabase db) {
		try {
			BufferedReader br = ResourceUtil.getBufferedReaderForAsset(TABLE_NAME + ".csv");
			
			String InsertString1 = "INSERT INTO " + TABLE_NAME + " (" + TABLE_COLUMNS + ") values(";
			String InsertString2 = ");";

			db.beginTransaction();
			String data = "";
			while ((data = br.readLine()) != null) {
			    StringBuilder sb = new StringBuilder(InsertString1);
			    String[] sarray = data.split(";");
			    
			    if (sarray.length != TABLE_COLUMNS_NR) {
			    	throw new FileFormatException();
			    }

			    for (String value:sarray) {
			    	value = value.replace("'", " ");
			    	sb.append("'" + value + "',");
			    }
			    sb.deleteCharAt(sb.length()-1);
			    
			    sb.append(InsertString2);
			    db.execSQL(sb.toString());
			}
			db.setTransactionSuccessful();
			db.endTransaction();
			
			return true;
		} catch (IOException e) {
			Log.d(TABLE_NAME, "Error trying to read asset file" + TABLE_NAME + ".csv");
			e.printStackTrace();
		} catch (FileFormatException e) {
			Log.d(TABLE_NAME, "Asset file" + TABLE_NAME + ".csv is not in the correct format.");
			e.printStackTrace();
		}
		return false;
	}
	
	public static final String[] getTableColumns() { return TABLE_COLUMNS.split(","); }
	public abstract String getLabelName();
}
