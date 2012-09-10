package com.Tedx.UofM;

import android.content.Context;
import android.widget.Toast;

public class Error extends Throwable{
	String msg;
	Error(String msg_){
		msg = msg_;
	}
	public void handle(Context context){
		Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
		toast.show();
	}
}