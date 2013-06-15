package com.idp.grandprix;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;



public class EventActivity extends FooterActivity {
	private ListView listView1;
	private Controller controller;

	private Event events[];
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.openeventlistview);
		
        ViewGroup vg = (ViewGroup) findViewById(R.id.lldata);
        ViewGroup.inflate(EventActivity.this, R.layout.openeventlistview, vg);
        
        // set model
        controller =  LaserGrandPrixApp.getController();      
                    
        // get Events
        events = controller.getEvents();
	
        //display events in list
        OpenEventAdapter adapter = new OpenEventAdapter(this, R.layout.listview_item_row, events);
              
        listView1 = (ListView) findViewById(R.id.listView1);
        
        View header = (View) getLayoutInflater().inflate(R.layout.listview_header, null);
        listView1.addHeaderView(header);
       
        listView1.setAdapter(adapter);		

        listView1.setClickable(true);

	}
	

	

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
 
        switch (item.getItemId())
        {
        case R.id.menu_home:
        	startActivity(new Intent(getBaseContext(), MainActivity.class));
            break;
 
        case R.id.menu_calendar:
        	startActivity(new Intent(getBaseContext(), EventActivity.class));
            break;
 
        case R.id.menu_results:
        	startActivity(new Intent(getBaseContext(), ResultsActivity.class));
        	break;
 
        case R.id.menu_sponsors:
        	startActivity(new Intent(getBaseContext(), SponsorActivity.class));
        	break;
 
        case R.id.menu_facebook:
        	startActivity(new Intent(getBaseContext(), FacebookActivity.class));
        	break;
 
        case R.id.menu_about:
        	startActivity(new Intent(getBaseContext(), AboutActivity.class));
        	break;
        	
        default:
            return super.onOptionsItemSelected(item);
        }
        
        return true;
 
    }    
}
