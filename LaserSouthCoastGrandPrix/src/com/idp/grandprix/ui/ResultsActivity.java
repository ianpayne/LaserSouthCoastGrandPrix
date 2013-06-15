package com.idp.grandprix.ui;

import com.idp.grandprix.R;
import com.idp.grandprix.model.ResultLink;

import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ResultsActivity extends FooterActivity {
	private ListView listView1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_results);
		
        ViewGroup vg = (ViewGroup) findViewById(R.id.lldata);
        ViewGroup.inflate(ResultsActivity.this, R.layout.activity_results, vg);
		
		ResultLink[] results = new ResultLink[10];		
		
        results = new ResultLink[]
        {
            new ResultLink("2013", "http://www.roostersailing.com/laserscstd2013.html",
						   			"http://www.roostersailing.com/laserscrad2013.html",
						  			"http://www.roostersailing.com/lasersc472013.html"),
        	new ResultLink("2012", "http://www.roostersailing.com/laserscstd2012.html",
        						   "http://www.roostersailing.com/laserscrad2012.html",
        						   "http://www.roostersailing.com/lasersc472012.html"),
			new ResultLink("2011", "http://www.roostersailing.com/laserscstd2011.html",
								   "http://www.roostersailing.com/laserscrad2011.html",
								   "http://www.roostersailing.com/lasersc472011.html"),
			new ResultLink("2010", "http://www.roostersailing.com/laserscstd.html",
								   "http://www.roostersailing.com/laserscrad.html",
								   "http://www.roostersailing.com/lasersc47.html"),
			new ResultLink("2009", "http://www.roostersailing.com/laserscstd_2009.html",
								   "http://www.roostersailing.com/laserscrad_2009.html",
								   "http://www.roostersailing.com/lasersc47_2009.html"),
			new ResultLink("2008", "http://www.roostersailing.com/LaserStandardRig_LaserSouthCoastGrandPrix-2008.htm",
								   "http://www.roostersailing.com/LaserRadialRig_LaserSouthCoastGrandPrix-2008.htm",
								   "http://www.roostersailing.com/Laser4.7Rig_LaserSouthCoastGrandPrix-2008.htm"),
			new ResultLink("2007", "http://www.roostersailing.com/LaserStandard_LaserSouthCoastGrandPrix-2007.htm",
								   "http://www.roostersailing.com/LaserRadial_LaserSouthCoastGrandPrix-2007.htm",
								   "http://www.roostersailing.com/Laser4.7_LaserSouthCoastGrandPrix-2007.htm"),
			new ResultLink("2006", "http://www.roostersailing.com/LaserStd_SouthCoastGP-Overallresults2006.htm",
								   "http://www.roostersailing.com/LaserRadial_SouthCoastGP-Overallresults2006.htm",
								   "http://www.roostersailing.com/Laser4.7_SouthCoastGP-Overallresults2006.htm"),
			new ResultLink("2005", "http://www.roostersailing.com/LaserStd_SouthCoastGP-Overallresults.htm",
								   "http://www.roostersailing.com/LaserRadial_SouthCoastGP-Overallresults.htm",
								   "http://www.roostersailing.com/Laser4.7_SouthCoastGP-Overallresults.htm"),
        };
        
        
        
        ResultsAdapter adapter = new ResultsAdapter(this, R.layout.results_item_row, results);   
        
        
        
        //returns null
        listView1 = (ListView) this.findViewById(R.id.listResults);
        
        //Toast.makeText(getBaseContext(), "R.id.listResults: "  + R.id.listResults, Toast.LENGTH_LONG).show();
        
        if (listView1 != null) 
        {       
        	View header = (View) getLayoutInflater().inflate(R.layout.results_header, null);
        	listView1.addHeaderView(header);
       
        	listView1.setAdapter(adapter);
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
        	
        default:
            return super.onOptionsItemSelected(item);
        }
        
        return true;
 
    }   
}
