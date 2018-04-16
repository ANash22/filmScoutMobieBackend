package com.filmscout.nasha.filmscout.app;

import android.app.Application;

import com.filmscout.nasha.filmscout.api.ApiModule;

public class App extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate(){
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .apiModule(new ApiModule())
                .appModule(new AppModule(this ))
                .build();
    }

    public static   ApplicationComponent getApplicationComponent(Application application){
        return ((App) application).getApplicationComponent();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
