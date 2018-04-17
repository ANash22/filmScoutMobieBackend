package com.filmscout.nasha.filmscout.app.details;

import com.filmscout.nasha.filmscout.api.models.Images;
import com.filmscout.nasha.filmscout.api.models.MovieDetails;
import com.filmscout.nasha.filmscout.api.models.Video;

import java.util.List;

public interface DetailsContract {

    interface View {

        void showLoading();

        void showContent(MovieDetails movie);

        void showError();

        void onConfigurationSet(Images images);

        void setTrailer(List<Video> videos);

    }

    interface Presenter {

        void start(int movieId);

        void finish();
    }
}
