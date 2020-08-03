package com.hadi.apptemplate.base;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    Context provideApplicationCOntext(){
        return application;
    }

//    @Binds Application bindApplication(Application application);

}
