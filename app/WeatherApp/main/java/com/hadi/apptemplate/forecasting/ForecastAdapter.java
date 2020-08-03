package com.hadi.apptemplate.forecasting;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hadi.apptemplate.R;
import com.hadi.apptemplate.data.ForcastDateUtil;
import com.hadi.apptemplate.model.Forecast;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.RepoViewHolder> {

    private final RepoClickedListener listener;
    private final List<Forecast> data = new ArrayList<>();
    Context context;

    ForecastAdapter(RepoClickedListener listener) {
        this.listener = listener;
        setHasStableIds(true);
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View itemView = LayoutInflater.from(this.context).inflate(R.layout.forecast_list_item, parent, false);
        return new RepoViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).id();
    }

    void setData(List<Forecast> forecasts) {
        if (forecasts != null) {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ForecastDiffCallback(data, forecasts));
            data.clear();
            data.addAll(forecasts);
            diffResult.dispatchUpdatesTo(this);
        } else {
            data.clear();
            notifyDataSetChanged();
        }
    }

    static final class RepoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_day_of_week)
        TextView dayNameText;
//        @BindView(R.id.img_weather_image)
//        ImageView weahterDescriptionImage;
        @BindView(R.id.tv_degrees_celcius)
        TextView degreesCelciusText;
        @BindView(R.id.whole_background)
        LinearLayout background;

        @BindView(R.id.img_weather_image) ImageView image;



        private Forecast forecast;

        RepoViewHolder(View itemView, RepoClickedListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if (forecast != null) {
                    listener.onRepoClicked(forecast);
                }
            });
        }

        void bind(Forecast forecast) {
            this.forecast = forecast;
            int backgroundColour ;

            if(forecast.weather().get(0).description().equalsIgnoreCase("Clouds")){
                backgroundColour = R.color.background_cloudy;
                image.setBackgroundResource(R.drawable.forest_cloudy);

            }
            else{
                backgroundColour = R.color.background_sunny;
                image.setBackgroundResource(R.drawable.forest_cloudy);


            }
            background.setBackgroundColor(backgroundColour);

            dayNameText.setText(new ForcastDateUtil().getDayFromDate(forecast.dateInText()));

            degreesCelciusText.setText(NumberFormat.getInstance().format((int) forecast.data().averageTemperature() - 273));
        }
        }

        interface RepoClickedListener {

            void onRepoClicked(Forecast forecast);
        }
    }

