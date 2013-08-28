package com.kna.samples.emotiongif.fragment;

import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshAttacher;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kna.samples.emotiongif.R;

public class FragmentMain extends Fragment implements PullToRefreshAttacher.OnRefreshListener{

	private PullToRefreshAttacher mPullToRefreshAttacher;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View myFragmentView = inflater.inflate(R.layout.fragment_main, container, false);
		return myFragmentView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

        // Here we create a PullToRefreshAttacher manually with the Options instance created above.
        mPullToRefreshAttacher = PullToRefreshLayout.getAttacher(this.getActivity(), R.id.ptr_layout);

        // Set Listener to know when a refresh should be started
        mPullToRefreshAttacher.setRefreshListener(this);	
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