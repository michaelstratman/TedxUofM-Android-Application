package com.Tedx.UofM;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class Speaker implements Comparable<Speaker> {

	
	private int id;
	private String title;
	private String content;
	private String excerpt;
	private String thumbnail_url;
	//private Drawable speakerPicture;
	private String year;
	private String video_service;
	private String video_id;
	
	public Speaker(JSONObject jobject, Context context) throws JSONException{

		try{
			id = jobject.getInt("id");
			title = jobject.getString("title");
			content = jobject.getString("content");
			excerpt = jobject.getString("excerpt");
			if(jobject.has("thumbnail")){
				thumbnail_url = jobject.getString("thumbnail");
				//speakerPicture = Utilities.LoadImage(thumbnail_url, context);
			}
			else{
				thumbnail_url = "";
				//speakerPicture = Utilities.LoadImage(thumbnail_url, context);
			}
			year = jobject.getString("year");
			video_service = jobject.getString("video_service");
			video_id = jobject.getString("video_id");

		}
			catch(JSONException e){
				e.printStackTrace();
		}
			
		//clean up some of the HTML artifacts
			
		
//		year = year.replace("[\"", "");
//		year = year.replace("\"]", "");
//		video_id = video_id.replace("[\"", "");
//		video_id = video_id.replace("\"]", "");
//		
//		content = content.replace("<p>", "");
//		content = content.replace("</p>", "");
//		content = content.replace("&#8220;", "\"");
//		content = content.replace("&#8230;", "...");
//		content = content.replace("&#8221;", "\"");
//		content = content.replace("&#038;", "&");
//		
//		excerpt = excerpt.replace("&#8220;", "\"");
//		excerpt = excerpt.replace("&#8230;", "...");
//		excerpt = excerpt.replace("&#8221;", "\"");
//		excerpt = excerpt.replace("&#038;", "&");
	
			
			
	}
		
	//getters
	
	public int getId(){
		return id;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getContent(){
		return content;
	}
	
	public String getExcerpt(){
		return excerpt;
	}
	
	public String getThumbnail_url(){
		return thumbnail_url;
	}
	
	public String getYear(){
		return year;
	}
	
	public String getVideo_id(){
		return video_id;
	}
	
//	public Drawable getSpeakerPic(){
//		return speakerPicture;
//	}
	
	public String getVideoURL(){
		if(video_service.equals("youtube")){
			//return youtube url
			return "http://www.youtube.com/watch?v=" + video_id;
		}
		else if(video_service.equals("vimeo")){
			//return vimeo url
			return "http://vimeo.com/" + video_id;
		}
		else{
			return null;
		}
	}

//	@Override
//	public int compareTo(Object arg0) {
//		//if the years are equal compare the title's if not compare the the titles
//		if(year.equals(((Speaker)arg0).year)){
//			return title.compareTo(((Speaker)arg0).title);
//		}
//		else{
//			return year.compareTo(((Speaker)arg0).year);
//		}
//	}

	@Override
	public int compareTo(Speaker arg0) {
		if(year.equals(arg0.year)){
			return title.compareTo(arg0.title);
		}
		else{
			return arg0.year.compareTo(year);
		}
	}
	
}
	

