package com.chariottask.activity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chariottask.R;
import com.chariottask.activity.model.ChartTypeModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChartTypeAdapter extends RecyclerView.Adapter<ChartTypeAdapter.ChartTypeViewHolder> {

    private List<ChartTypeModel> chartTypeModels;
    private Context mContext;

    public ChartTypeAdapter(List<ChartTypeModel> chartTypeModelArrayList, Context mContext) {
        this.chartTypeModels = chartTypeModelArrayList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ChartTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.part_chart_type_item, parent, false);
        return new ChartTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChartTypeViewHolder holder, int position) {

        holder.title.setText(chartTypeModels.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return chartTypeModels.size();
    }

    public class ChartTypeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        public ChartTypeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
