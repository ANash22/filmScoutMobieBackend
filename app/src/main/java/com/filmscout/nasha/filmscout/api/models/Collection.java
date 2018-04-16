package com.filmscout.nasha.filmscout.api.models;

import android.support.v4.widget.SwipeRefreshLayout;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.squareup.moshi.Json;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
       "id",
        "name",
        "overview",
        "poster_path",
        "backdrop_path"
})

public class Collection {

    @JsonProperty("id ")
    public Integer id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("overview")
    public String overview;

    @JsonProperty("poster_path")
    public String posterPath;

    @JsonProperty("backdrop_path")
    public String backdropPath;
}
