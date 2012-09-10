package com.Tedx.UofM;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;


import com.monkeyseemonkeydo.widgets.LockingHorizontalScrollView;

public class info_home extends Activity /*implements OnTouchListener*/{
	private LockingHorizontalScrollView mScrollView;
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.better_gallery);
		
        mScrollView = (LockingHorizontalScrollView) findViewById(R.id.scrollview);
	}
		
	
	public void goto_fb_tedX(View view){
		String url = "https://facebook.com/pages/TEDxUofM/260422438196?_rdr";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}
	public void goto_twitter_tedX(View view){
		String url = "https://twitter.com/#!/tedxuofm";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}

	public void goto_fb_TED(View view){
		String url = "https://www.facebook.com/TED";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}
	public void goto_twitter_TED(View view){
		String url = "https://twitter.com/#!/TEDNews";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}
	public void map_to_power_center(View view){
		Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
				Uri.parse("http://maps.google.com/maps?q=Power+Center-Performing%20Arts+121+Fletcher+Street,+Ann+Arbor,+MI+48109"));
				startActivity(intent);
	}
	
	
}
