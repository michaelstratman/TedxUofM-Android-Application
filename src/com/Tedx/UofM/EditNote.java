package com.Tedx.UofM;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class EditNote extends Activity {
	private EditText subject;
	private EditText noteBody;
	private Long rowNum = null;
	private String oldSubject = null;
	private String oldBody = null;
	
	
	protected void onCreate(Bundle savedInstanceState){
		 super.onCreate(savedInstanceState);
	     setContentView(R.layout.editnote);
	     subject = (EditText)findViewById(R.id.entry);
		 noteBody = (EditText)findViewById(R.id.entry2);
	     Bundle extras = getIntent().getExtras();
	        if (extras != null) {
	            String title = extras.getString(EditNoteDBAdapter.TITLE);
	            String body = extras.getString(EditNoteDBAdapter.BODY);
	            rowNum = extras.getLong(EditNoteDBAdapter.ROW);

	            if (title != null) {
	                subject.setText(title);
	                oldSubject = title;
	            }
	            if (body != null) {
	                noteBody.setText(body);
	                oldBody = body;
	            }
	        }
	}
	
	public void saveNote(View v){
		Bundle toSave = new Bundle();
		toSave.putString(EditNoteDBAdapter.TITLE, subject.getText().toString());
		toSave.putString(EditNoteDBAdapter.BODY, noteBody.getText().toString());
		if(rowNum != null){
			toSave.putLong(EditNoteDBAdapter.ROW, rowNum);
			
		}
		Intent intent = new Intent();
        intent.putExtras(toSave);
        setResult(RESULT_OK, intent);
        finish();
	}
	
	@Override
	public void onBackPressed(){
		//super.onDestroy();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Save Changes?")
		.setCancelable(false)
		.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				saveNote(noteBody);
				EditNote.this.finish();
			}
		})
		.setNegativeButton("No", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id){
				//dialog.cancel();
				subject.setText(oldSubject);
				noteBody.setText(oldBody);
				saveNote(noteBody);
				EditNote.this.finish();
			}
			
		});
		AlertDialog savePrompt = builder.create();
		savePrompt.show();
	}
	
}
