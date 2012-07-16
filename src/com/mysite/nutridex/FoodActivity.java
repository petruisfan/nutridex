package com.mysite.nutridex;

import com.mysite.nutridex.db.DatabaseAdapter;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class FoodActivity extends Activity {

	private DatabaseAdapter dbAdapter;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.food_list);
        setContentView(R.layout.food_table);
        
        dbAdapter = new DatabaseAdapter(this);
        dbAdapter.open();
        
        fillData();
    }

    private void fillData() {
    	Bundle extras = getIntent().getExtras();
    	int tableNr = extras.getInt(getString(R.string.table));
    	Cursor notesCursor = dbAdapter.fetchTable(tableNr);
    	
    	fillHeaders();
    	fillTable(notesCursor);
//    	fillList(notesCursor);
    }

	private void fillTable(Cursor cursor) {
		cursor.moveToFirst();

		TableLayout tl = (TableLayout) findViewById(R.id.food_table);
		
		TableRow row;
		while (cursor.isAfterLast() == false) {
			row = new TableRow(getApplicationContext());
		    
			int cols = cursor.getColumnCount();
			
			for (int i=1; i<cols; i++ ) {
				String value = cursor.getString(i);
				TextView cell = new TextView(getApplicationContext());
				cell.setText(value);
				
				row.addView(cell);
			}
			tl.addView(row);
			
		    cursor.moveToNext();
		}
	}

//	private void fillList(Cursor notesCursor) {
//		// Get all of the rows from the database and create the item list
//        startManagingCursor(notesCursor);
//
//        // Create an array to specify the fields we want to display in the list (only TITLE)
//        String[] from = AbstractTable.getTableColumns();
//        
//        // and an array of the fields we want to bind those fields to (in this case just text1)
//        int[] to = new int[]{R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5,};
//
//        // Now create a simple cursor adapter and set it to display
//        SimpleCursorAdapter notes = new SimpleCursorAdapter(this, R.layout.food_row, notesCursor, from, to);
//        
//        setListAdapter(notes);
//	}

	private void fillHeaders() {
		String[] headersText = getResources().getStringArray(R.array.columns);
		
		((TextView)findViewById(R.id.column0)).setText(headersText[0]);
		((TextView)findViewById(R.id.column1)).setText(headersText[1]);
		((TextView)findViewById(R.id.column2)).setText(headersText[2]);
		((TextView)findViewById(R.id.column3)).setText(headersText[3]);
		((TextView)findViewById(R.id.column4)).setText(headersText[4]);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()){
		case R.id.about:
			Toast.makeText(getApplicationContext(),
        			"Nutridex 2012 (c) All rights reserved" , Toast.LENGTH_LONG)
        			.show();
		}
		return super.onOptionsItemSelected(item);
	}
}
