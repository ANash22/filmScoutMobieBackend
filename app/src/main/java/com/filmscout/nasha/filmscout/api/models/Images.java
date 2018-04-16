package com.filmscout.nasha.filmscout.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "base_url",
        "secure_base_url",
        "backdrop_sizes",
        "logo_sizes",
        "poster_sizes",
        "profile_sizes",
        "still_sizes"
})

public class Images {

    @JsonProperty("base_url")
    public String baseUrl;

    @JsonProperty("secure_base_url")
    public String secureBaseUrl;

    @JsonProperty("backdrop_sizes")
    public List<String> backDropSizes = null;

    @JsonProperty("logo_sizes")
    public List<String> logoSizes = null;

    @JsonProperty("poster_sizes")
    public List<String> posterSizes = null;

    @JsonProperty("still_sizes")
    public List<String> stillSizes = null;
}
