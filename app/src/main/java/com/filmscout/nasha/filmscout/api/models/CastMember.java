package com.filmscout.nasha.filmscout.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.squareup.moshi.Json;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "cast_id",
        "character",
        "credit_id",
        "gender",
        "id",
        "name",
        "order",
        "profile_path"
})

public class CastMember {
    @JsonProperty("cast_id")
    public Integer castId;

    @JsonProperty("character")
    public String character;

    @JsonProperty("credit_id")
    public String creditId;

    @JsonProperty("gender")
    public Integer gender;

    @JsonProperty("id")
    public Integer id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("order")
    public Integer order;

    @JsonProperty("profile_path")
    public String profliePath;


}
