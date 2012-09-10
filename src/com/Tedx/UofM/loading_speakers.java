package com.Tedx.UofM;

import java.util.Arrays;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

public class loading_speakers extends Activity{
	ProgressBar prog;
	int num_talks;
	int num_talks_loaded = 0;
	private void load_speakers(Context context){
		//ProgressBar prog = (ProgressBar)findViewById(R.id.speaker_loading_bar);
		
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		JSONObject speakerArray = null;
		try{
		speakerArray = Utilities.getJSONfromURL(Utilities.speakerURL, nameValuePairs);
		}
		catch(Error e){
			e.handle(this);
			speakerArray = null;
			return;
		}
		if(speakerArray == null){
			Toast toast = Toast.makeText(context, "Failed to load speakers.", Toast.LENGTH_SHORT);
			toast.show();
			return;
		}
		JSONArray talks;
			try {
				talks = speakerArray.getJSONArray("talks");
				num_talks = talks.length();
				prog.setMax(num_talks);
				speakers_home.speakers = new Speaker[num_talks];
				
				for(int i = 0; i < num_talks; i++){
					speakers_home.speakers[i] = new Speaker(talks.getJSONObject(i), context);
					//Log.v("TED", "TITLE: " + speakers_home.speakers[i].getTitle());
					prog.setProgress(i);
					++num_talks_loaded;
				}
				Arrays.sort(speakers_home.speakers);
				
			} catch (JSONException e) {
				Toast toast = Toast.makeText(context, "Failed to load speakers.", Toast.LENGTH_SHORT);
				toast.show();
				speakers_home.speakers = null;
				return;
			}
			
	}

	// define class somewhere in your code
	private class UIUpdate extends AsyncTask<Void,Void,Void>{

		@Override
		protected Void doInBackground(Void... arg0) {
		// fire your service here
		load_speakers(loading_speakers.this);
		if(speakers_home.speakers == null){
			finish();
		}
		return null;
	
		}
	
		@Override
		protected void onPostExecute(Void result) {
	
		    Intent intent = new Intent(loading_speakers.this, speakers_home.class);
		    startActivityForResult(intent,2);			
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	   // if(resultCode==2){
	        finish();
	    //}
	}
	UIUpdate ui;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.speaker_loading_page);
		 prog = (ProgressBar) findViewById(R.id.speaker_loading_bar);
	ui = new UIUpdate();
	ui.execute();

	}
	@Override
	protected void onDestroy(){
		//if the user destroys the activity and all the talks has not been loaded, cancel the loading speakers thread
		//and set speakers to null
		if(num_talks != num_talks_loaded){
			ui.cancel(true);
			speakers_home.speakers = null;
		}
		super.onDestroy();
	
	}


	
	
}
