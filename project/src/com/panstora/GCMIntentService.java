package com.panstora;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {
	private static int id = 1000;
	private String title;
	private String messages;
	private String url;
	@Override
	protected void onMessage(Context context, Intent intent) {
		
		Log.d("Panstora", "Got the message");
		
		String action = intent.getAction();
		
		Log.d("Panstora", action);
	    if ("com.google.android.c2dm.intent.RECEIVE".equals(action)) {          
	        title = intent.getStringExtra("title");
	        messages = intent.getStringExtra("message");
	        url = intent.getStringExtra("url");
	    }
	    
	    Intent i = new Intent(Intent.ACTION_VIEW);
	    i.setData(Uri.parse(url));
		
		
		NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
			.setSmallIcon(R.drawable.ic_launcher)
			.setContentTitle(title)
			.setContentText(messages);
		
		// The stack builder object will contain an artificial back stack for the
		// started Activity.
		// This ensures that navigating backward from the Activity leads out of
		// your application to the Home screen.
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		// Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(MainActivity.class);
		// Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(i);
		PendingIntent pendingIntent =
		        stackBuilder.getPendingIntent(
		            0,
		            PendingIntent.FLAG_UPDATE_CURRENT
		        );

		
		NotificationManager mNotificationManager =
			    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		// mId allows you to update the notification later on.
		notificationBuilder.setContentIntent(pendingIntent);
		mNotificationManager.notify(id, notificationBuilder.build());
		
		id++;
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
