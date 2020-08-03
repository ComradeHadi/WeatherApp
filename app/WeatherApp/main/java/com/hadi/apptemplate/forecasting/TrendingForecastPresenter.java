package com.hadi.apptemplate.forecasting;


import android.app.Application;
import android.util.Log;

import com.hadi.apptemplate.base.MyApplication;
import com.hadi.apptemplate.data.ForecastRequester;
import com.hadi.apptemplate.data.PreferenceManager;
import com.hadi.apptemplate.data.TrendingForecastResponse;
import com.hadi.apptemplate.di.ScreenScope;
import com.hadi.apptemplate.home.MainActivity;
import com.hadi.apptemplate.model.Forecast;

import java.util.List;
import java.util.Observable;

import javax.inject.Inject;

import dagger.Provides;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@ScreenScope
class TrendingForecastPresenter implements ForecastAdapter.RepoClickedListener {

    private final TrendingForecastViewModel viewModel;
    private final ForecastRequester forecastRequester;

    @Inject
    public TrendingForecastPresenter(TrendingForecastViewModel viewModel, ForecastRequester forecastRequester) {
        this.viewModel = viewModel;
        this.forecastRequester = forecastRequester;

        loadForecast();
    }



    @Inject
    public void loadForecast() {
        PreferenceManager preferenceManager = new PreferenceManager(MyApplication.application);
        List<String> geolocations = preferenceManager.getGPSLocation();
        forecastRequester.getTrendingForecast(geolocations.get(0), geolocations.get(1) ,"8dde1f92437fc28e9e8507f6bef34259")
                .doOnSubscribe(__->viewModel.loadingUpdated().accept(true))
                .doOnEvent((d,t)->viewModel.loadingUpdated().accept(false))
                .subscribe(viewModel.reposUpdated(), viewModel.onError());
    }

    @Override
    public void onRepoClicked(Forecast forecast) {

    }
}
