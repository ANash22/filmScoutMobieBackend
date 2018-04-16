package com.filmscout.nasha.filmscout.app.results;

import com.filmscout.nasha.filmscout.app.AppScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ResultsModule {
    private final ResultsContract.View resultsView;

    ResultsModule(ResultsContract.View resultsView){
        this.resultsView = resultsView;
    }

    @Provides
    @AppScope
    ResultsContract.View provideResultsView(){
        return resultsView;
    }
}
