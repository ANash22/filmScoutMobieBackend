package com.filmscout.nasha.filmscout.api.models;

import android.widget.ImageView;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "images",
        "change_keys"
})

public class ImageConfiguration {

    @JsonProperty("images")
    public Images images;

    @JsonProperty("change_keys")
    public List<String> changeKeys = null;

}
