package com.panstora;

import android.content.Context;
import android.content.Intent;

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
		throw new RuntimeException("Not implemented yet!");
	}

	@Override
	protected void onUnregistered(Context context, String registrationId) {
		throw new RuntimeException("Not implemented yet!");
	}

}
