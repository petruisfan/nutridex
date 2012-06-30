package com.mysite.nutridex;

import java.util.ArrayList;

import com.mysite.nutridex.db.DatabaseAdapter;
import com.mysite.nutridex.util.ResourceUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        
        ResourceUtil.setResources(getResources());	// set the resources so that resource utils can acces them.
        
        ListView listView = (ListView) findViewById(R.id.listView);
        
        DatabaseAdapter dbAdapter = new DatabaseAdapter(getApplicationContext());
        dbAdapter.open();
        
        ArrayList<String> categories = dbAdapter.tableList();
        dbAdapter.close();
        
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categories);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        		Toast.makeText(getApplicationContext(),
//        			"Click ListItem Number " + position, Toast.LENGTH_LONG)
//        			.show();
        		Intent intent = new Intent(getApplicationContext(), FoodActivity.class);
        		intent.putExtra(getString(R.string.table), position);
        		startActivity(intent);
        		
        		
        	}
        });
    }
}