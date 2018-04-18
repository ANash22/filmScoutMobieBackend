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
        "total_results",
        "total_pages",
        "results"
})

public class PeopleResponse {

    @JsonProperty("page")
    public Integer page;

    @JsonProperty("total_results")
    public Integer totalResults;

    @JsonProperty("total_pages")
    public Integer totalPages;

    @JsonProperty("results")
    public List<People> results = null;
}
