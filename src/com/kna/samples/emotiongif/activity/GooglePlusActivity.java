package com.kna.samples.emotiongif.activity;

import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.plus.PlusClient;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.PersonBuffer;
import com.kna.samples.emotiongif.R;


public class GooglePlusActivity extends Activity implements ConnectionCallbacks, OnConnectionFailedListener,
		PlusClient.OnPeopleLoadedListener {

	private SignInButton		buttonSignIn;
	private PlusClient			mPlusClient;
	private ConnectionResult	connectionResult;

	private RelativeLayout		relativeLayout;
	private LinearLayout		linearLayoutContacs;

	private TextView			textViewName;
	private TextView			textViewUrl;
	private TextView			textViewEmail;
	private TextView			textViewGender;
	private TextView			textViewBday;
	private Button				buttonLogOut;
	private ImageView			imageView;

	private static final int	REQUEST_CODE_RESOLVE_ERR	= 9000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_google_plus);

		setBinder();

		setListener();
	}

	private void setBinder() {
		mPlusClient = new PlusClient.Builder(this, this, this).setVisibleActivities(
				"http://schemas.google.com/AddActivity", "http://schemas.google.com/ListenActivity").build();

		buttonSignIn = (SignInButton) findViewById(R.id.gButtonSignIn);
		relativeLayout = (RelativeLayout) findViewById(R.id.gRelativeUser);
		linearLayoutContacs = (LinearLayout) findViewById(R.id.gLinearContact);
		textViewName = (TextView) findViewById(R.id.gTextName);
		textViewUrl = (TextView) findViewById(R.id.gTextUrl);
		textViewEmail = (TextView) findViewById(R.id.gTextEmail);
		textViewGender = (TextView) findViewById(R.id.gTextGender);
		textViewBday = (TextView) findViewById(R.id.gTextBday);
		buttonLogOut = (Button) findViewById(R.id.gButtonLogOut);
		imageView = (ImageView) findViewById(R.id.gImageUser);
	}

	private void setListener() {
		buttonSignIn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!mPlusClient.isConnected() && connectionResult != null) {
					try {
						connectionResult.startResolutionForResult(GooglePlusActivity.this, REQUEST_CODE_RESOLVE_ERR);
					} catch (SendIntentException e) {
						connectionResult = null;
						mPlusClient.connect();
					}
				}
			}

		});

		buttonLogOut.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO:
				Toast.makeText(GooglePlusActivity.this, "TODO", Toast.LENGTH_SHORT).show();
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		mPlusClient.connect();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		mPlusClient.disconnect();
	}

	// Implements OnConnectionFailedListener
	@Override
	public void onConnectionFailed(ConnectionResult result) {
		// TODO Auto-generated method stub
		connectionResult = result;

	}

	// Implements ConnectionCallbacks
	@Override
	public void onConnected(Bundle arg0) {
		// TODO Auto-generated method stub
		Person user = mPlusClient.getCurrentPerson();

		// show The Image
		new DownloadImageTask(imageView).execute(user.getImage().getUrl());

		textViewName.setText(user.getDisplayName());
		textViewUrl.setText(user.getUrl());
		textViewBday.setText(user.getBirthday());
		textViewEmail.setText(user.getEmails() != null ? user.getEmails().get(0).getValue() : "");
		textViewGender.setText(user.getGender() == 0 ? "Male" : "Female");

		buttonSignIn.setVisibility(View.GONE);
		relativeLayout.setVisibility(View.VISIBLE);

		mPlusClient.loadPeople(this, Person.Collection.VISIBLE);

	}

	// Implements ConnectionCallbacks
	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE_RESOLVE_ERR && resultCode == RESULT_OK) {
			connectionResult = null;
			mPlusClient.connect();
		}
	}

	@Override
	public void onPeopleLoaded(ConnectionResult status, PersonBuffer personBuffer, String nextPageToken) {
		if (status.getErrorCode() == ConnectionResult.SUCCESS) {
			try {
				linearLayoutContacs.removeAllViews();
				
				int size = personBuffer.getCount();

				for (int j = 0; j < size; j++) {
					Log.d("__EMOTIONGIF", "Display Name: " + personBuffer.get(j).getDisplayName());

					TextView textViewContact = new TextView(GooglePlusActivity.this);
					textViewContact.setText("Contact " + j + ": " + personBuffer.get(j).getDisplayName());

					linearLayoutContacs.addView(textViewContact);

				}
			} finally {
				personBuffer.close();
			}
		} else {
			Log.e("__EMOTIONGIF", "Error listing people: " + status.getErrorCode());
		}
	}

	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageView	bmImage;

		public DownloadImageTask(ImageView bmImage) {
			this.bmImage = bmImage;
		}

		protected Bitmap doInBackground(String... urls) {
			String urldisplay = urls[0];
			Bitmap mIcon11 = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return mIcon11;
		}

		protected void onPostExecute(Bitmap result) {
			bmImage.setImageBitmap(result);
		}
	}

}
