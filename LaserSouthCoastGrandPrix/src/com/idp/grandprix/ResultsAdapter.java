package com.idp.grandprix;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class ResultsAdapter extends ArrayAdapter<ResultLink> //implements OnClickListener
{

    Context context;
    int layoutResourceId;   
    ResultLink data[] = null;
   
    
    public ResultsAdapter(Context context, int layoutResourceId, ResultLink[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        Holder holder = null;
 
        //ImageView flag = null;
       
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
           
            holder = new Holder();
            holder.year = (TextView) row.findViewById(R.id.txtYear);
            //holder.standard = (Button) row.findViewById(R.id.standard);
            //holder.radial = (Button) row.findViewById(R.id.radial);
            //holder.fourPointSeven = (Button) row.findViewById(R.id.fourpointseven);
           
            row.setTag(holder);
        }
        else
        {
            holder = (Holder)row.getTag();
        }
        
        ResultLink result = data[position];
        holder.year.setText(result.year);
        //holder.standard.setText("Standard");
        //holder.radial.setText("Radial");
        //holder.fourPointSeven.setText("4.7");
        
        ImageButton button = (ImageButton) row.findViewById(R.id.standard);
        button.setOnClickListener(new MyClickListener(position, "Standard", data));
        button = (ImageButton) row.findViewById(R.id.radial);
        button.setOnClickListener(new MyClickListener(position, "Radial", data));
        button = (ImageButton) row.findViewById(R.id.fourpointseven);
        button.setOnClickListener(new MyClickListener(position, "4.7", data));
        
        return row;
    }
    
    private class MyClickListener implements OnClickListener {

        private int position;
        private String rig;
        private ResultLink data[];

        public MyClickListener(int position, String rig, ResultLink data[]) {
           this.position = position;
           this.rig = rig;
           this.data = data;
        }

        public void onClick(View v) {
           // get URL
           String url = "";
           if (rig.contains("Standard")){
        	   url = data[position].getStandard();
           }
           else if (rig.contains("Radial")){
        	   url = data[position].getRadial();
           }
           else if (rig.contains("4.7")){
        	   url = data[position].getFourPointSeven();
           }     
           
           // launch web view with results url
                     
           Intent intent = new Intent(context, WebActivity.class);
           
           if (intent == null || context == null)
           {
        	  System.out.println("NULL POINTER"); 
           }
           else
           {
        	   //System.out.println("Gets HERE");
        	   intent.putExtra("url", url);
        	   intent.putExtra("title", ("South Coast GP, " + data[position].getYear() + " results for the " + rig + " class."));
        	   //intent.putExtra("Year", data[position].getYear());
        	   //intent.putExtra("Rig", rig);
        	   
        	   context.startActivity(intent);
           }
        	   
        }
        /*
        public int getPosition() {
          return position;
        }
        
        public String getRig() {
        	return rig;
        }
        
        public ResultLink getResultLink(){
        	return data[getPosition()];
        }
*/
     }
    static class Holder
    {
        TextView year;
        //Button standard;
        //Button radial;
        //Button fourPointSeven;
    }
}
