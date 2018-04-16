package com.filmscout.nasha.filmscout.app.details;

import com.filmscout.nasha.filmscout.app.AppScope;

import dagger.Module;
import dagger.Provides;

@Module
public class DetailsModule {
    private final DetailsContract.View DetailView;

    DetailsModule(DetailsContract.View DetailView){
        this.DetailView = DetailView;
    }

    @Provides
    @AppScope
    DetailsContract.View provideDetailView(){
        return DetailView;
    }
}
