package com.idp.grandprix;

import android.os.AsyncTask;
import android.os.Bundle;
import android.content.Intent;
//import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FooterActivity {
	
	private Controller controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
        //setContentView(R.layout.activity_main);
        ViewGroup vg = (ViewGroup) findViewById(R.id.lldata);
        ViewGroup.inflate(MainActivity.this, R.layout.activity_main, vg);
        
        // set model
        controller =  LaserGrandPrixApp.getController();  
                
        if (!controller.getUpdated()){
            PromptDialog dlg = new PromptDialog(MainActivity.this, "Updating", "Check for updated series information!") {  
           	 @Override  
           	 public boolean onOkClicked(String input) {  
                    // check for updates and download
                    //Log.e("MainActivity", "checkForUpdates()");
                    new UpdateTask().execute("");

           	  return true; // true = close diaLog  
           	 }  
           	};  
           	dlg.show();         	
        }


    }
    
    private class UpdateTask extends AsyncTask<String, Void, String>{
    	protected String doInBackground(String...url){
             String message = "";
             if (controller.checkForUpdates()){ 
            	 showToastFromBackground("Update available, performing update.");
            	 if (controller.performUpdate()){
            		 controller.setUpdated(true);
            		 controller.loadCSVEvents();
            		 showToastFromBackground("Series information updated.");
            	 }
            	 else{
            		 showToastFromBackground("Series information has NOT been updated.");
            	 }
             }
             else {
            	 controller.setUpdated(true);
            	 showToastFromBackground("Series information is up todate");
             }
             return message;
    	}
    	protected void onProgressUpdate(Void...progress){
    		
    	}
    	protected void onPostExecute(String result){
    		
    	}
    }     
    /*
    private class checkForUpdateTask extends AsyncTask<String, Void, Boolean>{
    	protected Boolean doInBackground(String...url){
    	
             boolean requiresUpdating = model.checkForUpdates();
             if (requiresUpdating){  
                 return Boolean.TRUE;
             }
             else {
            	 return Boolean.FALSE;
             }
    	}
    	protected void onProgressUpdate(Void...progress){
    		
    	}
    	protected void onPostExecute(Boolean result){
    		if (result){
    			showToastFromBackground("Requires update");
    			new UpdateTask().execute("");
    		}
    		else {
    			showToastFromBackground("Up to date");
    		}
    		
    	}
    }    
    private class UpdateTask extends AsyncTask<String, Void, Boolean>{
    	protected Boolean doInBackground(String...url){
    	
             boolean success = model.performUpdate();
             if (success){
                 model.setUpdated(true);  
                 return Boolean.TRUE;
             }
             else {
            	 return Boolean.FALSE;
             }

    	}
    	protected void onProgressUpdate(Void...progress){
    		
    	}
    	protected void onPostExecute(Boolean result){
            if (result){  
                // display message
                showToastFromBackground("Updated!");
            }
            else {
           	 showToastFromBackground("Not updated!");
            }    		
    	}
    }
    */
    private void showToastFromBackground(final String message) {
    runOnUiThread(new Runnable() {

        @Override
        public void run() {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
        }
    });
}
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
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
