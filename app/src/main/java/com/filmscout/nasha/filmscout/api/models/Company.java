package com.filmscout.nasha.filmscout.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
       "description",
        "headquarters",
        "homepage",
        "id",
        "logoPath",
        "name",
        "orgin_country"
})

public class Company {

    @JsonProperty("description")
    public String description;

    @JsonProperty("headquarters")
    public String headquarters;

    @JsonProperty("homepage")
    public String homepage;

    @JsonProperty("id")
    public Integer id;

    @JsonProperty("logo_path")
    public String logoPath;

    @JsonProperty("name")
    public String name;

    @JsonProperty("origin_country")
    public String orginCountry;

}
