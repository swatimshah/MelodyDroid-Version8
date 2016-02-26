package com.droid.melodydroid.display;

import com.droid.melodydroid.criteria.SearchCriteria;
import com.droid.melodydroid.helper.MelodyDroidHelper;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class SampleView extends View {

	private Context context;

	private Paint mPaint;
	private float[] mPos;
	// private static String mode = "portrait";

	private Path mPathAlbum;
	private Path mPathArtist;
	private Path mPathGenre;
	private Path mPathBelowTitle;
	private Path mPathBelowYear;
	private Path mPathBelowMood;
	private Paint mPathPaint;

	private static final String POSTEXT = "Positioned";

	private static void makePath(Path p, String text) {

		if ("Album".equals(text)) {
			p.moveTo(0, (int) (getScreenHeight() * 0.1));
			p.cubicTo((int) (getScreenWidth() * 0.5), 0,
					(int) (getScreenWidth() * 0.6),
					(int) (getScreenHeight() * 0.2),
					(int) (getScreenWidth() * 0.7),
					(int) (getScreenHeight() * 0.1));
		}

		if ("Artist".equals(text)) {
			p.moveTo(0, (int) (getScreenHeight() * 0.2));
			p.cubicTo((int) (getScreenWidth() * 0.5),
					(int) (getScreenHeight() * 0.1),
					(int) (getScreenWidth() * 0.6),
					(int) (getScreenHeight() * 0.3),
					(int) (getScreenWidth() * 0.7),
					(int) (getScreenHeight() * 0.2));
		}

		if ("Genre".equals(text)) {
			p.moveTo(0, (int) (getScreenHeight() * 0.3));
			p.cubicTo((int) (getScreenWidth() * 0.5),
					(int) (getScreenHeight() * 0.2),
					(int) (getScreenWidth() * 0.6),
					(int) (getScreenHeight() * 0.4),
					(int) (getScreenWidth() * 0.7),
					(int) (getScreenHeight() * 0.3));
		}

		if ("Title".equals(text)) {
			p.moveTo((int) (getScreenWidth() * 0.3),
					(int) (getScreenHeight() * 0.45));
			p.cubicTo((int) (getScreenWidth() * 0.8),
					(int) (getScreenHeight() * 0.35),
					(int) (getScreenWidth() * 0.9),
					(int) (getScreenHeight() * 0.55), (int) (getScreenWidth()),
					(int) (getScreenHeight() * 0.45));
		}

		if ("Year".equals(text)) {
			p.moveTo((int) (getScreenWidth() * 0.3),
					(int) (getScreenHeight() * 0.55));
			p.cubicTo((int) (getScreenWidth() * 0.8),
					(int) (getScreenHeight() * 0.45),
					(int) (getScreenWidth() * 0.9),
					(int) (getScreenHeight() * 0.65), (int) (getScreenWidth()),
					(int) (getScreenHeight() * 0.55));
		}

		if ("Mood".equals(text)) {
			p.moveTo((int) (getScreenWidth() * 0.3),
					(int) (getScreenHeight() * 0.65));
			p.cubicTo((int) (getScreenWidth() * 0.8),
					(int) (getScreenHeight() * 0.55),
					(int) (getScreenWidth() * 0.9),
					(int) (getScreenHeight() * 0.75), (int) (getScreenWidth()),
					(int) (getScreenHeight() * 0.65));
		}

	}

	private float[] buildTextPositions(String text, float y, Paint paint) {
		float[] widths = new float[text.length()];
		// initially get the widths for each char
		int n = paint.getTextWidths(text, widths);
		// now popuplate the array, interleaving spaces for the Y values
		float[] pos = new float[n * 2];
		float accumulatedX = 0;
		for (int i = 0; i < n; i++) {
			pos[i * 2 + 0] = accumulatedX;
			pos[i * 2 + 1] = y;
			accumulatedX += widths[i];
		}
		return pos;
	}

	public SampleView(Context context) {
		super(context);
		this.context = context;
		setFocusable(true);

		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setTextSize(30);
		mPaint.setTypeface(Typeface.SERIF);

		mPos = buildTextPositions(POSTEXT, 0, mPaint);

		mPathAlbum = new Path();
		makePath(mPathAlbum, "Album");
		mPathArtist = new Path();
		makePath(mPathArtist, "Artist");
		mPathGenre = new Path();
		makePath(mPathGenre, "Genre");
		mPathBelowTitle = new Path();
		makePath(mPathBelowTitle, "Title");
		mPathBelowYear = new Path();
		makePath(mPathBelowYear, "Year");
		mPathBelowMood = new Path();
		makePath(mPathBelowMood, "Mood");

		mPathPaint = new Paint();
		mPathPaint.setAntiAlias(true);
		mPathPaint.setColor(Color.GRAY);//mPathPaint.setColor(0x800000FF);
		mPathPaint.setStyle(Paint.Style.STROKE);

	}

	public SampleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;

		setFocusable(true);

		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setTextSize(30);
		mPaint.setTypeface(Typeface.SERIF);

		mPos = buildTextPositions(POSTEXT, 0, mPaint);

		mPathAlbum = new Path();
		makePath(mPathAlbum, "Album");
		mPathArtist = new Path();
		makePath(mPathArtist, "Artist");
		mPathGenre = new Path();
		makePath(mPathGenre, "Genre");
		mPathBelowTitle = new Path();
		makePath(mPathBelowTitle, "Title");
		mPathBelowYear = new Path();
		makePath(mPathBelowYear, "Year");
		mPathBelowMood = new Path();
		makePath(mPathBelowMood, "Mood");

		mPathPaint = new Paint();
		mPathPaint.setAntiAlias(true);
		mPathPaint.setColor(Color.GRAY);//mPathPaint.setColor(0x80FFFFFF);
		mPathPaint.setStyle(Paint.Style.STROKE);

	}

	public SampleView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;

		setFocusable(true);

		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setTextSize(30);
		mPaint.setTypeface(Typeface.SERIF);

		mPos = buildTextPositions(POSTEXT, 0, mPaint);

		mPathAlbum = new Path();
		makePath(mPathAlbum, "Album");
		mPathArtist = new Path();
		makePath(mPathArtist, "Artist");
		mPathGenre = new Path();
		makePath(mPathGenre, "Genre");
		mPathBelowTitle = new Path();
		makePath(mPathBelowTitle, "Title");
		mPathBelowYear = new Path();
		makePath(mPathBelowYear, "Year");
		mPathBelowMood = new Path();
		makePath(mPathBelowMood, "Mood");

		mPathPaint = new Paint();
		mPathPaint.setAntiAlias(true);
		mPathPaint.setColor(Color.GRAY);//mPathPaint.setColor(0x800000FF);
		mPathPaint.setStyle(Paint.Style.STROKE);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		// canvas.drawColor(Color.WHITE);

		Paint p = mPaint;

		// now draw the text on path

		// canvas.translate(0, DY);
		canvas.drawPath(mPathAlbum, mPathPaint);
		p.setTextAlign(Paint.Align.LEFT);
		p.setTextSize(35);
		mPathPaint.setColor(Color.GRAY);//p.setColor(0x80FFFFFF);
		canvas.drawTextOnPath("Album: " + SearchCriteria.getAlbum(), mPathAlbum, 0, 0, p);

		// canvas.translate(0, DY * 1.5f);
		canvas.drawPath(mPathArtist, mPathPaint);
		p.setTextAlign(Paint.Align.LEFT);
		p.setTextSize(35);
		mPathPaint.setColor(Color.GRAY);//p.setColor(0x80FFFFFF);
		canvas.drawTextOnPath("Artist: " + SearchCriteria.getArtist(), mPathArtist, 0, 0, p);

		// canvas.translate(0, DY * 1.5f);
		canvas.drawPath(mPathGenre, mPathPaint);
		p.setTextAlign(Paint.Align.LEFT);
		p.setTextSize(35);
		mPathPaint.setColor(Color.GRAY);//p.setColor(0x80FFFFFF);
		canvas.drawTextOnPath("Genre: " + SearchCriteria.getGenre(), mPathGenre, 0, 0, p);

		// ----------------------------------------------

		// canvas.translate(0, DY_BELOW);
		canvas.drawPath(mPathBelowTitle, mPathPaint);
		p.setTextAlign(Paint.Align.RIGHT);
		p.setTextSize(35);
		mPathPaint.setColor(Color.GRAY);//p.setColor(0x80FFFFFF);
		canvas.drawTextOnPath("Title: " + SearchCriteria.getTitle(), mPathBelowTitle, 0, 0, p);

		// canvas.translate(0, DY_BELOW * 0.05f);
		canvas.drawPath(mPathBelowYear, mPathPaint);
		p.setTextAlign(Paint.Align.RIGHT);
		p.setTextSize(35);
		mPathPaint.setColor(Color.GRAY);//p.setColor(0x80FFFFFF);
		canvas.drawTextOnPath("Year: " + SearchCriteria.getYear(), mPathBelowYear, 0, 0, p);

		// canvas.translate(0, DY_BELOW * 0.05f);
		canvas.drawPath(mPathBelowMood, mPathPaint);
		p.setTextAlign(Paint.Align.RIGHT);
		p.setTextSize(35);
		mPathPaint.setColor(Color.GRAY);//p.setColor(0x80FFFFFF);
		canvas.drawTextOnPath("Mood", mPathBelowMood, 0, 0, p);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		// TODO Auto-generated method stub
		float x;
		float y;

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:

			// screen touch get x of the touch event
			x = event.getX();
			// screen touch get y of the touch event
			y = event.getY();

			RectF pBoundsAlbum = new RectF();
			mPathAlbum.computeBounds(pBoundsAlbum, true);
			if (pBoundsAlbum.contains(x, y)) {
				if (!MelodyDroidHelper.isSdPresent()) {
					Intent sdCardError = new Intent(context, SdCardError.class);
					context.startActivity(sdCardError);
				} else {
					SearchCriteria.setLastSelectedFilterName("Album");
					Intent displaySearchCriteriaValues = new Intent(context,
							DisplaySearchCriteriaValues.class);
					context.startActivity(displaySearchCriteriaValues);
				}
			}
			// ----------------------------------------------------------------------------------
			RectF pBoundsArtist = new RectF();
			mPathArtist.computeBounds(pBoundsArtist, true);
			if (pBoundsArtist.contains(x, y)) {
				if (!MelodyDroidHelper.isSdPresent()) {
					Intent sdCardError = new Intent(context, SdCardError.class);
					context.startActivity(sdCardError);
				} else {
					SearchCriteria.setLastSelectedFilterName("Artist");
					Intent displaySearchCriteriaValues = new Intent(context,
							DisplaySearchCriteriaValues.class);
					context.startActivity(displaySearchCriteriaValues);
				}
			}
			// ----------------------------------------------------------------------------------
			RectF pBoundsGenre = new RectF();
			mPathGenre.computeBounds(pBoundsGenre, true);
			if (pBoundsGenre.contains(x, y)) {
				if (!MelodyDroidHelper.isSdPresent()) {
					Intent sdCardError = new Intent(context, SdCardError.class);
					context.startActivity(sdCardError);
				} else {
					SearchCriteria.setLastSelectedFilterName("Genre");
					Intent displaySearchCriteriaValues = new Intent(context,
							DisplaySearchCriteriaValues.class);
					context.startActivity(displaySearchCriteriaValues);
				}
			}
			// -----------------------------------------------------------------------------------
			RectF pBoundsTitle = new RectF();
			mPathBelowTitle.computeBounds(pBoundsTitle, true);
			if (pBoundsTitle.contains(x, y)) {
				if (!MelodyDroidHelper.isSdPresent()) {
					Intent sdCardError = new Intent(context, SdCardError.class);
					context.startActivity(sdCardError);
				} else {
					SearchCriteria.setLastSelectedFilterName("Title");
					Intent displaySearchCriteriaValues = new Intent(context,
							DisplaySearchCriteriaValues.class);
					context.startActivity(displaySearchCriteriaValues);
				}
			}
			// -----------------------------------------------------------------------------------
			RectF pBoundsYear = new RectF();
			mPathBelowYear.computeBounds(pBoundsYear, true);
			if (pBoundsYear.contains(x, y)) {
				if (!MelodyDroidHelper.isSdPresent()) {
					Intent sdCardError = new Intent(context, SdCardError.class);
					context.startActivity(sdCardError);
				} else {
					SearchCriteria.setLastSelectedFilterName("Year");
					Intent displaySearchCriteriaValues = new Intent(context,
							DisplaySearchCriteriaValues.class);
					context.startActivity(displaySearchCriteriaValues);
				}
			}
			// -----------------------------------------------------------------------------------
			RectF pBoundsMood = new RectF();
			mPathBelowMood.computeBounds(pBoundsMood, true);
			if (pBoundsMood.contains(x, y)) {
				if (!MelodyDroidHelper.isSdPresent()) {
					Intent sdCardError = new Intent(context, SdCardError.class);
					context.startActivity(sdCardError);
				} else {
					// SearchCriteria.setLastSelectedFilterName("Mood");
					// Intent displaySearchCriteriaValues = new Intent(context,
					// DisplaySearchCriteriaValues.class);
					// context.startActivity(displaySearchCriteriaValues);
				}
			}

			break;

		case MotionEvent.ACTION_UP:
			// screen touch get x of the touch event
			x = event.getX();
			// screen touch get y of the touch event
			y = event.getY();
			performClick();
			break;

		case MotionEvent.ACTION_MOVE:
			// screen touch get x of the touch event
			x = event.getX();
			// screen touch get y of the touch event
			y = event.getY();
			break;
		}

		return true;
	}

	@Override
	public boolean performClick() {
		// TODO Auto-generated method stub
		return super.performClick();
	}

	@Override
	public void addOnLayoutChangeListener(OnLayoutChangeListener listener) {
		// TODO Auto-generated method stub
		super.addOnLayoutChangeListener(listener);

	}

	@Override
	protected void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);

	}

	public static int getScreenWidth() {
		return Resources.getSystem().getDisplayMetrics().widthPixels;
	}

	public static int getScreenHeight() {
		return Resources.getSystem().getDisplayMetrics().heightPixels;
	}
}
