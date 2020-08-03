package com.hadi.apptemplate.home;

import com.bluelinelabs.conductor.Controller;
import com.hadi.apptemplate.di.ControllerKey;
import com.hadi.apptemplate.forecasting.TrendingForecastComponent;
import com.hadi.apptemplate.forecasting.TrendingForecastController;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {TrendingForecastComponent.class})
public abstract class MainScreenBindingModule {

    @Binds
    @IntoMap
    @ControllerKey(TrendingForecastController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindTrendingReposInjector(TrendingForecastComponent.Builder builder);
}
