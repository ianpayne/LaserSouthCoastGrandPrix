package com.idp.grandprix.ui;


import com.idp.grandprix.R;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

public class AboutActivity extends FooterActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_about);

        ViewGroup vg = (ViewGroup) findViewById(R.id.lldata);
        ViewGroup.inflate(AboutActivity.this, R.layout.activity_about, vg);
	}

	public void emailDeveloper(View v){
		String subject = "Laser South Coast Grand Prix App";
		String message = "Your Laser South Coast Grand Prix application is fantastic.";
		String email = "ian.payne@btinternet.com"; // event.getContact().getEmail();
		
		final Intent emailIntent = new Intent(Intent.ACTION_SEND); 
		emailIntent.setType("text/plain"); 
		emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email}); 
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject); 
		emailIntent.putExtra(Intent.EXTRA_TEXT, message); 
		startActivity(Intent.createChooser(emailIntent, "Send mail..."));
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
 
    } */  
}
