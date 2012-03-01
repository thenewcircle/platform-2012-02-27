package com.marakana.android.yamba;

import java.util.Date;
import java.util.List;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;
import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class UpdaterService extends IntentService {
	
	private static final String TAG = "UpdaterService";
	
	public UpdaterService() {
		super(TAG);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// Process the Intent
		YambaApplication app = YambaApplication.getInstance();
		try {
			List<Twitter.Status> timeline = app.getTwitter().getHomeTimeline();
			for (Twitter.Status status: timeline) {
				String user = status.user.name;
				String msg = status.text;
				long id = status.id;
				Date createdAt = status.createdAt;
				Log.v(TAG, user + " posted at " + createdAt + ": " + msg);
			}
		} catch (TwitterException e) {
			Log.w(TAG, "Failed to fetch timeline data");
		}
	}

}
