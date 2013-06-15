package com.idp.grandprix.ui;

import com.idp.grandprix.R;
import com.idp.grandprix.model.Event;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ClubAdapter extends BaseAdapter {
    Context context;
    int layoutResourceId;
    Event event;
    
    public ClubAdapter(Context context, Event event) {
        this.context = context;
        this.event = event;
    }
    
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		View row = view;
		Holder holder = null;
		
		if (view == null){
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			view = inflater.inflate(R.layout.activity_club_2, null);
			
			holder = new Holder();
			
			view.setTag(holder);
			
		} else {
			holder = (Holder) view.getTag();
		}
		
        int resID = this.context.getResources().getIdentifier(event.getClub().getFlag(), "drawable",  this.context.getPackageName());  
        holder.flag.setImageResource(resID);
        //holder.club.setText(event.getClub().getName());
        holder.club.setText("Should not get here");
        holder.date.setText(event.getDate());
        holder.time.setText(event.getTime());
        holder.details.setText(event.getNorUrl()); ///TODO
        
		
		return row;
	}
	
	static class Holder
	{
		TextView club;
		TextView date;
		TextView time;
		TextView details;
		ImageView flag;
		
		
	}

}
