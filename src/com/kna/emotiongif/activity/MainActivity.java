package com.kna.emotiongif.activity;

import android.app.ActionBar;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.kna.emotiongif.R;
import com.kna.emotiongif.config.Config;
import com.kna.emotiongif.data.DataManager;
import com.kna.emotiongif.listener.LeftPanelSelecCallbackListener;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.PushService;

public class MainActivity extends Activity implements LeftPanelSelecCallbackListener, Config{

	private ActionBar actionBar;
	private SlidingPaneLayout slidingPaneLayout;
	
	private String title = "";
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch(item.getItemId()){
			case android.R.id.home:
				// app icon in action bar clicked; go home
				if(slidingPaneLayout.isOpen())	{
					slidingPaneLayout.closePane();
				}else{
					slidingPaneLayout.openPane();
				}
				return true;
				
				default:
					return super.onOptionsItemSelected(item);
		}
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        final View root = getLayoutInflater().inflate(R.layout.main_activity, null);
        setContentView(root);
        
        actionBar = getActionBar();
        
        slidingPaneLayout = SlidingPaneLayout.class.cast(root.findViewById(R.id.slidingpanelayout));
        actionBar.setDisplayHomeAsUpEnabled(true);
        
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
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void selectItem(int position) {
		String text = DataManager.CATEGORIES.get(position).getName();
		title = text;
		slidingPaneLayout.closePane();
	}

}
