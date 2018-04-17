package com.filmscout.nasha.filmscout.app.results;

import com.filmscout.nasha.filmscout.Config;
import com.filmscout.nasha.filmscout.api.MovieApiService;
import com.filmscout.nasha.filmscout.api.models.ImageConfiguration;
import com.filmscout.nasha.filmscout.api.models.MovieDetails;
import com.filmscout.nasha.filmscout.api.models.MovieResponse;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultsPresenter implements ResultsContract.Presenter {

    private ResultsContract.View view;
    private MovieApiService apiService;
    private String apiKey = Config.API_KEY_URL;

    private int page = 1;
    private String certification;
    private String primaryReleaseGTE;
    private String primaryReleaseLTE;
    private Double voteAverage;
    private String cast;
    private String crew;
    private String genre;
    private List<String> keywords;
    private ImageConfiguration configuration;
    private MovieResponse movieResponse;
    private MovieDetails movieDetails;

    @Inject
    ResultsPresenter(ResultsContract.View view, MovieApiService apiService){

        this.view = view;
        this.apiService = apiService;
    }

    @Override
    public void start(String apiKey, String certification,
                      String primaryReleaseGTE, String primaryReleaseLTE, Double voteAverage,
                      String cast, String crew, String genre, List<String> keywords, boolean isRefresh){
        view.showLoading(false);
        getSearchResults(apiKey, certification,
                primaryReleaseGTE, primaryReleaseLTE, voteAverage, cast, crew, genre, keywords, true);
        getConfiguration();
    }

    private void getConfiguration(){
        Call<ImageConfiguration> call = apiService.getConfiguration(apiKey);
        call.enqueue(new Callback<ImageConfiguration>() {
            @Override
            public void onResponse(Call<ImageConfiguration> call, Response<ImageConfiguration> response) {
                if(response.isSuccessful()){
                    view.onConfigurationSet(response.body().images);
                }
            }

            @Override
            public void onFailure(Call<ImageConfiguration> call, Throwable t) {

            }
        });
    }

    @Override
    public void onPullToRefresh(){
        page = 1;
        view.showLoading(true);
        getSearchResults(apiKey, certification,
                primaryReleaseGTE, primaryReleaseLTE, voteAverage, cast, crew, genre, keywords, true);
    }

    @Override
    public void onScrollToEnd(){
        page++;
        view.showLoading(true);
        getSearchResults(apiKey, certification,
                primaryReleaseGTE, primaryReleaseLTE, voteAverage, cast, crew, genre, keywords, false );
    }

    private void getSearchResults(String apiKey, String certification,
                                  String primaryReleaseGTE, String primaryReleaseLTE, Double voteAverage,
                                  String cast, String crew, String genre, List<String> keywords, boolean isRefresh){
        Call<MovieResponse> call = apiService.getSearchResults(apiKey,certification,
                primaryReleaseGTE, primaryReleaseLTE, voteAverage, cast, crew, genre, keywords);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.isSuccessful()){
                    view.showContent(response.body().results, isRefresh);

                }
                else{
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void finish(){

    }
}
