package com.filmscout.nasha.filmscout.app.search;

import android.app.ListActivity;
import android.widget.ArrayAdapter;

import com.filmscout.nasha.filmscout.R;
import com.filmscout.nasha.filmscout.api.models.SelectData;

public class SearchComponent extends ListActivity {

    String[] genres = getResources().getStringArray(R.array.genres);
    String[] ratings = getResources().getStringArray(R.array.mpaa_ratings);



}
