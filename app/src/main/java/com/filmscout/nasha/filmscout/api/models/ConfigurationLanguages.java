package com.filmscout.nasha.filmscout.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "iso_639_1",
        "english_name",
        "name"
})

public class ConfigurationLanguages {

    @JsonProperty("iso_639_1")
    public String iso6391;

    @JsonProperty("english_name")
    public String englishName;

    @JsonProperty("name")
    public String name;
}
