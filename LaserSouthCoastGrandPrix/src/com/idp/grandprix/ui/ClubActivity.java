package com.idp.grandprix.ui;

import com.idp.grandprix.R;
import com.idp.grandprix.model.Event;

import android.location.Address;
import android.net.Uri;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.telephony.TelephonyManager;

public class ClubActivity extends FooterActivity {
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_club);
		context = getBaseContext();

        ViewGroup vg = (ViewGroup) findViewById(R.id.lldata);
        ViewGroup.inflate(ClubActivity.this, R.layout.activity_club_2, vg);
        
        
		Intent i = getIntent();
		Event event = (Event) i.getParcelableExtra("event");     
 
		Button btnNor = (Button) findViewById(R.id.btnNor);
		btnNor.setOnClickListener(new MyClickListener(context, event, R.id.btnNor));
        
		Button btnSi = (Button) findViewById(R.id.btnSi);
		btnSi.setOnClickListener(new MyClickListener(context, event, R.id.btnSi));
		
		Button btnResults = (Button) findViewById(R.id.btnResults);
		btnResults.setOnClickListener(new MyClickListener(context, event, R.id.btnResults));
		
		Button btnMap = (Button) findViewById(R.id.btnMap);
		btnMap.setOnClickListener(new MyClickListener(context, event, R.id.btnMap));
		
		Button btnWebsite = (Button) findViewById(R.id.btnWebsite);
		btnWebsite.setOnClickListener(new MyClickListener(context, event, R.id.btnWebsite));
		
		Button btnEmailContact = (Button) findViewById(R.id.btnEmailContact);
		btnEmailContact.setOnClickListener(new MyClickListener(context, event, R.id.btnEmailContact));
		
		Button btnPhoneContact = (Button) findViewById(R.id.btnPhoneContact);
		btnPhoneContact.setOnClickListener(new MyClickListener(context, event, R.id.btnPhoneContact));
		
		Button btnEmailClub = (Button) findViewById(R.id.btnEmailClub);
		btnEmailClub.setOnClickListener(new MyClickListener(context, event, R.id.btnEmailClub));
		
		Button btnPhoneClub = (Button) findViewById(R.id.btnPhoneClub);
		btnPhoneClub.setOnClickListener(new MyClickListener(context, event, R.id.btnPhoneClub));
		
		if (event.getResultsUrl() == null){
			btnResults.setEnabled(false);
		}
		if (event.getNorUrl() == null){
			btnNor.setEnabled(false);
		}
		if (event.getSiUrl() == null){
			btnSi.setEnabled(false);
		}
		if (event.getClub().getWebsite() == null){
			btnWebsite.setEnabled(false);
		}
				
		TextView club = (TextView) findViewById(R.id.txtClub);
		club.setText(event.getClub().getName());
		
		TextView date = (TextView) findViewById(R.id.txtDate);
		date.setText(event.getDate());
		
		TextView time = (TextView) findViewById(R.id.txtTime);
		time.setText(event.getTime());
		
        int resID = this.getBaseContext().getResources().getIdentifier(event.getClub().getFlag(), "drawable",  this.getBaseContext().getPackageName());  
        ImageView flag = (ImageView) findViewById(R.id.imgFlag);
        flag.setImageResource(resID);	
        
		TextView contactName = (TextView) findViewById(R.id.txtContactName);
		contactName.setText(event.getContact().getName());
		
		if (event.getContact().getEmail() != null){
			TextView contactEmail = (TextView) findViewById(R.id.txtContactEmail);
			contactEmail.setText(event.getContact().getEmail());	
		}
		else {
			btnEmailContact.setEnabled(false);
		}

		if (event.getContact().getPhone() != null){
			TextView contactPhone = (TextView) findViewById(R.id.txtContactPhone);
			contactPhone.setText(event.getContact().getPhone());			
		}
		else {
			btnPhoneContact.setEnabled(false);
		}
		
		TextView addr = (TextView) findViewById(R.id.txtAddress);
		addr.setText(getAddress(event.getAddress()));
		
		if (event.getClub().getEmail() !=null){
			TextView clubEmail = (TextView) findViewById(R.id.txtClubEmail);
			clubEmail.setText(event.getClub().getEmail());
		} 
		else {
			btnEmailClub.setEnabled(false);
		}
		

		if (event.getClub().getAddress().getPhone() != null){
			TextView clubPhone = (TextView) findViewById(R.id.txtClubPhone);
			clubPhone.setText(event.getClub().getAddress().getPhone());
		}
		else {
			btnPhoneClub.setEnabled(false);
		}

	}
	
	private class MyClickListener implements OnClickListener
	{
		private Context context;
		private Event event;
		private int button;
		
		public MyClickListener(Context context, Event event, int button)
		{
			this.context = context;
			this.event = event;
			this.button = button;
		}
		public void onClick(View v)
		{
			String url = null;
			String title = null;
			String email = null;
			String subject = null;
			String message = null;
			String phone = null;
			
			switch (button)
			{
				case R.id.btnNor:
					url = event.getNorUrl();
					title = event.getClub().getName() + ", NoR";
					showWebPage(context, title, url);			
					break;
				
				case R.id.btnSi:
					url = event.getSiUrl();
					title = event.getClub().getName() + ", SI's";
					showWebPage(context, title, url);
					break;
				
				case R.id.btnResults:
					url = event.getResultsUrl();
					title = event.getClub().getName() + ", results";
					showWebPage(context, title, url);
					break;
				
				case R.id.btnMap:
						//String postCode = event.getAddress().getPostalCode();
						String club = event.getClub().getName();
						String lat = String.valueOf(event.getClub().getAddress().getLatitude());
						String lng = String.valueOf(event.getClub().getAddress().getLongitude());
						
						Intent intent = new Intent(v.getContext(), ClubMapActivity.class);		
						intent.putExtra("lat", lat);
						intent.putExtra("lng", lng);
						intent.putExtra("club", club);
						startActivity(intent);					
					break;
				
				case R.id.btnWebsite:
					url = event.getClub().getWebsite();
					title = event.getClub().getName();
					showWebPage(context, title, url);
					break;
				
				case R.id.btnEmailContact:
					subject = "Laser South Coast Grand Prix - " + event.getClub().getName();
					message = "Please send details of your Laser Open";
					email = event.getContact().getEmail();
					email(email, subject, message);
					break;
					
				case R.id.btnEmailClub:
					subject = "Laser South Coast Grand Prix - " + event.getClub().getName();
					message = "Please send details of your Laser Open";
					email = event.getClub().getEmail();
					email(email, subject, message);
					break;
				
				case R.id.btnPhoneClub:
					phone = event.getAddress().getPhone();
					telephone(phone);		
					break;
					
				case R.id.btnPhoneContact:
					
					phone = event.getContact().getPhone();
					telephone(phone);		
					break;
			}
		}
	}
	
	public void telephone(String phone){
        TelephonyManager tm = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        String ableToMakePhoneCalls = tm.getVoiceMailNumber(); //check device for voicemail number (null means no voicemail number).
        
        if(ableToMakePhoneCalls == null){ //If the device does not have voicemail, then it must not be a phone. So it can't call.
        	Toast.makeText(context, "Unfortunately your device can not make phone calls", Toast.LENGTH_LONG).show();
        }
        else if (phone != null){
			Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone)); 
			startActivity(phoneIntent);
        }
	}
	
	public void email(String email, String subject, String message){
		
		final Intent emailIntent = new Intent(Intent.ACTION_SEND); 
		emailIntent.setType("text/plain"); 
		emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email}); 
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject); 
		emailIntent.putExtra(Intent.EXTRA_TEXT, message); 
		startActivity(Intent.createChooser(emailIntent, "Send mail..."));		
	}
	
	public void showWebPage(Context context, String title, String url)
	{
		Intent intent = new Intent(context, WebActivity.class);
		intent.putExtra("url", url);
		intent.putExtra("title", title);
		
		startActivity(intent);
	}
	
	public String getAddress(Address address){
		String addr = ((address.getAddressLine(0) != null) ? address.getAddressLine(0) + "\n" : "");
		addr = addr + ((address.getAddressLine(1) != null) ? address.getAddressLine(1) + "\n" : "");
		addr = addr + ((address.getAddressLine(2) != null) ? address.getAddressLine(2) + "\n" : "");
		addr = addr + ((address.getAddressLine(3) != null) ? address.getAddressLine(3) + "\n" : "");
		addr = addr + ((address.getPostalCode() != null)   ? address.getPostalCode()   + "\n" : "");
		
		return addr;
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
