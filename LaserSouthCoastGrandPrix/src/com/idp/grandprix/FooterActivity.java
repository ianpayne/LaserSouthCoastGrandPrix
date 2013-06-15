package com.idp.grandprix;

import android.os.Bundle;
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
	

	
}
