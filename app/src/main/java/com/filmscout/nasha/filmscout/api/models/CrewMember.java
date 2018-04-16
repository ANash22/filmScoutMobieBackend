package com.filmscout.nasha.filmscout.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "credit_id",
        "department",
        "gender",
        "id",
        "job",
        "name",
        "profile_path"
})

public class CrewMember {

    @JsonProperty("credit_id")
    public String creditId;

    @JsonProperty("department")
    public String department;

    @JsonProperty("gender")
    public Integer gender;

    @JsonProperty("id")
    public Integer id;

    @JsonProperty("job")
    public String job;

    @JsonProperty("name")
    public String name;

    @JsonProperty("profile_path")
    public String profilePath;

}
