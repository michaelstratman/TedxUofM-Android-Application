package com.Tedx.UofM;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class speaker_list_adapter extends ArrayAdapter<Speaker>{

		Context context; 
		Activity activity;
	    int layoutResourceId;    
	    Speaker data[] = null;
	    public ImageManager imageManager;
	    
	    public speaker_list_adapter(Activity a, int layoutResourceId, Speaker[] data) {
	        super(a, layoutResourceId, data);
	        this.layoutResourceId = layoutResourceId;
	        this.context = a.getApplicationContext();
	        activity = a;
	        this.data = data;
	        imageManager = new ImageManager(context);
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        View row = convertView;
	        SpeakerHolder holder = null;
	        
	        
	        if(row == null)
	        {
	            LayoutInflater inflater = activity.getLayoutInflater();
	            row = inflater.inflate(layoutResourceId, parent, false);
	            
	           holder = new SpeakerHolder();
	           holder.name = (TextView)row.findViewById(R.id.speaker_list_name);
	           holder.description = (TextView)row.findViewById(R.id.speaker_list_desc);
	           holder.pic = (ImageView)row.findViewById(R.id.speaker_list_pic);
	           //holder.year = (TextView)row.findViewById(R.id.speaker_item_year);
	           row.setTag(holder);           
	          
	        }
	        else
	        {
	            holder = (SpeakerHolder)row.getTag();
	        }
	        
	    	Speaker speaker = data[position];
	    	if(speaker != null){
		    	holder.name.setText(speaker.getTitle() + " (" + speaker.getYear() + ")");//concatinate the year on the end
		        holder.description.setText(speaker.getExcerpt());
		        holder.pic.setTag(speaker.getThumbnail_url());
		        imageManager.displayImage(speaker.getThumbnail_url(), activity, holder.pic);
		        
	    	}
	        return row;
	    }
	    
	    static class SpeakerHolder
	    {
	        TextView name;
	        TextView description;
	        ImageView pic;
	    }
	
}
