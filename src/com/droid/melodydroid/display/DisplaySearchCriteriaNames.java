package com.droid.melodydroid.display;

import com.droid.melodydroid.R;
import com.droid.melodydroid.core.DatabaseSynchronizationService;
import com.droid.melodydroid.criteria.SearchCriteria;
import com.droid.melodydroid.helper.MelodyDroidHelper;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class DisplaySearchCriteriaNames extends Activity {

	private static final String KEY_FIRST_RUN = "firstTime";
	private static final int ITEM_ID_1 = 1;
	private static final int ITEM_ID_2 = 2;
	private static final int ITEM_ID_3 = 3;
	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_MAX_OFF_PATH = 250;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	private GestureDetector gestureDetector;
	View.OnTouchListener gestureListener;
	AnimationDrawable guitarAnimation;
	SampleView testView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
			setContentView(R.layout.main);
		} else {
			setContentView(R.layout.main_landscape);
		}

		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		if (!prefs.getBoolean(KEY_FIRST_RUN, false)) {
			// run your one time code
			DatabaseSynchronizationService dbSyncService = DatabaseSynchronizationService
					.getInstance(this);
			dbSyncService.findMelodiesFromSourceAndSynchDatabase();

			SharedPreferences.Editor editor = prefs.edit();
			editor.putBoolean(KEY_FIRST_RUN, true);
			editor.commit();
		}

		final Intent queryIntent = getIntent();
		onNewIntent(queryIntent);
	}

	class MyGestureDetector extends SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			try {
				if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
					return false;
				// right to left swipe
				if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
						&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {

				} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
						&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					if (Renderer.renderDisplayControl) {
						Intent displayPlaybackControls = new Intent(
								DisplaySearchCriteriaNames.this,
								DisplayPlaybackControls.class);
						displayPlaybackControls.putExtra("swipeBack",
								"DisplaySearchCriteriaNames");
						startActivity(displayPlaybackControls);
					}
				}
			} catch (Exception e) {
				// nothing
			}
			return false;
		}

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, ITEM_ID_1, 1, R.string.search).setIcon(
				R.drawable.ic_menu_search);
		menu.add(0, ITEM_ID_2, 2, R.string.play_list).setIcon(
				R.drawable.playlist);
		menu.add(0, ITEM_ID_3, 3, R.string.dbsync).setIcon(R.drawable.syncicon);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case ITEM_ID_1:
			onSearchRequested();
			return true;
		case ITEM_ID_2:
			Intent displayPlaylists = new Intent(this, DisplayPlaylists.class);
			startActivity(displayPlaylists);
			return true;
		case ITEM_ID_3:
			DatabaseSynchronizationService dbSyncService = DatabaseSynchronizationService
					.getInstance(DisplaySearchCriteriaNames.this);
			dbSyncService.findMelodiesFromSourceAndSynchDatabase();
			return true;
		}

		return false; // should never happen
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);

		RelativeLayout myRelativeLayout = (RelativeLayout) findViewById(R.id.myDynamicLayout);
		myRelativeLayout.removeAllViews();
		// myRelativeLayout.setGravity(Gravity.BOTTOM);

		if (MelodyDroidHelper.isSearchOptionSelected()) {
			Button clearSearch = new Button(this);
			clearSearch.setText("Clear");
			// clearSearch.setMaxWidth(90);
			// clearSearch.setMaxHeight(55);

			RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			// ((LinearLayout.LayoutParams)
			// clearSearch.getLayoutParams()).gravity = Gravity.BOTTOM;
			// ((LinearLayout.LayoutParams)
			// clearSearch.getLayoutParams()).weight = 1.0f;
			layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			// layoutParams.weight = 1.0f;
			clearSearch.setLayoutParams(layoutParams);
			clearSearch.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
			myRelativeLayout.addView(clearSearch);
			clearSearch.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					SearchCriteria.clearAll();
					Intent displaySearchCriteriaNames = new Intent(
							DisplaySearchCriteriaNames.this,
							DisplaySearchCriteriaNames.class);
					startActivity(displaySearchCriteriaNames);
				}
			});

			clearSearch.setOnTouchListener(gestureListener);
		}

		// // Gesture detection
		gestureDetector = new GestureDetector(new MyGestureDetector());
		gestureListener = new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (gestureDetector.onTouchEvent(event)) {
					return true;
				}
				return false;
			}
		};

		SampleView testView = (SampleView) findViewById(R.id.testView);
		testView.setOnTouchListener(gestureListener);

	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// ignore orientation change

		super.onConfigurationChanged(newConfig);
		// setContentView(R.layout.main_landscape);
	}

	// @Override
	// public void onWindowFocusChanged(boolean hasFocus) {
	// // TODO Auto-generated method stub
	// super.onWindowFocusChanged(hasFocus);
	//
	// if (hasFocus) {
	// LinearLayout ll = (LinearLayout) findViewById(R.id.organizationLayout);
	// guitarAnimation = (AnimationDrawable) ll.getBackground();
	// guitarAnimation.start();
	// }
	//
	// }

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

	}

	public static int getScreenHeight() {
		return Resources.getSystem().getDisplayMetrics().heightPixels;
	}

}
