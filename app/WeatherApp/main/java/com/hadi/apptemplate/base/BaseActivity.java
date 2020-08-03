package com.hadi.apptemplate.base;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.Router;
import com.hadi.apptemplate.R;
import com.hadi.apptemplate.data.ForecastGPS;
import com.hadi.apptemplate.data.PreferenceManager;
import com.hadi.apptemplate.di.Injector;
import com.hadi.apptemplate.di.ScreenInjector;
import com.hadi.apptemplate.home.MainActivity;
import com.hadi.apptemplate.home.MainActivityComponent;
import com.hadi.apptemplate.home.MainScreenBindingModule;
import com.hadi.apptemplate.ui.ScreenNavigator;

import java.util.UUID;

import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity {
    private String instanceId;

    private Router router;

    private ForecastGPS gps;


    @Inject
    ScreenInjector screenInjector;

    @Inject
    ScreenNavigator screenNavigator;

    @Inject
    MyApplication application;

    private static String INSTANCE_ID_KEY = "instance_id";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        if(savedInstanceState != null){
            instanceId = savedInstanceState.getString(INSTANCE_ID_KEY);
        }
        else {
            instanceId = UUID.randomUUID().toString();
        }
        Injector.inject(this);
        setContentView(layoutRes());


        ViewGroup screenContainer = findViewById(R.id.screen_container);

        if(screenContainer == null){
            throw new NullPointerException("The activity needs a view with id screen_container");

        }


        super.onCreate(savedInstanceState);

        router = Conductor.attachRouter(this, screenContainer, savedInstanceState);
        screenNavigator.initialiseWithRouter(router, initialController());


//        MainActivityComponent mainActivityComponent = DaggerMainActivityComponent.builder()
//                .mainActivityModule(MainScreenBindingModule(BaseActivity.this))
//                .build();


        backstackOperation();
        getLocationNow();
    }



    @LayoutRes
    protected abstract int layoutRes();

    protected abstract Controller initialController();

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(INSTANCE_ID_KEY, instanceId);
    }

    public String getInstanceId(){
        return instanceId;
    }


    @Override
    public void onBackPressed() {
       if(!screenNavigator.pop()){
           super.onBackPressed();
       }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        screenNavigator.clear();
        if(isFinishing()){

            Injector.clearComponent(this);

        }
    }

    public ScreenInjector getScreenInjector() {
        return screenInjector;
    }

    private void backstackOperation() {

        router.addChangeListener(new ControllerChangeHandler.ControllerChangeListener() {
            @Override
            public void onChangeStarted(@Nullable Controller to, @Nullable Controller from, boolean isPush, @NonNull ViewGroup container, @NonNull ControllerChangeHandler handler) {

            }

            @Override
            public void onChangeCompleted(@Nullable Controller to, @Nullable Controller from, boolean isPush, @NonNull ViewGroup container, @NonNull ControllerChangeHandler handler) {

                if(!isPush && from !=null){

                    Injector.inject(from);

                }
            }
        });
    }

    @Inject
    public void getLocationNow(){
        gps = new ForecastGPS(this);

        // Check if GPS enabled
        if(gps.canGetLocation()) {

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            PreferenceManager preferenceManager = new PreferenceManager(getApplication());
            preferenceManager.saveGPSLocation(String.valueOf(latitude), String.valueOf(longitude));

            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
            Log.e("GPS", String.valueOf(latitude));
        } else {
            // Can't get location.
            // GPS or network is not enabled.
            // Ask user to enable GPS/network in settings.
            gps.showSettingsAlert();
        }

    }





}
