package com.kna.samples.emotiongif.fragment;

import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshAttacher;
import android.app.Activity;
import android.app.ListFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.kna.samples.emotiongif.R;
import com.kna.samples.emotiongif.adapter.LeftPanelCategoryAdapter;
import com.kna.samples.emotiongif.data.DataManager;
import com.kna.samples.emotiongif.listener.LeftPanelSelecCallbackListener;

public class FragmentLeftPanel extends ListFragment implements PullToRefreshAttacher.OnRefreshListener {

	private LeftPanelSelecCallbackListener	leftPanelSelecCallbackListener;

	private PullToRefreshAttacher			mPullToRefreshAttacher;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		leftPanelSelecCallbackListener = (LeftPanelSelecCallbackListener) activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = super.onCreateView(inflater, container, savedInstanceState);

		view.setBackgroundColor(getResources().getColor(R.color.red_emotiongif));

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		LeftPanelCategoryAdapter leftPanelCategoryAdapter = new LeftPanelCategoryAdapter(getActivity(),
				android.R.layout.simple_list_item_1, DataManager.CATEGORIES);

		setListAdapter(leftPanelCategoryAdapter);

		/**
		 * Here we create a PullToRefreshAttacher manually without an Options
		 * instance. PullToRefreshAttacher will manually create one using
		 * default values.
		 */
		mPullToRefreshAttacher = new PullToRefreshAttacher(this.getActivity(), getListView());

		// Set Listener to know when a refresh should be started
		mPullToRefreshAttacher.setRefreshListener(this);

	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Toast.makeText(getActivity(), "Pulsado " + position, Toast.LENGTH_SHORT).show();
		leftPanelSelecCallbackListener.selectItem(position);
	}

	@Override
	public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
		super.onInflate(activity, attrs, savedInstanceState);
	}

	@Override
	public void onRefreshStarted(View view) {
		/**
		 * Simulate Refresh with 4 seconds sleep
		 */
		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				super.onPostExecute(result);

				// Notify PullToRefreshAttacher that the refresh has finished
				mPullToRefreshAttacher.setRefreshComplete();
			}
		}.execute();

	}

}
