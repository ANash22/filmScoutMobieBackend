package com.filmscout.nasha.filmscout.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "certifcation",
        "meaning",
        "order"
})

public class Certification {

    @JsonProperty("certification")
    public String certification;

    @JsonProperty("meaning")
    public String meaning;

    @JsonProperty("order")
    public Integer order;


}
