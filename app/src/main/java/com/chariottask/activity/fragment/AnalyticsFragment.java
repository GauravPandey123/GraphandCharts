package com.chariottask.activity.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chariottask.R;
import com.chariottask.activity.adapter.ChartTypeAdapter;
import com.chariottask.activity.helper.RecyclerTouchListener;
import com.chariottask.activity.model.ChartTypeModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AnalyticsFragment extends DialogFragment {
    View view;

    Unbinder unbinder;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.cardView)
    CardView cardView;
    @BindView(R.id.cardView1)
    CardView cardView1;
    @BindView(R.id.textViewMap)
    TextView textViewMap;
    private String titleString;
    private List<ChartTypeModel> data;
    private SubmitData submitData;
    ChartTypeAdapter chartTypeAdapter;
    String mapString;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        setUpElements();
        setUpLisnters();
        return view;
    }

    private void setUpElements() {

    }

    private void setUpLisnters() {
        data = new ArrayList<>();
        chartTypeAdapter = new ChartTypeAdapter(data, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(chartTypeAdapter);

        prepareChartData();

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapString=textViewMap.getText().toString();
                SubmitData setDataListListener = (SubmitData) getActivity();
                setDataListListener.submitData(mapString);
                dismiss();
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                titleString=data.get(position).getTitle();
                SubmitData setDataListListener = (SubmitData) getActivity();
                setDataListListener.submitData(titleString);
                dismiss();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }


    @Override
    public void onStart() {
        super.onStart();

        // safety check
        if (getDialog() == null) {
            return;
        }

        // set the animations to use on showing and hiding the dialog
        getDialog().getWindow().setWindowAnimations(
                R.style.dialog_animation_fade);

    }

    private void prepareChartData() {
        ChartTypeModel chartTypeModel = new ChartTypeModel(getResources().getString(R.string.linechart));
        data.add(chartTypeModel);

        chartTypeModel = new ChartTypeModel(getResources().getString(R.string.barchart));
        data.add(chartTypeModel);

        chartTypeModel = new ChartTypeModel(getResources().getString(R.string.horizontalchart));
        data.add(chartTypeModel);

        chartTypeModel = new ChartTypeModel(getResources().getString(R.string.piechart));
        data.add(chartTypeModel);

        chartTypeAdapter.notifyDataSetChanged();
    }


    public interface SubmitData {
        void submitData(String data);
    }


}
