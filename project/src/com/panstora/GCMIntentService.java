package com.panstora;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {

	@Override
	protected void onMessage(Context context, Intent intent) {
		
	}

	@Override
	protected void onError(Context context, String errorId) {
		Log.e("GSM Panstora", "Error: " + errorId);
	}

	@Override
	protected void onRegistered(Context context, String registrationId) {
//		//Get the shared preferences
//		SharedPreferences storage = context.getSharedPreferences("DB", 0);
//		
//		//Open up a editor
//		SharedPreferences.Editor editor = storage.edit();
//		
//		//Store the registration id
//	    editor.putString("registration", registrationId);
//	    
//	    //Commit the changes
//	    editor.commit();
	}

	@Override
	protected void onUnregistered(Context context, String registrationId) {
//		Log.i("GSM Panstora", "User has unregistered");
//		
//		//Get the shared preferences
//		SharedPreferences storage = context.getSharedPreferences("DB", 0);
//		
//		//Open up a editor
//		SharedPreferences.Editor editor = storage.edit();
//		
//		//Clear out the registration id
//	    editor.remove("registration");
//	    
//	    //Commit the changes
//	    editor.commit();
	}

}
