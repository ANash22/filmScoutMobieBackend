package com.filmscout.nasha.filmscout.app.details;

import com.filmscout.nasha.filmscout.app.AppScope;
import com.filmscout.nasha.filmscout.app.ApplicationComponent;

import dagger.Component;

@AppScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = DetailsModule.class
)
interface DetailsComponent {

    void inject(DetailsActivity DetailActivity);
}
