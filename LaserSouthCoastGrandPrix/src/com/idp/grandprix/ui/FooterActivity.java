package com.idp.grandprix.ui;

import com.idp.grandprix.LaserGrandPrixApp;
import com.idp.grandprix.R;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.app.Activity;
import android.content.Intent;

public class FooterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_footer);
		
		ImageButton imgButton = (ImageButton) findViewById(R.id.btnHome);
		imgButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v){				
				startActivity(new Intent(getBaseContext(), MainActivity.class));
			}
		});
		
		imgButton = (ImageButton) findViewById(R.id.btnCalendar);
		imgButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v){				
				startActivity(new Intent(getBaseContext(), EventActivity.class));
			}
		});
		
		imgButton = (ImageButton) findViewById(R.id.btnResults);
		imgButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v){				
				startActivity(new Intent(getBaseContext(), ResultsActivity.class));
			}
		});
		
		imgButton = (ImageButton) findViewById(R.id.btnSponsors);
		imgButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v){				
				startActivity(new Intent(getBaseContext(), SponsorActivity.class));	// TO DO
			}
		});
		
		imgButton = (ImageButton) findViewById(R.id.btnFacebook);
		imgButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v){				
				startActivity(new Intent(getBaseContext(), FacebookActivity.class));
			}
		});

		imgButton = (ImageButton) findViewById(R.id.btnAbout);
		imgButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v){
				startActivity(new Intent(getBaseContext(), AboutActivity.class));	// TO DO
			}
		});
	}
	
	
	protected class Callback extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url){
			return false;
		}
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
        	
        // Add menu item to updated app
        case R.id.menu_update:
        	new UpdateTask().execute(LaserGrandPrixApp.getController(), this);
        break;
        	
        default:
            return super.onOptionsItemSelected(item);
        }
        
        return true;
 
    }   

	
}
