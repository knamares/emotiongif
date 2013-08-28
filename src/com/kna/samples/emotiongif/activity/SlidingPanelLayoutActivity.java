package com.kna.samples.emotiongif.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.MenuItem;
import android.view.View;

import com.kna.samples.emotiongif.R;
import com.kna.samples.emotiongif.config.Config;
import com.kna.samples.emotiongif.data.DataManager;
import com.kna.samples.emotiongif.listener.LeftPanelSelecCallbackListener;

public class SlidingPanelLayoutActivity extends Activity implements LeftPanelSelecCallbackListener, Config {

	private ActionBar			actionBar;
	private SlidingPaneLayout	slidingPaneLayout;

	private String				title	= "";

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				return true;

			default:
				return super.onOptionsItemSelected(item);
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final View root = getLayoutInflater().inflate(R.layout.sliding_panel_layout_activity, null);
		setContentView(root);

		actionBar = getActionBar();

		slidingPaneLayout = SlidingPaneLayout.class.cast(root.findViewById(R.id.slidingpanelayout));
		actionBar.setDisplayHomeAsUpEnabled(true);

		title = (String) actionBar.getTitle();

		slidingPaneLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {

		
			@Override
			public void onPanelSlide(View view, float v) {
			}

			@Override
			public void onPanelOpened(View view) {

				switch (view.getId()) {
					case R.id.fragment_secondpane:
						title = (String) actionBar.getTitle();
						actionBar.setTitle("Select Category");
						break;
					default:
						break;
				}
			}

			@Override
			public void onPanelClosed(View view) {

				switch (view.getId()) {
					case R.id.fragment_secondpane:
						actionBar.setTitle(title);
						break;
					default:
						break;
				}
			}

		});

	}

	@Override
	public void selectItem(int position) {
		String text = DataManager.CATEGORIES.get(position).getName();
		title = text;
		slidingPaneLayout.closePane();
	}

}
