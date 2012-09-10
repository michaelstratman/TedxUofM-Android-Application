package com.Tedx.UofM;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.ParseException;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

public class Utilities {
	
	
	public static String serverURL = "http://tedxuofm.webfactional.com/api/talks/search/"; 
	public static String speakerURL = "http://tedxuofm.com/api/talks/search/";
	public static String eventsURL = "http://tedxuofm.com/api/events/search/"; 
	
	public static boolean postHTTPfromURL(String URL){
		InputStream is = null;
		StringBuilder sb=null;
		String result = null;

			//InputStream is = null;
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			try{
			     HttpClient httpclient = new DefaultHttpClient();
			     HttpPost httppost = new HttpPost(URL);
			     httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			     //HttpResponse response = httpclient.execute(httppost);
			     httpclient.execute(httppost);
			    // HttpEntity entity = response.getEntity();
			     //is = entity.getContent();
			     }catch(Exception e){
			         Log.e("log_tag", "Error in http connection"+e.toString());
			    }
			try{
				      BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
				       sb = new StringBuilder();
				       sb.append(reader.readLine() + "\n");
				       String line="0";
				       while ((line = reader.readLine()) != null) {
				                      sb.append(line + "\n");
				        }
				      //  is.close();
				        result=sb.toString();
				        }catch(Exception e){
				              Log.e("log_tag", "Error converting result "+e.toString());
				        }
				      
			    if(result == "1") return true; //Successful post
			    else return false; //not successful post;

	}

	
	
	public static JSONObject getJSONfromURL(String url, ArrayList<NameValuePair> nameValuePairs) throws Error{
		JSONObject jArray = null;
		String result = null;
		InputStream is = null;
		StringBuilder sb=null;
		
		//http post
		try{
		     HttpClient httpclient = new DefaultHttpClient();
		     HttpPost httppost = new HttpPost(url);
		     httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		     HttpResponse response = httpclient.execute(httppost);
		     HttpEntity entity = response.getEntity();
		     is = entity.getContent();
		     }catch(Exception e){
		    	 
		         Log.e("log_tag", "Error in http connection"+e.toString());
		         throw new Error("Something went wrong while fetching data from the server, please try again");
		    }
		//convert response to string
		try{
		      BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
		       sb = new StringBuilder();
		       sb.append(reader.readLine() + "\n");
		       String line="0";
		       while ((line = reader.readLine()) != null) {
		                      sb.append(line + "\n");
		        }
		        is.close();
		        result=sb.toString();
		        }catch(Exception e){
		              Log.e("log_tag", "Error converting result "+e.toString());
				      throw new Error("Something went wrong while fetching data from the server, please try again");

		        }

		try{
		      jArray = new JSONObject(result);
		      }
		      catch(JSONException e1){
		    	  
		    	  e1.printStackTrace();
			         throw new Error("Something went wrong while fetching data from the server, please try again");

		      } catch (ParseException e1) {
					e1.printStackTrace();
			         throw new Error("Something went wrong while fetching data from the server, please try again");

			}
		      return jArray;
	}
	
	public static boolean isNetworkAvailable(Context context) {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null;
	}
	
	public static Drawable LoadImage(String url, Context MyContext) {

	    Drawable d;
	    try {
	        
	    	InputStream is = (InputStream) new URL(url).getContent();
	        d = Drawable.createFromStream(is, "src name");
	        return d;
	        
	    } catch (NullPointerException e) {
	     d = MyContext.getResources().getDrawable(R.drawable.x_missing_pic);
	      return d;
	    } catch (Exception e) {
	    	d = MyContext.getResources().getDrawable(R.drawable.x_missing_pic);
		      return d;
	    }
	}
		
}
