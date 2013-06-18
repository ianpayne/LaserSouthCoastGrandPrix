package com.idp.grandprix.ui;

import com.idp.grandprix.R;
import com.idp.grandprix.model.Event;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
//import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class EventAdapter extends ArrayAdapter<Event>
	{

	    Context context;
	    int layoutResourceId;   
	    //Event data[] = null;
	   
	    public EventAdapter(Context context, int layoutResourceId, Event[] data) {
	        super(context, layoutResourceId, data);
	        this.layoutResourceId = layoutResourceId;
	        this.context = context;
	        //this.data = data;
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        View row = convertView;
	        EventHolder holder = null;
	        //ImageView flag = null;
	       
	        if(row == null)
	        {
	            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
	            row = inflater.inflate(layoutResourceId, parent, false);
	           
	            holder = new EventHolder();
	            holder.flag = (ImageView) row.findViewById(R.id.imgFlag);
	            holder.date = (TextView) row.findViewById(R.id.txtDate);
	            holder.club = (TextView) row.findViewById(R.id.txtClub);
	            holder.time = (TextView) row.findViewById(R.id.txtTime);
	            //holder.nor = (TextView) row.findViewById(R.id.nor);
	           
	            row.setTag(holder);
	        }
	        else
	        {
	            holder = (EventHolder)row.getTag();
	        }
	        //getItem(position);
	        //Event event = data[position];
	        Event event = getItem(position);
	        
	        if (event != null){
	        	//Log.e("Event", event.getClub().getName());
		        int resID = this.context.getResources().getIdentifier(event.getClub().getFlag(), "drawable",  this.context.getPackageName());  
		        holder.flag.setImageResource(resID);
		        holder.date.setText(event.getDate());
		        holder.club.setText(event.getClub().getName());
		        holder.time.setText(event.getTime());
		        //holder.nor.setText(event.nor);
		        
		        row.setOnClickListener(new MyClickListener(event));	        	
	        }
	        else {
	        	//Log.e("Event", "Event is null!");
	        }
	        

	        
	        return row;
	    }
	    
	    private class MyClickListener implements OnClickListener {
	        private Event event;

	        public MyClickListener(Event event) {
	           this.event = event;
	        }

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Event event = events[position];
				
				Intent intent = new Intent(getContext(), ClubActivity.class);
				intent.putExtra("event", event);
	    	   
	    	    context.startActivity(intent);				
			}
	        	   
	    }
	   
	    static class EventHolder
	    {
	    	ImageView flag;
	        TextView date;
	        TextView club;
	        TextView time;
	        TextView nor;
	    }
	}