package com.hadi.apptemplate.forecasting;

import android.util.Log;

import com.hadi.apptemplate.R;
import com.hadi.apptemplate.di.ScreenScope;
import com.hadi.apptemplate.model.Forecast;
import com.jakewharton.rxrelay2.BehaviorRelay;

import java.util.List;
import io.reactivex.functions.Consumer;

import javax.inject.Inject;

import io.reactivex.Observable;
import timber.log.Timber;

@ScreenScope
class TrendingForecastViewModel {
    private final BehaviorRelay<List<Forecast>> forecastRelay = BehaviorRelay.create();
    private final BehaviorRelay<Integer> errorRelay = BehaviorRelay.create();
    private final BehaviorRelay<Boolean> loadingRelay = BehaviorRelay.create();

    @Inject
    TrendingForecastViewModel(){


    }

    //Exposing the BehaviourRelays to the views as observables

    Observable<List<Forecast>> repos(){
        return forecastRelay;
    }

    Observable<Integer> error(){
        return errorRelay;
    }

    Observable<Boolean> loading(){
        return loadingRelay;
    }



    //Passing on data to our presenters

    Consumer<List<Forecast>> reposUpdated(){
        errorRelay.accept(-1);
        return forecastRelay;

    }

    Consumer<Boolean> loadingUpdated(){

        return loadingRelay;

    }

    Consumer<Throwable> onError(){
        return throwable -> {

            Timber.e(throwable, "Error loading repos");
            Log.e("CHECK URL", throwable.getMessage());
            errorRelay.accept(R.string.relay_error_int);

        };
    }
}
