package com.Tedx.UofM;


import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;



public class schedule_list_adapter extends ArrayAdapter<Event>{

	 Context context; 
	    int layoutResourceId;    
	    Event data[] = null;
	    
	    public schedule_list_adapter(Context context, int layoutResourceId, Event[] data) {
	        super(context, layoutResourceId, data);
	        this.layoutResourceId = layoutResourceId;
	        this.context = context;
	        this.data = data;
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        View row = convertView;
	        EventHolder holder = null;
	        
	        if(row == null)
	        {
	            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
	            row = inflater.inflate(layoutResourceId, parent, false);
	            
	           holder = new EventHolder();
	           holder.title = (TextView) row.findViewById(R.id.event_item_title);
	           holder.time = (TextView) row.findViewById(R.id.event_item_time);
	           holder.location = (TextView) row.findViewById(R.id.event_item_location);
	           holder.bar = (LinearLayout) row.findViewById(R.id.event_item_bar);
	           row.setTag(holder);
	          
	        }
	        else
	        {
	            holder = (EventHolder)row.getTag();
	        }
	        
	    	Event event = data[position];
	    	holder.title.setText(event.title());
	    	holder.location.setText(event.location());
	    	holder.time.setText(event.get_time());
	    	
	    	Date d = new Date();
	    	d.setTime(System.currentTimeMillis());
	    	int diff = event.get_start().compareTo(d);
	    	int diffEnd = event.get_end().compareTo(d);
	    	
	    	if(diff <= 0){
	    		//event start date is before current time
	    		if(diffEnd <= 0){
	    			//event is going on
	    	    	holder.bar.setBackgroundColor(Color.GREEN);

	    			
	    		}
	    		else{
	    			//event has passed
	    	    	holder.bar.setBackgroundColor(Color.RED);

	    			
	    		}
	    	}
	    	else{
	    	//event has not happened yet
	    		holder.bar.setBackgroundColor(Color.BLUE);
		    	//holder.bar.setBackgroundColor(0xFFFFFF);

	    		
	    	}
	    	
	    	
	        return row;
	    }
	    
	    static class EventHolder
	    {
	        TextView title;
	        TextView time;
	        TextView location;
	        LinearLayout bar;
	    }
	
}
