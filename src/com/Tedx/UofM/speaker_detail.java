package com.Tedx.UofM;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class speaker_detail extends Activity{
	
	public void watchVideo(View view){
		String url = speaker.getVideoURL();
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
	}

	int id;
	private Speaker speaker;
	
	ImageView img;
	TextView nameTV;
	TextView descriptionTV;
	TextView yearTV;
	
	private void updateUI(){
		
		
		img = (ImageView)findViewById(R.id.speaker_detail_pic);
		speakers_home.adapter.imageManager.displayImage(speaker.getThumbnail_url(), this, img);
		//img.setImageDrawable(speaker.getSpeakerPic());
		
		nameTV = (TextView)findViewById(R.id.speaker_detail_name);
		nameTV.setText(speaker.getTitle());
		
		descriptionTV = (TextView)findViewById(R.id.speaker_detail_description);
		descriptionTV.setText(speaker.getContent());
	
		yearTV = (TextView)findViewById(R.id.speaker_detail_year);
		yearTV.setText(speaker.getYear());
		
		ImageView play = (ImageView) findViewById(R.id.speaker_play);
		if(speaker.getVideoURL() == null){
			play.setVisibility(View.GONE);
			FrameLayout fl = (FrameLayout) findViewById(R.id.speaker_picture_frame);
			fl.setClickable(false);
		};
		
	}
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.speaker_detail);
		id = getIntent().getIntExtra("id", 0);
		speaker = speakers_home.speakers[id];
	
		updateUI();
	}
	
}
