package com.filmscout.nasha.filmscout.app.details;

import com.filmscout.nasha.filmscout.api.models.Images;
import com.filmscout.nasha.filmscout.api.models.MovieDetails;

public interface DetailsContract {

    interface View {

        void showLoading();

        void showContent(MovieDetails movie);

        void showError();

        void onConfigurationSet(Images images);

    }

    interface Presenter {

        void start(int movieId);

        void finish();
    }
}
