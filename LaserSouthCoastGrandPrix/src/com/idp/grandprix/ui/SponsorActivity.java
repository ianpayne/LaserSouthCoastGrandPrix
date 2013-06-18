package com.idp.grandprix.ui;


import com.idp.grandprix.R;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

public class SponsorActivity extends FooterActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_sponsor);
        ViewGroup vg = (ViewGroup) findViewById(R.id.lldata);
        ViewGroup.inflate(SponsorActivity.this, R.layout.activity_sponsor, vg);
	}
	
	public void roosterWebsite(View v){
		Intent intent = new Intent(getBaseContext(), WebActivity.class);
		intent.putExtra("url", "http://www.roostersailing.com");
		intent.putExtra("title", "Rooster Sailing");
		
		startActivity(intent);		
	}
	
	public void wildwindWebsite(View v){
		Intent intent = new Intent(getBaseContext(), WebActivity.class);
		intent.putExtra("url", "http://www.wildwind.co.uk");
		intent.putExtra("title", "Wildwind Sailing Holidays");
		
		startActivity(intent);		
	}
	/*
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
 
    }   */
}
