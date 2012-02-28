package com.marakana.android.yamba;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StatusActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button buttonUpdate = (Button) findViewById(R.id.button_update);
        buttonUpdate.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.button_update:
			// Update button clicked
			break;
		default:
			// We should never get here!
		}
	}
}