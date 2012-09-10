package com.Tedx.UofM;




import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class speakers_home extends Activity{
	
	public static Speaker speakers[];
	public static speaker_list_adapter adapter;
	private void launch_speaker_detail_activity(int i){
		Intent intent = new Intent(speakers_home.this, speaker_detail.class);
		intent.putExtra("id", i);
		startActivity(intent);
	
	}
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.speaker_list);
		
		ListView speakerList = (ListView)findViewById(R.id.speaker_list_view);
	
		adapter = new speaker_list_adapter(this, R.layout.speaker_list_item, speakers);
		//Log.v("TED", "adapter count = " + adapter.getCount());
		speakerList.setAdapter(adapter);

		speakerList.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View view,
		        int position, long id) {
		    	launch_speaker_detail_activity(position);
		     
		    }
		  });
	}
	
	
	
	
	protected void onStop() {
	    // TODO Auto-generated method stub
	    setResult(2);
	    super.onStop();
	}
	@Override
	protected void onDestroy() {
	    // TODO Auto-generated method stub
	    setResult(2);
	    super.onDestroy();
	}
	
}
