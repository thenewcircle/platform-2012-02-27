package com.marakana.android.yamba;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class UserPrefsActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Create the interface from the preference resource.
		addPreferencesFromResource(R.xml.user_prefs);
	}

}
