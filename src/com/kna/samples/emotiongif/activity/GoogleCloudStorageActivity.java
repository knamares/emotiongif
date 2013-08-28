package com.kna.samples.emotiongif.activity;

import com.kna.samples.emotiongif.R;
import com.kna.samples.emotiongif.R.layout;
import com.kna.samples.emotiongif.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class GoogleCloudStorageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_google_cloud_storage);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.google_cloud_storage, menu);
		return true;
	}

}
