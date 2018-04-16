package com.filmscout.nasha.filmscout.app.results;

import com.filmscout.nasha.filmscout.app.AppScope;
import com.filmscout.nasha.filmscout.app.ApplicationComponent;


import dagger.Component;

@AppScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = ResultsModule.class
)

interface ResultsComponent {

    void inject (ResultsActivity resultsActivity);
}
