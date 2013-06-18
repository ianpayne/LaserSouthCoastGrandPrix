package com.idp.grandprix.ui;

import com.idp.grandprix.LaserGrandPrixApp;
import com.idp.grandprix.R;
import com.idp.grandprix.controller.Controller;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends FooterActivity {
	
	private Controller controller;
	private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = this;
        
        //setContentView(R.layout.activity_main);
        ViewGroup vg = (ViewGroup) findViewById(R.id.lldata);
        ViewGroup.inflate(MainActivity.this, R.layout.activity_main, vg);
        
        // get controller
        controller =  LaserGrandPrixApp.getController();  
                
        // move to Application.onCreate

        if (!controller.getUpdated()){
            PromptDialog dlg = new PromptDialog(MainActivity.this, "Updating", "Check for updated series information!") {  
           	 @Override  
           	 public boolean onOkClicked(String input) {  
                    // check for updates and download
                    //Log.e("MainActivity", "checkForUpdates()");
                    new UpdateTask().execute(controller, activity);

           	  return true; // true = close iaLog  
           	 }  
           	};  
           	dlg.show();         	
        }


    }
    
    /*
    
    // checks for updates and performs updates
    private class UpdateTask extends AsyncTask<String, String, String>{
    	protected String doInBackground(String...url){
             String message = "";
             if (controller.checkForUpdates()){ 
            	 //showToastFromBackground("Update available, performing update.");
            	 publishProgress("Update available, performing update.");
            	 if (controller.performUpdate()){
            		 controller.setUpdated(true);
            		 controller.loadCSVEvents();
            		 //showToastFromBackground("Series information updated.");
            		 publishProgress("Series information updated.");
            	 }
            	 else{
            		 //showToastFromBackground("Series information has NOT been updated.");
            		 publishProgress("Series information has NOT been updated.");
            	 }
             }
             else {
            	 controller.setUpdated(true);
            	 //showToastFromBackground("Series information is up todate");
            	 publishProgress("Series information is up todate");
             }
             return message;
    	}
    	protected void onProgressUpdate(String...progress){
    		super.onProgressUpdate(progress);
    		showToastFromBackground(progress[0]);
    	}
    	protected void onPostExecute(String result){
    		
    	}
    }     

    private void showToastFromBackground(final String message) {
    runOnUiThread(new Runnable() {

        @Override
        public void run() {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
        }
    });
    
}
    */

   
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
	
	public void toggleText(View v){
		TextView more = (TextView) findViewById(R.id.txtMore);
		TextView less = (TextView) findViewById(R.id.txtLess);
		TextView main = (TextView) findViewById(R.id.txtMain);
		Button email = (Button) findViewById(R.id.btnEmailCoordinator);
		TextView coordinator = (TextView) findViewById(R.id.txtCoordinator);	
		
		if (more.getVisibility() == TextView.VISIBLE){
			more.setVisibility(TextView.GONE);
			less.setVisibility(TextView.VISIBLE);
			main.setVisibility(TextView.VISIBLE);
			email.setVisibility(TextView.VISIBLE);
			coordinator.setVisibility(TextView.VISIBLE);	
		}
		else {
			more.setVisibility(TextView.VISIBLE);
			less.setVisibility(TextView.GONE);
			main.setVisibility(TextView.GONE);
			email.setVisibility(TextView.GONE);
			coordinator.setVisibility(TextView.GONE);
		}
			
	}
	
	public void emailCoordinator(View v){
		String subject = "Laser South Coast Grand Prix";
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
 
    }    	
*/
}
