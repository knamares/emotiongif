package com.kna.samples.emotiongif.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;


import com.kna.samples.emotiongif.R;
import com.kna.samples.emotiongif.config.Config;
import com.kna.samples.emotiongif.data.DataManager;
import com.kna.samples.emotiongif.listener.LeftPanelSelecCallbackListener;

public class DrawerLayoutActivity extends Activity implements LeftPanelSelecCallbackListener, Config {
	private ActionBar				actionBar;
	private DrawerLayout			drawerLayout;
	private ActionBarDrawerToggle	actionBarDrawerToggle;
	private CharSequence			mDrawerTitle;
	private CharSequence			mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final View root = getLayoutInflater().inflate(R.layout.activity_drawer_layout, null);
		setContentView(root);

		mTitle = mDrawerTitle = getTitle();

		actionBar = getActionBar();

		// enable ActionBar app icon to behave as action to toggle nav drawer
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);

		drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

		// set a custom shadow that overlays the main content when the drawer
		// opens
		drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu();
			}
		};
		drawerLayout.setDrawerListener(actionBarDrawerToggle);

	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		actionBar.setTitle(mTitle);
	}

	@Override
	public void selectItem(int position) {
		String text = DataManager.CATEGORIES.get(position).getName();
		setTitle(text);
		drawerLayout.closeDrawer(Gravity.START);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		actionBarDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		actionBarDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action buttons
		switch (item.getItemId()) {

			default:
				return super.onOptionsItemSelected(item);
		}
	}

}
