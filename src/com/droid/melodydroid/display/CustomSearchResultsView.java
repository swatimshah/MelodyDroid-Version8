package com.droid.melodydroid.display;

import com.droid.melodydroid.R;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomSearchResultsView extends LinearLayout {

	public CustomSearchResultsView(Context context, CustomSearchResult searchResult) {
		super(context);

		this.setOrientation(VERTICAL);
		this.setTag(searchResult);

		View v = inflate(context, R.layout.custom_search_row, null);

		TextView album = (TextView) v.findViewById(R.id.album);
		album.setText("Album: " + searchResult.getAlbum());
		album.setTextColor(Color.DKGRAY);
		
		TextView artist = (TextView) v.findViewById(R.id.artist);
		artist.setText("Artist: " + searchResult.getSinger());
		artist.setTextColor(Color.DKGRAY);
		
		TextView genre = (TextView) v.findViewById(R.id.genre);
		genre.setText("Genre: " + searchResult.getGenre());
		genre.setTextColor(Color.DKGRAY);
		
		TextView title = (TextView) v.findViewById(R.id.title);
		title.setText("Title: " + searchResult.getTitle());
		title.setTextColor(Color.DKGRAY);

		TextView year = (TextView) v.findViewById(R.id.year);
		year.setText("Year: " + searchResult.getYear());
		year.setTextColor(Color.DKGRAY);

		TextView fileName = (TextView) v.findViewById(R.id.fileName);
		fileName.setText("File Name: " + searchResult.getFileName());
		fileName.setTextColor(Color.DKGRAY);
				
		v.setBackgroundResource(R.drawable.listview_selector);
		
		addView(v);
	}	
}
