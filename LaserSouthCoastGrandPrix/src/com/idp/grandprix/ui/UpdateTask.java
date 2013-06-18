
package com.idp.grandprix.ui;
import com.idp.grandprix.controller.Controller;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

   // checks for updates and performs updates
    public class UpdateTask extends AsyncTask<Controller, String, String>{
    	protected Controller controller;
    	protected Activity activity;
    	
    	protected String doInBackground(Controller...controllers){
             String message = "";
             controller = controllers[0];
             //context = controller.getContext();
             
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
            	 publishProgress("Series information is up to date");
             }
             return message;
    	}
    	
		public void execute(Controller controller2, Activity act) {
			activity = act;
			super.execute(controller2);
			
			
		} 
    	
    	protected void onProgressUpdate(String...progress){
    		super.onProgressUpdate(progress);
    		showToastFromBackground(progress[0]);
    	}
    	protected void onPostExecute(String result){
    		
    	}
    	
        private void showToastFromBackground(final String message) {
            activity.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(activity.getBaseContext(), message, Toast.LENGTH_LONG).show();
                }
            });
        }
        
        

    }

