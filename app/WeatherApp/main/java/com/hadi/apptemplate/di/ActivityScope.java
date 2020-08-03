package com.hadi.apptemplate.di;


import android.app.Activity;

import com.hadi.apptemplate.home.MainActivity;
import com.hadi.apptemplate.home.MainActivityComponent;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

import dagger.Binds;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {



}
