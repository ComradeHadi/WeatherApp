package com.hadi.apptemplate.forecasting;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hadi.apptemplate.R;
import com.hadi.apptemplate.base.BaseController;
import com.hadi.apptemplate.base.MyApplication;
import com.hadi.apptemplate.home.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class TrendingForecastController extends BaseController {

    @Inject
    TrendingForecastPresenter trendingReposPresenter;
    @Inject
    TrendingForecastViewModel trendingForecastViewModel;



    @BindView(R.id.forecast_list) RecyclerView repoList;
    @BindView(R.id.loading_indicator) View loadingView;
    @BindView(R.id.erro_tv) TextView errorText;

    /**
     *
     * @param
     */
    @BindView(R.id.tv_current_temperature) TextView currentTemperature;
    @BindView(R.id.tv_minimum_temperature) TextView minimumTemperature;
    @BindView(R.id.tv_maximum_temperature) TextView maximumTemperature;
    @BindView(R.id.weather_description) TextView desc;
    @BindView(R.id.weather_data_banner)  LinearLayout layoutBanner;


    @Override
    protected void onViewBound(View view) {
        repoList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        repoList.setAdapter(new ForecastAdapter(trendingReposPresenter));
    }

    @Override
    protected Disposable[] subscriptions() {
        return new Disposable[]{
                trendingForecastViewModel.loading()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loading -> {
                    loadingView.setVisibility(loading ? View.VISIBLE : View.GONE);
                    repoList.setVisibility(loading ? View.GONE : View.VISIBLE);
                    errorText.setVisibility(loading ? View.GONE : errorText.getVisibility());
                }),

                trendingForecastViewModel.repos()
                .observeOn(AndroidSchedulers.mainThread())
               // .subscribe(((ForecastAdapter)repoList.getAdapter())::setData),
                .subscribe(data -> {
                    Log.e("DATA P", data.get(0).toString());
                    currentTemperature.setText(String.valueOf((int) data.get(0).data().averageTemperature() - 273 ));
                    minimumTemperature.setText(String.valueOf((int) data.get(0).data().minimum() - 273 ));
                    maximumTemperature.setText(String.valueOf((int) data.get(0).data().maximum() - 273 ));

                    if(data.get(0).weather().get(0).description().equalsIgnoreCase("Clouds")){
                        Log.e("HERE", "clouds");
                        desc.setText("CLOUDY");

                        final int sdk = android.os.Build.VERSION.SDK_INT;
                        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            layoutBanner.setBackgroundDrawable(ContextCompat.getDrawable(MyApplication.application, R.drawable.forest_cloudy) );
                        } else {
                            layoutBanner.setBackground(ContextCompat.getDrawable(MyApplication.application, R.drawable.forest_cloudy));
                        }

                    }
                    else {
                        desc.setText("SUNNY");


                        final int sdk = android.os.Build.VERSION.SDK_INT;
                        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            layoutBanner.setBackgroundDrawable(ContextCompat.getDrawable(MyApplication.application, R.drawable.forest_sunny) );
                        } else {
                            layoutBanner.setBackground(ContextCompat.getDrawable(MyApplication.application, R.drawable.forest_sunny));
                        }


                    }


                    ((ForecastAdapter)repoList.getAdapter()).setData(data);
//                    loadingView.setVisibility(loading ? View.VISIBLE : View.GONE);
//                    repoList.setVisibility(loading ? View.GONE : View.VISIBLE);
//                    errorText.setVisibility(loading ? View.GONE : errorText.getVisibility());
                }),

                trendingForecastViewModel.error()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(erroRes->{
                    if(erroRes == -1){
                        errorText.setText(null);
                        errorText.setVisibility(View.GONE);

                    }

                    else {
                        errorText.setVisibility(View.VISIBLE);
                        repoList.setVisibility(View.GONE);
                        errorText.setText(erroRes);
                    }
                })
        };
    }

    @Override
    protected int layoutRes() {
        return R.layout.screen_trending_forecast;
    }
}
