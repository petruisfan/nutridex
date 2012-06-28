package com.mysite.nutridex;

import com.mysite.nutridex.db.AbstractTable;
import com.mysite.nutridex.db.DatabaseAdapter;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class FoodActivity extends ListActivity {

	private DatabaseAdapter dbAdapter;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_list);
        dbAdapter = new DatabaseAdapter(this);
        dbAdapter.open();
        fillData();
    }

    private void fillData() {
    	Bundle extras = getIntent().getExtras();
    	int tableNr = extras.getInt("TABLE");
    	Cursor notesCursor = dbAdapter.fetchTable(tableNr);

    	
    	fillHeaders();
    	
        // Get all of the rows from the database and create the item list
        startManagingCursor(notesCursor);

        // Create an array to specify the fields we want to display in the list (only TITLE)
        String[] from = AbstractTable.getTableColumns();
        
        // and an array of the fields we want to bind those fields to (in this case just text1)
        int[] to = new int[]{R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5,};

        // Now create a simple cursor adapter and set it to display
        SimpleCursorAdapter notes = new SimpleCursorAdapter(this, R.layout.food_row, notesCursor, from, to);
        
        setListAdapter(notes);
    }

	private void fillHeaders() {
		String[] headersText = getResources().getStringArray(R.array.columns);
		
		((TextView)findViewById(R.id.column0)).setText(headersText[0]);
		((TextView)findViewById(R.id.column1)).setText(headersText[1]);
		((TextView)findViewById(R.id.column2)).setText(headersText[2]);
		((TextView)findViewById(R.id.column3)).setText(headersText[3]);
		((TextView)findViewById(R.id.column4)).setText(headersText[4]);
	}

}
