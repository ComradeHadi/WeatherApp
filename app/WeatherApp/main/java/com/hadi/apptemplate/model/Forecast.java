package com.hadi.apptemplate.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

@AutoValue
public abstract class Forecast {

    @Json(name = "dt")
    public abstract long id();

    @Json(name = "dt_txt")
    public abstract String dateInText();

    @Json(name = "main")
    public abstract Main data();

    @Json(name = "weather")
    public abstract List<Weather> weather();



//    @Override
//    public String renderKey() {
//        return "Repo";
//    }
//
//    @Override
//    public long getId() {
//        return id();
//    }

    public static JsonAdapter<Forecast> jsonAdapter(Moshi moshi) {
        return new AutoValue_Forecast.MoshiJsonAdapter(moshi);
    }
}
