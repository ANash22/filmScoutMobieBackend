package com.filmscout.nasha.filmscout.api;

import com.filmscout.nasha.filmscout.api.models.Certification;
import com.filmscout.nasha.filmscout.api.models.CertificationResponse;
import com.filmscout.nasha.filmscout.api.models.Genre;
import com.filmscout.nasha.filmscout.api.models.GenreResponse;
import com.filmscout.nasha.filmscout.api.models.ImageConfiguration;
import com.filmscout.nasha.filmscout.api.models.Movie;
import com.filmscout.nasha.filmscout.api.models.MovieDetails;
import com.filmscout.nasha.filmscout.api.models.MovieResponse;
import com.filmscout.nasha.filmscout.api.models.Person;
import com.filmscout.nasha.filmscout.api.models.VideoResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApiService {
    enum SortBy {
        RELEASE_DATE_ASCENDING("release_date.asc"),
        RELEASE_DATE_DESCENDING("release_date.desc");

        String value;

        SortBy(String value) {
            this.value = value;
        }


        @Override
        public String toString() {
            return this.value;
        }
    }

@GET(ApiModule.DISCOVER)
    Call<MovieResponse> getSearchResults(
            @Query("api_key") String apiKey,
            @Query("certification") String certification,
            @Query("primary_release_date.gte") String primaryReleaseGTE,
            @Query("primary_release_date.lte") String primaryReleaseLTE,
            @Query("vote_average.gte") Double voteAverage,
            @Query("with_cast") String cast,
            @Query("with_crew") String crew,
            @Query("with_genres") String genre,
            @Query("with_keywords")List<String> keywords
    );

    @GET(ApiModule.MOVIE + "{id}/similar")
    Call<MovieResponse> getSimilarMovies(
            @Path("id") Integer movieId,
            @Query("api_key") String apiKey
    );

    @GET(ApiModule.MOVIE + "{id}/recommendations")
    Call<MovieResponse> getRecommendations(
            @Path("id") Integer movieId,
            @Query("api_key") String apiKey
    );

    @GET(ApiModule.GENRE)
    Call<GenreResponse> getGenres(
            @Query("api_key") String apiKey
    );

    @GET(ApiModule.MPAA_RATING)
    Call<CertificationResponse> getMPAARatings(
            @Query("api_key") String apiKey
    );


    @GET(ApiModule.MOVIE + "{id}/credits")
    Call<MovieDetails> getCredits(
            @Path("id") Integer movieId,
            @Query("api_key") String apiKey
    );

    @GET(ApiModule.MOVIE + "{id}")
    Call<MovieDetails> getMovieDetails(
            @Path("id") Integer movieId,
            @Query("api_key") String apiKey
    );

    @GET(ApiModule.MOVIE + "{id}/videos")
    Call<VideoResponse> getMovieVideos(
            @Path("id") Integer movieId,
            @Query("api_key") String apiKey
    );

    @GET(ApiModule.PERSON + "{id}")
    Call<Person> getPerson(
            @Path("id") int personId,
            @Query("api_key") String apiKey
    );

    @GET(ApiModule.SEARCH_MOVIE)
    Call<MovieResponse> searchMovies(
            @Query("query") String query,
            @Query("api_key") String apiKey

    );

    @GET(ApiModule.CONFIGURATION)
    Call<ImageConfiguration> getConfiguration(
            @Query("api_key") String apiKey
    );



}
