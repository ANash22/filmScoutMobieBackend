package com.filmscout.nasha.filmscout.app.details;

import com.filmscout.nasha.filmscout.Config;
import com.filmscout.nasha.filmscout.api.ApiModule_ProvideMovieApiServiceFactory;
import com.filmscout.nasha.filmscout.api.MovieApiService;
import com.filmscout.nasha.filmscout.api.models.ImageConfiguration;
import com.filmscout.nasha.filmscout.api.models.Images;
import com.filmscout.nasha.filmscout.api.models.Movie;
import com.filmscout.nasha.filmscout.api.models.MovieDetails;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsPresenter implements DetailsContract.Presenter {

    private DetailsContract.View view;
    private MovieApiService apiService;
    private String apiKey = Config.API_KEY_URL;

    private Images images;

    @Inject
    DetailsPresenter(DetailsContract.View view, MovieApiService apiService){
        this.view = view;
        this.apiService = apiService;
    }

    @Override
    public void start(int movieId){
        if(images == null){
            getConfiguration(movieId);
        }
        else {
            view.onConfigurationSet(images);
            getMovieDetails(movieId);
        }
    }

    private void getConfiguration(final int movieId){
        Call<ImageConfiguration> call = apiService.getConfiguration(apiKey);
        call.enqueue(new Callback<ImageConfiguration>() {
            @Override
            public void onResponse(Call<ImageConfiguration> call, Response<ImageConfiguration> response) {
                if(response.isSuccessful()){
                    images = response.body().images;
                    view.onConfigurationSet(images);
                    getMovieDetails(movieId);
                }
            }

            @Override
            public void onFailure(Call<ImageConfiguration> call, Throwable t) {

            }
        });
    }

    private void getMovieDetails(int movieId){
        Call<MovieDetails> call = apiService.getMovieDetails(movieId,apiKey);
        call.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                if(response.isSuccessful()) {
                    view.showContent(response.body());
                }
                else{
                    view.showError();
                }

            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                view.showError();

            }
        });
    }

    @Override
    public void finish(){

    }
}
