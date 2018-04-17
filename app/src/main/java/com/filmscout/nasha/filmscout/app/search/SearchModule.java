package com.filmscout.nasha.filmscout.app.search;

import android.view.Menu;

import com.filmscout.nasha.filmscout.app.AppScope;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchModule {

    private final SearchContract.View searchView;

    SearchModule(SearchContract. View searchView){
        this.searchView = searchView;
    }

    @Provides
    @AppScope
    SearchContract.View provideMainView(){
        return searchView;
    }


}
