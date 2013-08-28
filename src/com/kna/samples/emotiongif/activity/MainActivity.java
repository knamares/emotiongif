package com.kna.samples.emotiongif.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kna.samples.emotiongif.R;
import com.kna.samples.emotiongif.config.Config;

public class MainActivity extends Activity implements Config{

	private Button buttonSlidingPanelLayout;
	private Button buttonDrawerLayout;
	private Button buttonGooglePlusSignIn;
	private Button buttonGooglePlayGames;
	private Button buttonFusedLocation;
	private Button buttonGoogleCloudStorage;
	private Button buttonGoogleMapsV2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Set object from the view
		setBinder();
		
		//Set listener for object
		setListener();
	}
	

	private void setBinder() {
		buttonSlidingPanelLayout = (Button) findViewById(R.id.buttonSlidingPanelLayout);
		buttonDrawerLayout = (Button) findViewById(R.id.buttonDrawerLayout);
		buttonGooglePlusSignIn = (Button) findViewById(R.id.buttonGooglePlusSignIn);
		buttonGooglePlayGames = (Button) findViewById(R.id.buttonGooglePlayGames);
		buttonFusedLocation = (Button) findViewById(R.id.buttonFusedLocation);
		buttonGoogleCloudStorage = (Button) findViewById(R.id.buttonGoogleCloudStorage);
		buttonGoogleMapsV2 = (Button) findViewById(R.id.buttonGoogleMapsV2);
	}


	private void setListener() {
		buttonSlidingPanelLayout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, SlidingPanelLayoutActivity.class));
			}
		});

		buttonDrawerLayout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, DrawerLayoutActivity.class));
			}
		});

		buttonGooglePlusSignIn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, GooglePlusActivity.class));
			}
		});

		buttonGooglePlayGames.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, GooglePlayGamesActivity.class));
			}
		});

		buttonFusedLocation.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, FusedLocationActivity.class));
			}
		});

		buttonGoogleCloudStorage.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, GoogleCloudStorageActivity.class));
			}
		});

		buttonGoogleMapsV2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, GoogleMapsV2Activity.class));
			}
		});
		
	}
	



}
