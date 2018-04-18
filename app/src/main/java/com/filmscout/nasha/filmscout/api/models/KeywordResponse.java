package com.filmscout.nasha.filmscout.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "page",
        "results",
        "total_pages",
        "total_results"
})

public class KeywordResponse {

    @JsonProperty("page")
    public Integer page;

    @JsonProperty("results")
    public List<Keyword> results;

    @JsonProperty("total_pages")
    public Integer totalPages;

    @JsonProperty("total_results")
    public  Integer totalResults;
}
