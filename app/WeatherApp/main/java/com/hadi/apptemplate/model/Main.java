package com.hadi.apptemplate.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class Main {

    @Json(name = "temp")
    public abstract double temperature();


    @Json(name = "feels_like")
    public abstract double averageTemperature();

    @Json(name = "temp_min")
    public abstract double minimum();

    @Json(name = "temp_max")
    public abstract double maximum();





//    @Override
//    public String renderKey() {
//        return "Repo";
//    }
//
//    @Override
//    public long getId() {
//        return id();
//    }
//
    public static JsonAdapter<Main> jsonAdapter(Moshi moshi) {
        return new AutoValue_Main.MoshiJsonAdapter(moshi);
    }
}
