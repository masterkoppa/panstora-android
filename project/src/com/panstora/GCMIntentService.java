package com.panstora;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {

	@Override
	protected void onMessage(Context context, Intent intent) {
		throw new RuntimeException("Not implemented yet!");
	}

	@Override
	protected void onError(Context context, String errorId) {
		throw new RuntimeException("Not implemented yet!");
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
		throw new RuntimeException("Not implemented yet!");
	}

}
