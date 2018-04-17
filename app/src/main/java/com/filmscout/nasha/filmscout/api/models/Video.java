package com.filmscout.nasha.filmscout.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "iso_639_1",
        "iso_3166_1",
        "key",
        "name",
        "site",
        "size",
        "type"
})

public class Video {

    @JsonProperty("id")
    public String id;

    @JsonProperty("iso_639_1")
    public String iso6391;

    @JsonProperty("iso_3166_1")
    public String iso31661;

    @JsonProperty("key")
    public String key;

    @JsonProperty("name")
    public String name;

    @JsonProperty("site")
    public String site;

    @JsonProperty("size")
    public Integer size;

    @JsonProperty("type")
    private String type;


}
