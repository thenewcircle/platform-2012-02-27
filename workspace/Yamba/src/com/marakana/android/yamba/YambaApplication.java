package com.marakana.android.yamba;

import winterwell.jtwitter.Twitter;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;
import android.util.Log;

public class YambaApplication extends Application
		implements OnSharedPreferenceChangeListener {
	private static final String TAG = "YambaApplication";
	
	private static YambaApplication instance;

    private Twitter twitter;
    private SharedPreferences prefs;
    private String mPrefUserKey;
    private String mPrefPasswordKey;
    private String mPrefSiteUrlKey;
    
	/**
	 * Returns a typed reference to the custom Application object.
	 * This eliminated the need to caste the return value of
	 * getApplication() elsewhere in our code.
	 * 
	 * @return	A reference to the custom Application object
	 */
	public static YambaApplication getInstance() {
		return instance;
	}

	/** 
	 * Initializes a custom Application subclass for the Yamba application.
	 * 
	 * @see android.app.Application#onCreate()
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		
		// Cache a static reference to the custom application instance.
		instance = this;
		
		// Register the application as a listener to preference changes.
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		prefs.registerOnSharedPreferenceChangeListener(this);
		
		// Read our preference key strings from our string resources.
		mPrefUserKey = getString(R.string.pref_user_key);
		mPrefPasswordKey = getString(R.string.pref_password_key);
		mPrefSiteUrlKey = getString(R.string.pref_site_url_key);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		// At this point, all preferences related to the Twitter object.
		// If any changes, release the old Twitter object.
		synchronized (this) {
			twitter = null;
		}
	}

	/**
	 * Performs a "lazy" initialization of the application's Twitter
	 * object. The application actually caches the reference to the
	 * Twitter object only until the user changes the preference
	 * settings affecting the object, at which point it is released.
	 * The next call to getTwitter() re-initialized the Twitter object.
	 * 
	 * @return	Shared Twitter object reflecting current preference settings
	 */
	public synchronized Twitter getTwitter() {
		if (twitter == null) {
			//	If we need to get past a web proxy, add:
			//
			//	System.setProperty("http.proxyHost", "host");
			//	System.setProperty("http.proxyPort", "port_number");
			//
			//	If the web proxy requires authentication,
			//
			//	System.setProperty("http.proxyUser", "user");
			//	System.setProperty("http.proxyPassword", "password");
	        
			Log.v(TAG, "(Re-)initializing Twitter object");
			
			// Read latest preference values.
			String user = prefs.getString(mPrefUserKey, null);
			String password = prefs.getString(mPrefPasswordKey, null);
			String url = prefs.getString(mPrefSiteUrlKey, null);
			
			// Create a new Twitter object using the preference values.
	        twitter = new Twitter(user, password);
	        twitter.setAPIRootUrl(url);
		}
		return twitter;
	}

}
