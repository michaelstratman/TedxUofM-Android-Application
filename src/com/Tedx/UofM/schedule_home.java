package com.Tedx.UofM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

public class schedule_home extends Activity{

	public static Set<Event> events = new TreeSet<Event>();
	
	private void loadEvents(){
		
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		JSONObject eventArray = null;
		try{	
		eventArray = Utilities.getJSONfromURL(Utilities.eventsURL, nameValuePairs);
		}
		catch(Error e){
			e.handle(this);
			finish();
		}
		if(eventArray == null){
			Toast toast = Toast.makeText(this, "Failed to load events.", Toast.LENGTH_SHORT);
			toast.show();
			finish();
		}
		
		JSONArray events_json = null;
			try {
				events_json = eventArray.getJSONArray("events");
				int num_events = events_json.length();
				
				for(int i = 0; i < num_events; i++){
					Event e = new Event(events_json.getJSONObject(i));
					events.add(e);
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Toast toast = Toast.makeText(this, "Failed to load events.", Toast.LENGTH_SHORT);
				toast.show();
				finish();
			}
		
	}
	
	
	private void loadUpEventstoTest(){
		
		int month = 2;
		int year = 2012;
		int day = 26;
		for(int i = 1, h = 1, m = 1; i < 30; i++, h =  (int)(Math.random() * 24), m = (int)(Math.random() * 60)){
			Date start = new Date();
			Date end = new Date();
			start.setDate(day);
			start.setYear(year);
			start.setMonth(month);
			start.setHours(h);
			start.setMinutes(m);
			
			end.setDate(day);
			end.setYear(year);
			end.setMonth(month);
			end.setHours(h);
			end.setMinutes(m+(int)(Math.random() * 60));

			try {
				Event e;
				e = new Event("Event #" + i, "Power Center", start, end);
				events.add(e);
			} catch (Error er) {
				// TODO Auto-generated catch block
				er.printStackTrace();
			}
			
		}
		
	}
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.schedule_list);
		loadEvents();
		
		ListView speakerList = (ListView) findViewById(R.id.event_list_view);
		Event[] e_array = (Event[]) events.toArray(new Event[events.size()]);
		schedule_list_adapter adapter = new schedule_list_adapter(this, R.layout.event_list_item, e_array);
		speakerList.setAdapter(adapter);
		
	}
}
