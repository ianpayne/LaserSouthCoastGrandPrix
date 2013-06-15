package com.idp.grandprix.ui;


import com.idp.grandprix.R;

import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class ClubMapActivity extends FooterActivity {
	private String club;
	private String lat;
	private String lng;
	private WebView webView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ViewGroup vg = (ViewGroup)findViewById(R.id.lldata);
		ViewGroup.inflate(getBaseContext(), R.layout.webview, vg);
				
		webView = (WebView)findViewById(R.id.webView1);
		webView.setWebViewClient(new Callback());
		WebSettings webSettings = webView.getSettings();
		webSettings.setBuiltInZoomControls(true);
						
		//get passed url
		Intent intent = getIntent();
		lat = intent.getStringExtra("lat");
		lng = intent.getStringExtra("lng");
		club = intent.getStringExtra("club");
		displayMap();
		
	}
	
	public void displayMap(){
		this.setTitle("South Coast GP, " + club);
		String baseUrl = "http://maps.googleapis.com/maps/api/staticmap?";		
		//String lat = "50.803765";
		//String lng = "-0.825777";
		String center = "center=" + lat + "," + lng;
		String label = "label:CYC";
		String colour = "color:blue";
		String marker = "&markers=" + colour + "%7C" + label + "%7C" + lat + "," + lng;           
		String size = "&size=" + "640x640";
		String zoom = "&zoom=" + "2";
		String scale=null;
    	scale = "&scale=1";
		
		String maptype = "&maptype=roadmap";
		String sensor = "&sensor="+ "false";
		String builtUrl = baseUrl + zoom + center +  size + scale + maptype + marker + sensor;
		
		webView.loadUrl(builtUrl);		
	}
	/*
	public void toggleZoom(View v){
		Button btnZoom = (Button) findViewById(R.id.btnZoom);
		if (btnZoom.getText() == "1x"){
			btnZoom.setText("2x");
			displayMap(1);
		} else {
			btnZoom.setText("1x");
			displayMap(2);
		}
		
	}
	*/

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
