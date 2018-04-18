package com.filmscout.nasha.filmscout.app.results;

import com.filmscout.nasha.filmscout.api.models.Images;
import com.filmscout.nasha.filmscout.api.models.Movie;

import java.util.List;

public interface ResultsContract {

    interface View{

        void showLoading(boolean isRefresh);

        void showContent(List<Movie> movies, boolean isRefresh);

        void showError();

        void onConfigurationSet(Images images);


    }

    interface Presenter {

        void start(String apiKey, String title, String certification,
                   String primaryReleaseGTE, String primaryReleaseLTE, Double voteAverage,
                   String cast, String crew, String genre, String keywords);

        void onPullToRefresh(String apiKey, String title, String certification,
                             String primaryReleaseGTE, String primaryReleaseLTE, Double voteAverage,
                             String cast, String crew, String genre, String keywords);

        void onScrollToEnd(String apiKey, String title, String certification,
                           String primaryReleaseGTE, String primaryReleaseLTE, Double voteAverage,
                           String cast, String crew, String genre, String keywords);

        void finish();
    }
}
