package com.marakana.android.yamba;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StatusActivity extends Activity implements OnClickListener {
    private static final String TAG = "StatusActivity";
    
    private Twitter twitter;
    
    private EditText mStatusMsg;
    private Toast mToast;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mStatusMsg = (EditText) findViewById(R.id.edit_msg);
        Button buttonUpdate = (Button) findViewById(R.id.button_update);
        buttonUpdate.setOnClickListener(this);
        
        mToast = Toast.makeText(this, null, Toast.LENGTH_LONG);
        
//        System.setProperty("http.proxyHost", "host");
//        System.setProperty("http.proxyPort", "port_number");
//
//        If proxy requires authentication,
//
//        System.setProperty("http.proxyUser", "user");
//        System.setProperty("http.proxyPassword", "password");
        
        twitter = new Twitter("student", "password-bad");
        twitter.setAPIRootUrl("http://yamba.marakana.com/api");
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
			
			if (!TextUtils.isEmpty(msg)) {
				new PostToTwitter().execute(msg);
			}
			
			break;
		default:
			// We should never get here!
		}
	}
	
	private class PostToTwitter extends AsyncTask<String, Void, Integer> {

		@Override
		protected Integer doInBackground(String... params) {
			int result = R.string.post_status_success;
			
			try {
				twitter.setStatus(params[0]);
			} catch (TwitterException e) {
				Log.w(TAG, "Unable to post status message", e);
				result = R.string.post_status_fail;
			}
			
			return result;
		}

		@Override
		protected void onPostExecute(Integer result) {
			super.onPostExecute(result);
			mToast.setText(result);
			mToast.show();
		}
		
	}
}