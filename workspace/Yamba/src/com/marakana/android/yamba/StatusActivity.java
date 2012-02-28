package com.marakana.android.yamba;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class StatusActivity extends Activity implements OnClickListener {
    private static final String TAG = "StatusActivity";
    
    private EditText mStatusMsg;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mStatusMsg = (EditText) findViewById(R.id.edit_msg);
        Button buttonUpdate = (Button) findViewById(R.id.button_update);
        buttonUpdate.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.button_update:
			// Update button clicked
			Log.v(TAG, "Button clicked");
			String msg = mStatusMsg.getText().toString();
			Log.v(TAG, "User entered: " + msg);
			mStatusMsg.setText("");
			break;
		default:
			// We should never get here!
		}
	}
}