package com.hadi.apptemplate.base;

import android.app.Application;

import com.hadi.apptemplate.di.ActivityInjector;

import javax.inject.Inject;

import dagger.Provides;
import timber.log.BuildConfig;
import timber.log.Timber;

public class MyApplication extends Application {

    @Inject
    ActivityInjector activityInjector;

    private ApplicationComponent component;
    public static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        component.inject(this);

        if(BuildConfig.DEBUG){

            Timber.plant(new Timber.DebugTree());

        }


    }

    @Inject
    public MyApplication application(){
        return application;

    }

    @Inject
    public MyApplication(){
        this.application = application();
    }

    public ActivityInjector getActivityInjector() {

        return activityInjector;
    }
}
