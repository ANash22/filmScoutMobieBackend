package com.filmscout.nasha.filmscout.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "vote_count",
        "id",
        "video",
        "vote_average",
        "title",
        "popularity",
        "poster_path",
        "orginal_language",
        "original_title",
        "genre_ids",
        "backdrop_path",
        "adult",
        "overview",
        "release_date"
})

public class Movie {

    @JsonProperty("vote_count")
    public Integer voteCount;

    @JsonProperty("id")
    public Integer id;

    @JsonProperty("video")
    public Boolean video;

    @JsonProperty("vote_average")
    public Double voteAverage;

    @JsonProperty("title")
    public String title;

    @JsonProperty("popularity")
    public Double popularity;

    @JsonProperty("poster_path")
    public String posterPath;

    @JsonProperty("original_language")
    public String originalLanguage;

    @JsonProperty("original_title")
    public String originalTitle;

    @JsonProperty("genre_ids")
    public List<String> genreIds = null;

    @JsonProperty("backdrop_path")
    public String backdropPath;

    @JsonProperty("adult")
    public Boolean adult;

    @JsonProperty("overview")
    public String overview;

    @JsonProperty("release_date")
    public String releaseData;

}
