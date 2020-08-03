package com.hadi.apptemplate.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class Weather {

    @Json(name = "id")
    public abstract long weather_id();


    @Json(name = "main")
    public abstract String description();





//    @Override
//    public String renderKey() {
//        return "Repo";
//    }
//
//    @Override
//    public long getId() {
//        return id();
//    }

    public static JsonAdapter<Weather> jsonAdapter(Moshi moshi) {
        return new AutoValue_Weather.MoshiJsonAdapter(moshi);
    }
}
