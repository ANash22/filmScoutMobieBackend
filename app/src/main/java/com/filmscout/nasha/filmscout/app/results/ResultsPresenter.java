package com.filmscout.nasha.filmscout.app.results;

import com.filmscout.nasha.filmscout.Config;
import com.filmscout.nasha.filmscout.api.MovieApiService;
import com.filmscout.nasha.filmscout.api.models.Certification;
import com.filmscout.nasha.filmscout.api.models.CertificationResponse;
import com.filmscout.nasha.filmscout.api.models.Genre;
import com.filmscout.nasha.filmscout.api.models.GenreResponse;
import com.filmscout.nasha.filmscout.api.models.ImageConfiguration;
import com.filmscout.nasha.filmscout.api.models.Keyword;
import com.filmscout.nasha.filmscout.api.models.KeywordResponse;
import com.filmscout.nasha.filmscout.api.models.MovieDetails;
import com.filmscout.nasha.filmscout.api.models.MovieResponse;
import com.filmscout.nasha.filmscout.api.models.People;
import com.filmscout.nasha.filmscout.api.models.PeopleResponse;

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
    private String title;
    private List<Integer> genreIds;

    @Inject
    ResultsPresenter(ResultsContract.View view, MovieApiService apiService){

        this.view = view;
        this.apiService = apiService;
    }

    @Override
    public void start(String apiKey, String title, String certification,
                      String primaryReleaseGTE, String primaryReleaseLTE, Double voteAverage,
                      String cast, String crew, String genre, String keywords){
        view.showLoading(false);

        movies(apiKey, title, certification, primaryReleaseGTE, primaryReleaseLTE, voteAverage, cast, crew, genre, keywords, true);
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
    public void onPullToRefresh(String apiKey, String title, String certification,
                                String primaryReleaseGTE, String primaryReleaseLTE, Double voteAverage,
                                String cast, String crew, String genre, String keywords){
        page = 1;
        view.showLoading(true);
       movies(apiKey, title, certification, primaryReleaseGTE, primaryReleaseLTE,
               voteAverage, cast, crew, genre, keywords, true);
    }

    @Override
    public void onScrollToEnd(String apiKey, String title, String certification,
                              String primaryReleaseGTE, String primaryReleaseLTE, Double voteAverage,
                              String cast, String crew, String genre, String keywords){
        page++;
        view.showLoading(true);
        movies(apiKey, title, certification, primaryReleaseGTE, primaryReleaseLTE,
                voteAverage, cast, crew, genre, keywords, true);
    }

    private void getMovie(String apiKey, String title, final boolean isRefresh){
        Call<MovieResponse> call = apiService.searchMovies(apiKey, title);
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

    private void searchYearBefore(String apiKey, String primaryReleaseLTE, boolean isRefresh){
        Call<MovieResponse> call = apiService.searchYearBefore(apiKey, primaryReleaseLTE);
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

    private void searchYearAfter(String apiKey, String primaryReleaseGTE, boolean isRefresh){
        Call<MovieResponse> call = apiService.searchYearAfter(apiKey,primaryReleaseGTE);
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

    private void searchGenre(String apiKey, String genre, boolean isRefresh){
        String g = getGenres(genre, isRefresh);
        Call<MovieResponse> call = apiService.searchGenre(apiKey, g);
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

    private void searchRating(String apiKey, Double voteAverage, boolean isRefresh){
        Call<MovieResponse> call = apiService.searchRating(apiKey, voteAverage);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.isSuccessful()){
                    view.showContent(response.body().results, isRefresh);
                }
                else {
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }

    private void searchCastCrewKey(String apiKey, String cast,
                                   String crew, String keywords, boolean isRefresh){

        String actor = getPeople(apiKey, cast, isRefresh);
        String director = getPeople(apiKey, cast, isRefresh);
        String key = getKeyword(apiKey, keywords, isRefresh);
        Call<MovieResponse> call = apiService.searchCastCrewKey(apiKey, actor, director, key);
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

    private void searchCastCrew(String apiKey, String cast, String crew, boolean isRefresh){
        String actor = getPeople(apiKey, cast, isRefresh);
        String director = getPeople(apiKey, crew, isRefresh);
        Call<MovieResponse> call = apiService.searchCastCrew(apiKey, actor, director);
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

    private void searchCrewKey(String apiKey, String crew, String keywords, boolean isRefresh){
        String director = getPeople(apiKey, crew, isRefresh);
        String key = getKeyword(apiKey, keywords, isRefresh);
        Call<MovieResponse> call = apiService.searchCrewKey(apiKey, director, key);
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

    private  void searchCastKey(String apiKey, String cast, String keywords, boolean isRefresh){
        String actor = getPeople(apiKey, cast, isRefresh);
        String key = getKeyword(apiKey, keywords, isRefresh);
        Call<MovieResponse> call = apiService.searchCastKey(apiKey, actor, key);
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
    private void searchKeywords(String apiKey, String keywords, boolean isRefresh){
        String k = getKeyword(apiKey, keywords, isRefresh);
        Call<MovieResponse> call = apiService.searchKeywords(apiKey, k);
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

    private void searchCast(String apiKey, String cast, boolean isRefresh){
        String c = getPeople(apiKey, cast, isRefresh);
        Call<MovieResponse> call = apiService.searchCast(apiKey, c);
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

    private void searchMppas(String apiKey, String cert, boolean isRefresh){
        cert = getMppas(apiKey, isRefresh);
        Call<MovieResponse> call = apiService.searchMppa(apiKey, cert);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.isSuccessful()){
                    view.showContent(response.body().results, isRefresh);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }

    private void searchCrew(String apiKey, String crew, boolean isRefresh){
        String c = getPeople(apiKey, crew, isRefresh);
        Call<MovieResponse> call = apiService.searchCrew(apiKey, c);
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

   /** private void discoverMovies(String apiKey,boolean isRefresh){
        Call<MovieResponse> call = apiService.discoverMovies(apiKey, MovieApiService.LANGUAGE, MovieApiService.SortBy.POPULARITY_DESCENDING,
                MovieApiService.COUNTRY, MovieApiService.ADULT, page);
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
    }**/

    private String getMppas(String apiKey, boolean isRefresh){
        final String[] cert = {""};
        Call<CertificationResponse> call = apiService.getMPAARatings(apiKey);
        call.enqueue(new Callback<CertificationResponse>() {
            @Override
            public void onResponse(Call<CertificationResponse> call, Response<CertificationResponse> response) {
                List<Certification> certifications = response.body().certifications;
                int i = 0;
                cert[0] = certifications.get(i).certification;
            }

            @Override
            public void onFailure(Call<CertificationResponse> call, Throwable t) {

            }
        });
        return cert[0];
    }

    private String getGenres(String apiKey, boolean isRefresh){
        final String[] g = {""};
        Call<GenreResponse> call = apiService.getGenres(apiKey);
        call.enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                List<Genre> genres = response.body().genres;
                int i = 0;

                Integer id = genres.get(i).id;
                String s = id.toString();
                g[0] = s;
                //searchGenre(apiKey, s, isRefresh);
            }

            @Override
            public void onFailure(Call<GenreResponse> call, Throwable t) {

            }
        });
        return g[0];
    }

    private String getPeople(String apiKey, String person, boolean isRefresh){
        final String[] p = {""};
        Call<PeopleResponse> call = apiService.searchPeople(apiKey, person);
        call.enqueue(new Callback<PeopleResponse>() {
            @Override
            public void onResponse(Call<PeopleResponse> call, Response<PeopleResponse> response) {
                List<People> person = response.body().results;
                int i = 0;
                Integer id = person.get(i).id;
                String s = id.toString();
                p[0] = s;
                //searchCast(apiKey,s, isRefresh);


            }

            @Override
            public void onFailure(Call<PeopleResponse> call, Throwable t) {

            }
        });

        return p[0];
    }

    private String getKeyword(String apiKey, String keyword, boolean isRefresh){
        final String[] k = {""};
        Call<KeywordResponse> call = apiService.searchKeyword(apiKey, keyword);
        call.enqueue(new Callback<KeywordResponse>() {
            @Override
            public void onResponse(Call<KeywordResponse> call, Response<KeywordResponse> response) {
                List<Keyword> keywords = response.body().results;
                int i = 0;
                Integer id = keywords.get(i).id;
                String s = id.toString();
                k[0] = s;
                //searchKeywords(apiKey, k, isRefresh);

            }

            @Override
            public void onFailure(Call<KeywordResponse> call, Throwable t) {

            }
        });
        return k[0];
    }


    private void getSearchResults(String apiKey, String certification,
                                  String primaryReleaseGTE, String primaryReleaseLTE, Double voteAverage,
                                  String cast, String crew, String genre, String keywords, boolean isRefresh){
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

    private void movies(String apiKey, String title, String certification,
                        String primaryReleaseGTE, String primaryReleaseLTE, Double voteAverage,
                        String cast, String crew, String genre, String keywords, boolean isRefresh){

        if (title != "") {

            getMovie(apiKey,title, isRefresh);
        }
        else{
            movieResults(apiKey,certification,primaryReleaseGTE, primaryReleaseLTE,
                    voteAverage, cast, crew, genre, keywords, isRefresh);
        }


    }

    private void movieResults(String apiKey, String certification,
                              String primaryReleaseGTE, String primaryReleaseLTE, Double voteAverage,
                              String cast, String crew, String genre, String keywords, boolean isRefresh){

        if(primaryReleaseGTE != "" || primaryReleaseLTE != ""){
            if(primaryReleaseLTE != ""){
                searchYearBefore(apiKey, primaryReleaseLTE, isRefresh);
            }
            else{
                searchYearAfter(apiKey, primaryReleaseGTE, isRefresh);
            }
        }
        if(genre != ""){

            searchGenre(apiKey, genre,isRefresh);
        }
        if(certification != ""){

            searchMppas(apiKey, certification,isRefresh);

        }

        if(voteAverage != 0){

            searchRating(apiKey, voteAverage,isRefresh);

        }

        if(keywords != "" && cast != "" && crew != ""){
            searchCastCrewKey(apiKey,cast, crew, keywords, isRefresh);
        }

        if(keywords == "" && cast!= "" && crew != ""){
            searchCastCrew(apiKey, cast, crew, isRefresh);
        }

        if(keywords != "" && cast == "" && crew!= ""){
            searchCrewKey(apiKey, crew, keywords, isRefresh);
        }
        if(keywords != "" && cast != "" && crew == ""){
            searchCastKey(apiKey, cast, keywords, isRefresh);
        }

        if(keywords != "" && cast == "" && crew == ""){
            searchKeywords(apiKey, keywords ,isRefresh);
        }
        if(keywords == "" && cast != "" && crew == ""){
            searchCast(apiKey, cast, isRefresh);
        }
        if(keywords == "" && cast == "" && crew != ""){
            searchCrew(apiKey, crew, isRefresh);
        }


    }

    @Override
    public void finish(){

    }
}
