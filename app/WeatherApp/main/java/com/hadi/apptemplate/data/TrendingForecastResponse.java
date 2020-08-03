package com.hadi.apptemplate.data;

import com.google.auto.value.AutoValue;
import com.hadi.apptemplate.model.Forecast;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

@AutoValue
public abstract class TrendingForecastResponse {

    @Json(name = "cod")
    public abstract String cod();

    @Json(name = "message")
    public abstract String message();

    @Json(name = "cnt")
    public abstract String cnt();

    @Json(name = "list")
    public abstract List<Forecast> forecast_lists();

//    @Json(name = "city")
//    public abstract String city();

    public static JsonAdapter<TrendingForecastResponse> jsonAdapter(Moshi moshi){
        return new AutoValue_TrendingForecastResponse.MoshiJsonAdapter(moshi);
    }

}
