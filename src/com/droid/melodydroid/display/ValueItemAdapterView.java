package com.droid.melodydroid.display;


import com.droid.melodydroid.R;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ValueItemAdapterView extends LinearLayout {

	public ValueItemAdapterView(Context context, ValueItem valueItem) {
		super(context);

		this.setOrientation(VERTICAL);
		this.setTag(valueItem);

		View v = inflate(context, R.layout.value_row, null);

		TextView value = (TextView) v.findViewById(R.id.value);
		value.setText(valueItem.getValue());
		value.setTextColor(Color.DKGRAY);
		
		v.setBackgroundResource(R.drawable.listview_selector);
		addView(v);
	}
}
