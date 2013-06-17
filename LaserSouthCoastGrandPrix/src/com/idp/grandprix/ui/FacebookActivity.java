package com.idp.grandprix.ui;


import com.idp.grandprix.R;

import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class FacebookActivity extends FooterActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.facebook_activity);
		ViewGroup vg = (ViewGroup)findViewById(R.id.lldata);
		ViewGroup.inflate(getBaseContext(), R.layout.webview, vg);
		
		WebView wv = (WebView)findViewById(R.id.webView1);
		wv.setWebViewClient(new Callback());
		WebSettings webSettings = wv.getSettings();
		webSettings.setBuiltInZoomControls(true);
		wv.loadUrl("http://www.facebook.com/LaserSouthCoastGrandPrix");
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
