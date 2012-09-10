package com.Tedx.UofM;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class home extends Activity {
	
	public void gotoSpeakers(View view){
		if(Utilities.isNetworkAvailable(this)){
			//check to see if the network is available
			
			if(speakers_home.speakers == null){
				//speakers havent been loaded yet so go to the loading page
				Intent intent = new Intent(home.this, loading_speakers.class);
				startActivity(intent);	
			}
			else{
				//speakers have already been loaded so no reason to load them again
				Intent intent = new Intent(home.this, speakers_home.class);
				startActivity(intent);
				
			}
			
		}
		else{
			Toast toast = Toast.makeText(this, "No Internet Connection Detected", Toast.LENGTH_SHORT);
			toast.show();
			return;
		}
	}
	
	public void gotoInfo(View view){
		Intent intent = new Intent(home.this, info_home.class);
		startActivity(intent);	
	}
	
	public void gotoSchedule(View view){
		Intent intent = new Intent(home.this, schedule_home.class);
		startActivity(intent);	
	}
	public void gotoNotes(View view){
		Intent intent = new Intent(home.this, NotesList.class);
		startActivity(intent);
	}
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

	}
}
