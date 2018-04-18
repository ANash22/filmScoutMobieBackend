package com.filmscout.nasha.filmscout.api;

import com.filmscout.nasha.filmscout.api.models.Certification;
import com.filmscout.nasha.filmscout.api.models.CertificationResponse;
import com.filmscout.nasha.filmscout.api.models.Genre;
import com.filmscout.nasha.filmscout.api.models.GenreResponse;
import com.filmscout.nasha.filmscout.api.models.ImageConfiguration;
import com.filmscout.nasha.filmscout.api.models.KeywordResponse;
import com.filmscout.nasha.filmscout.api.models.Movie;
import com.filmscout.nasha.filmscout.api.models.MovieDetails;
import com.filmscout.nasha.filmscout.api.models.MovieResponse;
import com.filmscout.nasha.filmscout.api.models.PeopleResponse;
import com.filmscout.nasha.filmscout.api.models.Person;
import com.filmscout.nasha.filmscout.api.models.VideoResponse;

import java.util.List;

import javax.annotation.Generated;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;



public interface MovieApiService {




    enum SortBy {
        POPULARITY_ASCENDING("popularity.asc"),
        POPULARITY_DESCENDING("popularity.desc");



        String value;

        SortBy(String value) {
            this.value = value;
        }


        @Override
        public String toString() {
            return this.value;
        }
    }




@GET(ApiModule.DISCOVER_URL)
    Call<MovieResponse> getSearchResults(
            @Query("api_key") String apiKey,
            @Query("certification") String certification,
            @Query("primary_release_date.gte") String primaryReleaseGTE,
            @Query("primary_release_date.lte") String primaryReleaseLTE,
            @Query("vote_average.gte") Double voteAverage,
            @Query("with_cast") String cast,
            @Query("with_crew") String crew,
            @Query("with_genres") String genre,
            @Query("with_keywords") String keywords
    );
    @GET(ApiModule.DISCOVER_URL)
    Call<MovieResponse> searchYearBefore(
            @Query("api_key") String apiKey,
            @Query("primary_release_date.lte") String primaryReleaseLTE
    );

    @GET(ApiModule.DISCOVER_URL)
    Call<MovieResponse> searchYearAfter(
            @Query("api_key") String apiKey,
            @Query("primary_release_date.gte") String primaryReleaseGTE
    );

    @GET(ApiModule.DISCOVER_URL)
    Call<MovieResponse> searchGenre(
            @Query("api_key") String apiKey,
            @Query("with_genres") String genre
    );

    @GET(ApiModule.DISCOVER_URL)
    Call<MovieResponse> searchMppa(
            @Query("api_key") String apiKey,
            @Query("certification") String cert
    );

    @GET(ApiModule.DISCOVER_URL)
    Call<MovieResponse> searchRating(
            @Query("api_key") String apiKey,
            @Query("vote_average.gte") Double voteAverage

    );

    @GET(ApiModule.DISCOVER_URL)
    Call<MovieResponse> searchCastCrewKey(
            @Query("api_key") String apiKey,
            @Query("with_cast") String cast,
            @Query("with_crew") String crew,
            @Query("with_keywords") String keywords
    );

    @GET(ApiModule.DISCOVER_URL)
    Call<MovieResponse> searchCastCrew(
            @Query("api_key") String apiKey,
            @Query("with_cast") String cast,
            @Query("with_crew") String crew
    );

    @GET(ApiModule.DISCOVER_URL)
    Call<MovieResponse> searchCrewKey(
            @Query("api_key") String apiKey,
            @Query("with_crew") String crew,
            @Query("with_keywords") String keywords

    );

    @GET(ApiModule.DISCOVER_URL)
    Call<MovieResponse> searchCastKey(
            @Query("api_key") String apiKey,
            @Query("with_cast") String cast,
            @Query("with_keywords") String keywords

    );

    @GET(ApiModule.DISCOVER_URL)
    Call<MovieResponse> searchKeywords(
            @Query("api_key") String apiKey,
            @Query("with_keywords") String keywords
    );

    @GET(ApiModule.DISCOVER_URL)
    Call<MovieResponse> searchCast(
            @Query("api_key") String apiKey,
            @Query("with_cast") String cast
    );

    @GET(ApiModule.DISCOVER_URL)
    Call<MovieResponse> searchCrew(
            @Query("api_key") String apiKey,
            @Query("with_crew") String crew
    );

    @GET(ApiModule.DISCOVER)
    Call<MovieResponse> discoverMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("sort_by") SortBy sortBy,
            @Query("certification_country") String country,
            @Query("include_adult") Boolean adult,
            @Query("page") int page


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
            @Query("api_key") String apiKey,
            @Query("query") String query


    );

    @GET(ApiModule.PEOPLE)
    Call<PeopleResponse> searchPeople(
            @Query("api_key") String apiKey,
            @Query("query") String query
    );

    @GET(ApiModule.KEYWORD)
    Call<KeywordResponse> searchKeyword(
            @Query("api_key") String apiKey,
            @Query("query") String query
    );



    @GET(ApiModule.CONFIGURATION)
    Call<ImageConfiguration> getConfiguration(
            @Query("api_key") String apiKey
    );



}
