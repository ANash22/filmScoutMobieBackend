package com.filmscout.nasha.filmscout.app.search;

import android.app.ListActivity;
import android.widget.ArrayAdapter;

import com.filmscout.nasha.filmscout.R;
import com.filmscout.nasha.filmscout.api.models.SelectData;
import com.filmscout.nasha.filmscout.app.AppScope;
import com.filmscout.nasha.filmscout.app.ApplicationComponent;

import dagger.Component;


@AppScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = SearchModule.class
)

interface SearchComponent {

    void inject (SearchActivity SearchActivity);




}
