package com.hadi.apptemplate.data;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ForecastService {

    @GET("data/2.5/forecast?")
    @Headers({
            "Accept: application/json",})
    Single<TrendingForecastResponse> getTrendingForecast(
                                                         @Query("lat") String lat,
                                                         @Query("lon") String lon,
                                                         @Query("appid") String app_id
                                                         );




}
