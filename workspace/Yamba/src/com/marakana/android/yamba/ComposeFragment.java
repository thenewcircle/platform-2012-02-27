package com.marakana.android.yamba;

import winterwell.jtwitter.TwitterException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ComposeFragment extends Fragment implements OnClickListener {
	private static final String TAG = "ComposeFragment";

    private EditText mStatusMsg;
    private Toast mToast;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// Inflate the ComposeFragment layout
		View top = inflater.inflate(R.layout.compose_fragment, container, false);
        
		// Find our views and initialize them.
        mStatusMsg = (EditText) top.findViewById(R.id.edit_msg);
        Button buttonUpdate = (Button) top.findViewById(R.id.button_update);
        buttonUpdate.setOnClickListener(this);
        
        // Create and cache a Toast. We use the Application's context because
        // this fragment -- and the Toast -- can exist across multiple
        // Activity instances and we don't want to leak a reference.
        mToast = Toast.makeText(getActivity().getApplicationContext(), null, Toast.LENGTH_LONG);
        
		return top;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.button_update:
			// Update button clicked
			Log.v(TAG, "Button clicked");
			
			// Read the content of the EditText and clear it.
			String msg = mStatusMsg.getText().toString();
			Log.v(TAG, "User entered: " + msg);
			mStatusMsg.setText("");
			
			// Attempt to post the message if the user entered any text.
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
			// Our return result, initialized to indicate a successful post
			int result = R.string.post_status_success;
			
			try {
				// Get access to our custom Application object
				YambaApplication app = YambaApplication.getInstance();
				
				// Access the Twitter object and post the status
				app.getTwitter().setStatus(params[0]);
			} catch (TwitterException e) {
				
				// If the post fails, log it and return a failure indication
				Log.w(TAG, "Unable to post status message", e);
				result = R.string.post_status_fail;
			}
			
			return result;
		}

		@Override
		protected void onPostExecute(Integer result) {
			super.onPostExecute(result);
			
			// Configure the Toast with the result message and show it.
			mToast.setText(result);
			mToast.show();
		}
		
	}
}
