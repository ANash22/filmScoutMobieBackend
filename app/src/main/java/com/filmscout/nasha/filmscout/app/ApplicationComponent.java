package com.filmscout.nasha.filmscout.app;

import android.app.Application;

import com.filmscout.nasha.filmscout.api.ApiModule;
import com.filmscout.nasha.filmscout.api.MovieApiService;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(
        modules = {
                AppModule.class,
                ApiModule.class
        }
)
public interface ApplicationComponent {

    Application application();

    MovieApiService movieApiService();

    void inject(App app);
}
