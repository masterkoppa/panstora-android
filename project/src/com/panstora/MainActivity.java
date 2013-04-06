package com.panstora;

import java.util.List;

import com.google.android.gcm.GCMRegistrar;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.provider.Settings.Secure;

public class MainActivity extends Activity {

	private WebView myWebView;
	
	private static final String SENDER_ID = "794813012353";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		GCMRegistrar.checkDevice(this);
		GCMRegistrar.checkManifest(this);
		final String regId = GCMRegistrar.getRegistrationId(this);
		
		if (regId.equals("")) {
		  GCMRegistrar.register(this, SENDER_ID);
		} else {
		  Log.v("MainActivity", "Already registered");
		}
		
		Uri data = getIntent().getData();
		//Get the URI I read in
		if(data != null){
			List<String> params = data.getPathSegments();
			
			Log.d("MainActivity", "Data URI: " + data.toString());
			
			for(String param : params){
				Log.d("MainActivity", "Params: " + param);
			}
		}else{
			Log.d("MainActivity", "Closing Application, nothing to see here folks!");
			
			//If I don't have a valid code, DIE!
			finish();
		}
		
		//Get the shared preferences
		SharedPreferences storage = this.getSharedPreferences("DB", 0);
		
		String dev_id = "?dev_id=" + storage.getString("registration", "0");
		
		myWebView = (WebView) findViewById(R.id.webView1);
		WebSettings webSettings = myWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		myWebView.setWebViewClient(new WebViewClient()); 
		myWebView.loadUrl(data.toString() + dev_id);// ?dev_id=
		
		String android_id = Secure.getString(getBaseContext().getContentResolver(),
                Secure.ANDROID_ID);
		
		Log.d("MainActivity", "Private android id = " + android_id);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}
	
	
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		//Force the back key to work
		
	    // Check if the key event was the Back button and if there's history
	    if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
	        myWebView.goBack();
	        return true;
	    }
	    // If it wasn't the Back key or there's no web page history, bubble up to the default
	    // system behavior (probably exit the activity)
	    return super.onKeyDown(keyCode, event);
	}
}
