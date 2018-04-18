package com.filmscout.nasha.filmscout.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "popularity",
        "id",
        "profile_path",
        "name",
        "known_for",
        "adult"
})

public class People {

    @JsonProperty("popularity")
    public Double popularity;

    @JsonProperty("id")
    public Integer id;

    @JsonProperty("profile_path")
    public String profilePath;

    @JsonProperty("name")
    public String name;

    @JsonProperty("known_for")
    public List<Movie> knownFor;

    @JsonProperty("adult")
    public Boolean adult;

}
