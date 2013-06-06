package com.kna.emotiongif.fragment;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.kna.emotiongif.R;
import com.kna.emotiongif.adapter.LeftPanelCategoryAdapter;
import com.kna.emotiongif.data.DataManager;
import com.kna.emotiongif.listener.LeftPanelSelecCallbackListener;

public class FragmentLeftPanel extends ListFragment {

	private LeftPanelSelecCallbackListener leftPanelSelecCallbackListener;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		leftPanelSelecCallbackListener = (LeftPanelSelecCallbackListener) activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = super.onCreateView(inflater, container, savedInstanceState);

		view.setBackgroundColor(R.color.red_emotiongif);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		LeftPanelCategoryAdapter leftPanelCategoryAdapter = new LeftPanelCategoryAdapter(getActivity(), android.R.layout.simple_list_item_1,
				DataManager.CATEGORIES);

		setListAdapter(leftPanelCategoryAdapter);

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

}
