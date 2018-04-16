package com.filmscout.nasha.filmscout.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "birthday",
        "deathday",
        "id",
        "name",
        "also_known_as",
        "gender",
        "biography",
        "popularity",
        "place_of_birth",
        "profile_path",
        "adult",
        "imdb_id",
        "homepage"
})

public class Person {

    @JsonProperty("birthday")
    public String birthday;

    @JsonProperty("deathday")
    public String deathday;

    @JsonProperty("id")
    public Integer id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("also_known_as")
    public List<String> alsoKnownAs = null;

    @JsonProperty("gender")
    public Integer gender;

    @JsonProperty("biography")
    public String biography;

    @JsonProperty("popularity")
    public Double popularity;

    @JsonProperty("place_of_birth")
    public String placeOfBirth;

    @JsonProperty("profile_path")
    public String profilePath;

    @JsonProperty("adult")
    public Boolean adult;

    @JsonProperty("imdb_id")
    public String imdbId;

    @JsonProperty("homepage")
    public String homepage;


}
