package com.panstora;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {
	private static int id = 1000;
	private String title;
	private String messages;
	@Override
	protected void onMessage(Context context, Intent intent) {
		
		Log.d("Panstora", "Got the message");
		
		String action = intent.getAction();
	    if ("com.google.android.c2dm.intent.RECEIVE".equals(action)) {          
	        String message = intent.getStringExtra("data"); 
	        parse_json_add(message);
	    }
		
		
		NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
			.setSmallIcon(R.drawable.ic_launcher)
			.setContentTitle(title)
			.setContentText(messages);
		
		
		
		//notificationBuilder.build();
		
		NotificationManager mNotificationManager =
			    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		// mId allows you to update the notification later on.
		mNotificationManager.notify(id, notificationBuilder.build());
		
		id++;
		
		
		
	}
	
	private void parse_json_add(String text) {
	    if (text!= null) 
	    { 
	         JSONObject temp;
			try {
				temp = new JSONObject(text);
				title = temp.get("title").toString();
				messages = temp.get("message").toString();
			} catch (JSONException e) {
				e.printStackTrace();
			}    
	    }
	}

	@Override
	protected void onError(Context context, String errorId) {
		Log.e("GSM Panstora", "Error: " + errorId);
	}

	@Override
	protected void onRegistered(Context context, String registrationId) {
		//Get the shared preferences
		SharedPreferences storage = context.getSharedPreferences("DB", 0);
		
		//Open up a editor
		SharedPreferences.Editor editor = storage.edit();
		
		//Store the registration id
	    editor.putString("registration", registrationId);
	    
	    //Commit the changes
	    editor.commit();
	}

	@Override
	protected void onUnregistered(Context context, String registrationId) {
		Log.i("GSM Panstora", "User has unregistered");
		
		//Get the shared preferences
		SharedPreferences storage = context.getSharedPreferences("DB", 0);
		
		//Open up a editor
		SharedPreferences.Editor editor = storage.edit();
		
		//Clear out the registration id
	    editor.remove("registration");
	    
	    //Commit the changes
	    editor.commit();
	}

}
