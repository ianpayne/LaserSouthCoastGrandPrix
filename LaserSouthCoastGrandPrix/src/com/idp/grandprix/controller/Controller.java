package com.idp.grandprix.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import java.util.*;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import com.idp.grandprix.LaserGrandPrixApp;
import com.idp.grandprix.model.Club;
import com.idp.grandprix.model.Contact;
import com.idp.grandprix.model.Event;


import android.content.Context;
import android.content.res.AssetManager;
import android.location.Address;
import android.os.Environment;
//import android.util.Log;
import android.widget.Toast;

/*
 * Split into separate controllers
 * 
 * Controller
 * 
 * 
 * NetworkController
 *  - checkForUpdate
 *  - performUpdate
 *  - getEventsVersion
 *  
 * fileController
 *  - copyAssets
 *  - copyFile
 *  - changeVersionNo
 *  - loadCSVFile
 *  
 * 
 */

public class Controller {
	private Context context;
	private static Controller controller;
	
	private static final int SIZE = 20;
	
	// move this to a model object?
	private Address addresses[];
	private Club clubs[];
	private Contact contacts[];
	private Event events[];
	
	private boolean updated = false;
	private int eventsVersion = 0;
	private int onlineVersion = 0;
	
	private static final String DIRECTORY="/Android/data/com.idp.grandprix/data/";
	private static final String FILENAME="events.csv";
	private static final String VERSION="version.txt";
	
	private Controller(Context context){	
		this.context = context;
		setup();
	}
	
	public static Controller createController(Context context){
		if (controller == null){
			controller = new Controller(context);
		}
		
		return controller; 
	}
	
	public Address[] getAddresses(){
		return addresses;
	}
	public Club[] getClubs(){
		return clubs;
	}
	public Contact[] getContacts(){
		return contacts;
	}
	public Event[] getEvents(){
		return events;
	}
	public Context getContext(){
		return context;
	}
	public boolean getUpdated(){
		return updated;
	}
	public void setUpdated(boolean updated){
		this.updated = updated;
	}
	
	//Helper methods
	private void setup(){
		//Log.e("SETUP", Environment.getExternalStoragePublicDirectory(DIRECTORY) + java.io.File.separator + FILENAME);
		//Log.e("SETUP", Environment.getDataDirectory() + java.io.File.separator + FILENAME);
		//check for file on SDCARD
		
		
		
        File file = new File(Environment.getExternalStorageDirectory() + DIRECTORY, FILENAME);
        File versionFile = new File(Environment.getExternalStorageDirectory() + DIRECTORY, VERSION );
        if (!file.exists()) {
        	//Log.e("SETUP", "CSV File doesn't exist, so copying");
        	this.copyAssets();  // replace with copying only the csv file
        }
        else if (!versionFile.exists()) {
        	//Log.e("SETUP", "Version files doesn't exist, so copying");
        	this.copyAssets();  // replace with copying only the version file
        }
        else {
        	//Log.e("SETUP", "File exists, so not copying");
        }
        
		//load csv file from sdcard and populate arrays
        //Log.e("SETUP", "loadCSVEvents()");
        loadCSVEvents();
                


		
	}
	

    private void copyAssets() { 
        AssetManager assetManager = context.getAssets(); 
        String[] files = null; 
        try { 
            files = assetManager.list(""); 
        } catch (IOException e) { 
            //Log.e("Assets Tag", e.getMessage()); 
        } 
        for(String filename : files) { 
            InputStream in = null; 
            OutputStream out = null; 
            try { 
	              in = assetManager.open(filename);
	              
	              File directory = new File(Environment.getExternalStorageDirectory() + DIRECTORY, filename );
	              if (!directory.exists()){
	                directory.getParentFile().mkdirs();
	                //Log.e("File stream tag", "Make directory" + directory.getName()); 
	              }

	              File f = new File(Environment.getExternalStorageDirectory() + DIRECTORY, filename );
	              if (!f.exists()){
	            	  f.createNewFile();
	            	  //Log.e("File stream tag", "create file" + f.getName()); 
	              }
	              
	              
	              out = new FileOutputStream(Environment.getExternalStorageDirectory() + DIRECTORY + filename); 

	              copyFile(in, out); 
	              in.close(); 
	              in = null; 
	              out.flush(); 
	              out.close(); 
	              out = null; 
            } catch(Exception e) { 
            	System.out.println("Caught Exception " + e);
               // Log.e("File stream tag", e.getMessage()); 
            }        
        } 
    } 
    private void copyFile(InputStream in, OutputStream out) throws IOException { 
        byte[] buffer = new byte[1024]; 
        int read; 
        while((read = in.read(buffer)) != -1){ 
          out.write(buffer, 0, read); 
        } 
    }
    
    public boolean checkForUpdates(){
    	boolean updateAvailable = false;

    		    	HttpClient httpclient = new DefaultHttpClient();
    		    	HttpGet httpget = new HttpGet("http://www.southcoastgrandprix.co.uk/check_for_updates.php");
    		    	try {
    		    	    HttpResponse response = httpclient.execute(httpget);
    		    	    if(response != null) {
    		    	        String line = "";
    		    	        InputStream inputstream = response.getEntity().getContent();
    		    	        line = convertStreamToString(inputstream);
    		    	        
    		    	        onlineVersion = Integer.parseInt(line);
    		    	        //Log.e("checkForUpdates", "returnedValue: " + onlineVersion);
    		    	           		    	        
    		    	        eventsVersion = getEventsVersion();    		    	        
    		    	        //Log.e("checkForUpdates", "Version number: " + eventsVersion);
    		    	        
    		    	        if (eventsVersion < onlineVersion){
    		    	        	// perform update
    		    	        	//Log.e("checkForUpdates", "perform update: ");
    		    	        	
    		    	        	//performUpdate();
    		    	        	
    		    	        	//Log.e("checkForUpdates", "save new version number: ");
    		    	        	
    		    	        	//changeVersionNo(returnedValue);
    		    	            
    		    	            //loadCSVEvents();
    		    	            
    		    	            updateAvailable = true;
    		    	        	
    		    	        } else {
    		    	        	//Log.e("checkForUpdates", "up todate: ");
    		    	        }
    		    	        
    		    	    } else {
    		    	        //Log.e("checkForUpdates", "Unable to complete your request");
    		    	    }
    		    	} catch (ClientProtocolException e) {
    		    		System.out.println("Caught ClientProtocolException " + e);
    		    	    //Log.e("checkForUpdates", "Caught ClientProtocolException" + e);
    		    	} catch (IOException e) {
    		    		System.out.println("Caught IOException " + e);
    		    	    //Log.e("checkForUpdates", "Caught IOException" + e);
    		    	} catch (Exception e) {
    		    		System.out.println("Caught Exception " + e);
    		    	    //Log.e("checkForUpdates", "Caught Exception" + e);
    		    	}
   	
    		    	
    		    

      	  
      	  return updateAvailable;
   	
    }
    
    public boolean performUpdate(){
 
			    	// call php file on website
			    	// return is csv file
			    	// save to sdcard
    	boolean success = false;
		try {
			    	HttpClient httpClient = new DefaultHttpClient();
			    	HttpContext localContext = new BasicHttpContext();
			    	HttpGet httpGet = new HttpGet("http://www.southcoastgrandprix.co.uk/files/AndroidEvents.csv");
			    	HttpResponse response;

					response = httpClient.execute(httpGet, localContext);

			    	InputStream in = response.getEntity().getContent(); 
			        OutputStream out = null; 
			        out = new FileOutputStream(Environment.getExternalStoragePublicDirectory(DIRECTORY) + java.io.File.separator + FILENAME); 
			        copyFile(in, out);
			        in.close(); 
			        in = null; 
			        out.flush(); 
			        out.close(); 
			        out = null;
			        success = true;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
		if (success){
			changeVersionNo(onlineVersion);
		}
		
		
		return success;
    
    }
    
    public void changeVersionNo(int newVersionNo){

		try {
	    	File f = new File(Environment.getExternalStoragePublicDirectory(DIRECTORY) + java.io.File.separator + VERSION);
	    	FileOutputStream fis = new FileOutputStream(f);
	    	DataOutputStream dis = new DataOutputStream(fis);
	    	BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(dis));
	    	writer.write(String.valueOf(newVersionNo));
	    	writer.flush();
	    	writer.close();
	    	dis.close();
	    	fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    private int getEventsVersion(){
    	int version = 0;
    	try {
			
			File f = new File(Environment.getExternalStoragePublicDirectory(DIRECTORY) + java.io.File.separator + VERSION);
			FileInputStream fis = new FileInputStream(f);
			DataInputStream dis = new DataInputStream(fis);
			BufferedReader reader = new BufferedReader(new InputStreamReader(dis));

			
	        // do reading, usually loop until end of file reading  
	        String mLine = reader.readLine();

	        if (mLine != null){
	        	version = Integer.parseInt(mLine);
	        }

	        reader.close();			
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    	return version;
	
    }
    
    private String convertStreamToString(InputStream is) {
        String line = "";
        StringBuilder total = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        try {
            while ((line = rd.readLine()) != null) {
                total.append(line);
            }
        } catch (Exception e) {
            Toast.makeText(LaserGrandPrixApp.getContext(), "Stream Exception", Toast.LENGTH_SHORT).show();
        }
        return total.toString();
    }
    
    /*
     * Reads in data from csv file on sdcard
     * 
     *  needs to be split into three
     */
    
    public void loadCSVEvents(){
    	
		Address tempAddresses[] = new Address[SIZE];
		Club tempClubs[] = new Club[SIZE];
		Contact tempContacts[] = new Contact[SIZE];
		Event tempEvents[] = new Event[SIZE];
	

    	try  {
    		
    		File f = new File(Environment.getExternalStorageDirectory() + DIRECTORY + FILENAME);
    		FileInputStream fis = new FileInputStream(f);
    		DataInputStream dis = new DataInputStream(fis);
    		BufferedReader reader = new BufferedReader(new InputStreamReader(dis));
    		
	    	int addressCount = 0;
	    	int clubCount = 0;
	    	int contactCount = 0;
	    	int eventCount = 0;
	    	
    		try {
    			
		        // do reading, usually loop until end of file reading  
		        String mLine = reader.readLine();
		        while (mLine != null) {
		        	// process csv file
		        	
		        	String[] fields = mLine.split(",", -1);
		        	
		        	if (fields == null){
		        		throw new Exception("empty line");
		        	}
		        	//Log.e("CSV", fields[1]);
		        	
		        	if (fields[0].contains("Address")){
		        		tempAddresses[addressCount] = createAddress(fields[1],fields[2],fields[3],fields[4],fields[5],fields[6],fields[7],fields[8],Double.valueOf(fields[9]),Double.valueOf(fields[10]));
		        		addressCount++;
		        	} else if (fields[0].contains("Club")) {
		        		Address address = findAddress(fields[1], tempAddresses);
		        		tempClubs[clubCount] = new Club(fields[1],fields[2],address,fields[4],fields[5],fields[6]);
		        		clubCount++;
		        	} else if (fields[0].contains("Contact")) {
		        		tempContacts[contactCount] = new Contact(fields[1],fields[2],fields[3],fields[4]);
		        		contactCount++;
		        	} else if (fields[0].contains("Event")) {
		        		Club club = findClub(fields[1], tempClubs);
		        		Address address = findAddress(fields[1], tempAddresses);
		        		Contact contact = findContact(fields[1], tempContacts);	
		        		tempEvents[eventCount] = new Event(fields[1], club, address, contact, fields[4],fields[5],fields[6],fields[7],fields[8],fields[9]);
		        		eventCount++;
		        	}
		        	
		        	mLine = reader.readLine(); 
		        }
		        
		        addresses = new Address[addressCount+1];
		        clubs = new Club[clubCount+1];
		        contacts = new Contact[contactCount+1];
		        events = new Event[eventCount+1];
		        
		        addresses = Arrays.copyOf(tempAddresses, addressCount);
		        clubs = Arrays.copyOf(tempClubs, clubCount);
		        contacts = Arrays.copyOf(tempContacts, contactCount);
		        events = Arrays.copyOf(tempEvents, eventCount);
    			
    		} finally {
    			reader.close();	
    		}
        		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
      	
    }
    
	private Address createAddress(String id, String addr1, String addr2, String addr3, String addr4, String county, String postCode, String phone, double latitude, double longitude){
		Address address = new Address(Locale.UK);
		address.setFeatureName(id);
		address.setAddressLine(0, addr1);
		address.setAddressLine(1, addr2);
		address.setAddressLine(2, addr3);
		address.setAddressLine(3, addr4);
		address.setAddressLine(4, county);
		address.setPostalCode(postCode);
		address.setPhone(phone);
		address.setLatitude(latitude);
		address.setLongitude(longitude);
			
		return address;
	}
    
    private Address findAddress(String id, Address[] addresses){
    	int count = 0;
    	while (count < addresses.length){
    		if (addresses[count].getFeatureName().contains(id)){
    			return addresses[count];
    		}
    		count++;
    	}    	
    	return null;
    }
    private Club findClub(String id, Club[] clubs){
    	int count = 0;
    	while (count < clubs.length){
    		if (clubs[count].getId().contains(id)){
    			return clubs[count];
    		}
    		count++;
    	}    	
    	return null;
    }
    private Contact findContact(String id, Contact[] contacts){
    	int count = 0;
    	while (count < contacts.length){
    		if (contacts[count].getId().contains(id)){
    			return contacts[count];
    		}
    		count++;
    	}    	
    	return null;
    }    
}
