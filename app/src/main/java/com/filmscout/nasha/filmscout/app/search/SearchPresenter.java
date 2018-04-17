package com.filmscout.nasha.filmscout.app.search;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.filmscout.nasha.filmscout.Config;
import com.filmscout.nasha.filmscout.R;
import com.filmscout.nasha.filmscout.api.MovieApiService;
import com.filmscout.nasha.filmscout.api.models.Certification;
import com.filmscout.nasha.filmscout.api.models.CertificationResponse;
import com.filmscout.nasha.filmscout.api.models.Genre;
import com.filmscout.nasha.filmscout.api.models.GenreResponse;
import com.filmscout.nasha.filmscout.api.models.Movie;
import com.filmscout.nasha.filmscout.app.details.DetailsContract;

import org.w3c.dom.Text;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter implements  SearchContract.Presenter{

    private SearchContract.View view;
    private MovieApiService apiService;
    private List<Certification> ratings;
    private List<Genre> genres;


    private String apiKey = Config.API_KEY_URL;

    @Inject
    SearchPresenter(SearchContract.View view, MovieApiService apiService){
        this.view = view;
        this.apiService = apiService;
    }

    @Override
    public void start(){
        view.showContent();
        //getGenres(view.getGenres(genres));
        //getRatings(view.getRatings(ratings));
        //getInfo();

    }

    private void getRatings(List<Certification> r){
        Call<CertificationResponse> call = apiService.getMPAARatings(apiKey);
        call.enqueue(new Callback<CertificationResponse>() {
            @Override
            public void onResponse(Call<CertificationResponse> call, Response<CertificationResponse> response) {
                if(response.isSuccessful()){
                    ratings = response.body().certifications;


                }else{
                    view.showError();
                }
            }


            @Override
            public void onFailure(Call<CertificationResponse> call, Throwable t) {

            }
        });
    }

    private void getGenres(List<Genre> g){

        Call<GenreResponse> genreCall = apiService.getGenres(apiKey);
        genreCall.enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                if(response.isSuccessful())
                {
                    genres = response.body().genres;

                }else {
                    view.showError();
                }

            }

            @Override
            public void onFailure(Call<GenreResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void finish(){

    }
}
