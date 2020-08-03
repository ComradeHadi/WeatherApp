package com.hadi.apptemplate.base;


import com.hadi.apptemplate.data.ForecastServiceModule;
import com.hadi.apptemplate.networking.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityBindingModule.class,
        ServiceModule.class,
        ForecastServiceModule.class
        })


public interface ApplicationComponent {
    void inject(MyApplication myApplication);
}
