package com.hadi.apptemplate.forecasting;

import com.hadi.apptemplate.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent
public interface TrendingForecastComponent extends AndroidInjector<TrendingForecastController> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<TrendingForecastController>{

    }
}
