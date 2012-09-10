package com.Tedx.UofM;
 
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class speaker_grid_adapter extends BaseAdapter{

	Context MyContext;
    Speaker speakers[];
    public speaker_grid_adapter(Context _MyContext)
    {
       MyContext = _MyContext;
    }
    
    @Override
    public int getCount() 
    {
                      /* Set the number of element we want on the grid */
       return speakers_home.speakers.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) 
    {
    	
    
       Speaker speaker = speakers_home.speakers[position];
       View v;
       if(convertView == null) {
			
    	   LayoutInflater inflater = LayoutInflater.from(MyContext);
			v = inflater.inflate(R.layout.speaker_grid_item, null);
			ImageView thumbnail = (ImageView)v.findViewById(R.id.speaker_grid_thumbnail);
			thumbnail.setImageDrawable(Utilities.LoadImage(speaker.getThumbnail_url(), MyContext));
			TextView speakerName = (TextView)v.findViewById(R.id.speaker_grid_name);
			speakerName.setText(speaker.getTitle()); 
			Log.v("TED", "getView for Speakers[" + position + "] TITLE = " + speaker.getTitle());
		}
       else{
    	   v = convertView;
       }

//		ImageView ivIcon = (ImageView)convertView.findViewById(R.id.ivIcon);
//		ivIcon.setImageBitmap(entry.getIcon());

		    

		return v;

}

	@Override
	public Object getItem(int arg0) {
		 return speakers_home.speakers[arg0];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	
	
	
	
	
}
//public class skateSpotAdapter extends ArrayAdapter<skateSpot>{
//
//    Context context; 
//    int layoutResourceId;    
//    skateSpot data[] = null;
//    
//    public skateSpotAdapter(Context context, int layoutResourceId, skateSpot[] data) {
//        super(context, layoutResourceId, data);
//        this.layoutResourceId = layoutResourceId;
//        this.context = context;
//        this.data = data;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View row = convertView;
//        skateSpotHolder holder = null;
//        
//        if(row == null)
//        {
//            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
//            row = inflater.inflate(layoutResourceId, parent, false);
//            
//            holder = new skateSpotHolder();
//           holder.name = (TextView)row.findViewById(R.id.name);
//           holder.category = (TextView)row.findViewById(R.id.category);
//           holder.distance = (TextView)row.findViewById(R.id.list_item_distance);
//           holder.rating = (ProgressBar)row.findViewById(R.id.list_item_rating);
//      
//            row.setTag(holder);
//        }
//        else
//        {
//            holder = (skateSpotHolder)row.getTag();
//        }
//        
//        skateSpot spot = data[position];
//        holder.name.setText(spot.name);
//        holder.category.setText(spot.category);
//        
//        String dist  = String.format("%.3g mi", spot.distanceToSource);
//        holder.distance.setText(dist);
//        holder.rating.setProgress((int)Math.ceil(spot.rating));
//        return row;
//    }
//    
//    static class skateSpotHolder
//    {
//        TextView name;
//        TextView category;
//        TextView distance;
//        ProgressBar rating;
//    }
//}