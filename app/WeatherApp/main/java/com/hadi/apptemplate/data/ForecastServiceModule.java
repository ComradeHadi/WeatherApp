package com.hadi.apptemplate.data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public abstract class ForecastServiceModule {


    @Provides
    @Singleton
    static ForecastService provideRepoService(Retrofit retrofit){
        return retrofit.create(ForecastService.class);
    }
}
