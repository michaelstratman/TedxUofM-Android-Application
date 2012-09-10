package com.Tedx.UofM;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.widget.Toast;



public class Event implements Comparable<Event> {

	
	
	private String title;
	private String location;
	private String description;
	private Date start;
	private Date end;
	
	public Event(JSONObject event_json){
		
		try{
			title = event_json.getString("title");
			description = event_json.getString("content");
			String start_time = event_json.getString("start_time");
			String end_time = event_json.getString("end_time");
			location = "Power Center";
			 DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 
			 try {
				start = (Date)formatter.parse(start_time);
				end = (Date)formatter.parse(end_time);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			 
			
		}
			catch(JSONException e){
				e.printStackTrace();
		}
		
	}
	
	Event(String title_, String location_, Date start_, Date end_) throws Error{
		title = title_;
		if(!start_.before(end_)){
			//date inputted doesnt make sense they are out of order
			throw new Error("Start date can not be after end date!");	
		}	
		location = location_;
		start = start_;
		end = end_;
	}
	public String title(){
		return title;
	}
	public String location(){
		return location;
	}
	public Date get_start(){
		return start;
	}
	public Date get_end(){
		return end;
	}
	public String toString(){
		return title + " " + location + " Starts " + start.toString() + " Ends " + end.toString();
	}
	public String get_time(){
		 DateFormat formatter = new SimpleDateFormat("hh:mm aa");
		 String s = formatter.format(start);
		 String e = formatter.format(end);
		 return s + " - " + e;
	}
	public long duration(){
		//return the difference in milliseconds between the two events
		return end.getTime() - start.getTime();
	}

	@Override
	public int compareTo(Event e2) {		
		if(start.equals(e2)){
			return title.compareTo(title);
		}
		else{
			return start.compareTo(e2.start);
		}	
	}

}
	
