package com.hadi.apptemplate.data;

import android.util.Log;

import com.hadi.apptemplate.model.Forecast;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;

public class ForecastRequester {

    private final ForecastService service;

    @Inject
    ForecastRequester(ForecastService service){
        this.service = service;
    }

    public Single<List<Forecast>> getTrendingForecast( String lat, String lon, String app_id ){
        return service.getTrendingForecast(lat, lon, app_id )
                .map(TrendingForecastResponse::forecast_lists)
                .subscribeOn(Schedulers.io());
    };

}
