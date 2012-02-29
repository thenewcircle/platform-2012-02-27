package com.marakana.android.yamba;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

public class StatusActivity extends FragmentActivity {
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Let the base activity class initialize the options menu.
		// This also allows fragments to include their own items
		// in the options menu.
		super.onCreateOptionsMenu(menu);
		
		// Add the activity's menu items to the options menu.
		getMenuInflater().inflate(R.menu.options_main, menu);
		
		// Indicate that we do have an options menu.
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.menu_preferences:
			
			// The user selected our activity's Settings item.
			// Start the UserPrefsActivity.
			Intent intent = new Intent(this, UserPrefsActivity.class);
			startActivity(intent);
			
			// Return true to indicate we handled the item.
			return true;
		default:
			
			// It's not a menu item we recognize. Give it to the
			// base class to process. This also gives fragments
			// the opportunity to handle their menu items.
			return super.onOptionsItemSelected(item);
		}
	}

}