package com.hadi.apptemplate.forecasting;



import androidx.recyclerview.widget.DiffUtil;

import com.hadi.apptemplate.model.Forecast;

import java.util.List;



public class ForecastDiffCallback extends DiffUtil.Callback {

    private final List<Forecast> oldList;
    private final List<Forecast> newList;

    public ForecastDiffCallback(List<Forecast> oldList, List<Forecast> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).id() == newList.get(newItemPosition).id();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }
}
