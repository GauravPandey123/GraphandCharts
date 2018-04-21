package com.chariottask.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.chariottask.R;
import com.chariottask.activity.Utils.BarChartUtil;
import com.chariottask.activity.Utils.LineChartUtil;
import com.chariottask.activity.Utils.PieChartUtil;
import com.chariottask.activity.fragment.AnalyticsFragment;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, AnalyticsFragment.SubmitData {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    FragmentManager fm = getSupportFragmentManager();
    @BindView(R.id.chart1)
    LineChart chart1;
    @BindView(R.id.piechart)
    PieChart piechart;
    @BindView(R.id.barChart)
    BarChart barchart;
    @BindView(R.id.horozontalBarChart)
    HorizontalBarChart horozontalBarChart;
    @BindView(R.id.textViewAnalytics)
    TextView textViewAnalytics;
    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    private String titleString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getView().setVisibility(View.INVISIBLE);
        mapFragment.getMapAsync(this);
        ButterKnife.bind(this);
        setUpElements();
        setUpListners();

    }

    private void setUpElements() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.dashBoard);

    }

    private void setUpHorizontalChart() {
        horozontalBarChart.setVisibility(View.VISIBLE);
        BarChartUtil.showBarChart(horozontalBarChart);
    }

    private void setUpPieChart() {
        piechart.setVisibility(View.VISIBLE);
        PieChartUtil.showPiechart(piechart);
    }

    private void setUpBarChart() {
        barchart.setVisibility(View.VISIBLE);
        BarChartUtil.showBarChart(barchart);
    }

    private void setUpLineChart() {
        chart1.setVisibility(View.VISIBLE);
        LineChartUtil.showLineChart(chart1);

    }


    private void setUpListners() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnalyticsFragment dFragment = new AnalyticsFragment();

                dFragment.show(fm, "Dialog Fragment");

            }
        });


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void submitData(String statusString) {
        titleString = statusString;
        setUpData();

    }

    private void setUpData() {
        if (titleString.equalsIgnoreCase("Map")) {
            mapFragment.getView().setVisibility(View.VISIBLE);
            piechart.setVisibility(View.GONE);
            chart1.setVisibility(View.GONE);
            barchart.setVisibility(View.GONE);
            textViewAnalytics.setVisibility(View.GONE);
            horozontalBarChart.setVisibility(View.GONE);
        } else if (titleString.equalsIgnoreCase(getResources().getString(R.string.linechart))) {
            mapFragment.getView().setVisibility(View.INVISIBLE);
            barchart.setVisibility(View.GONE);
            horozontalBarChart.setVisibility(View.GONE);
            piechart.setVisibility(View.GONE);
            textViewAnalytics.setVisibility(View.GONE);
            setUpLineChart();
        } else if (titleString.equalsIgnoreCase(getResources().getString(R.string.barchart))) {
            mapFragment.getView().setVisibility(View.INVISIBLE);
            chart1.setVisibility(View.GONE);
            horozontalBarChart.setVisibility(View.GONE);
            piechart.setVisibility(View.GONE);
            textViewAnalytics.setVisibility(View.GONE);
            setUpBarChart();
        } else if (titleString.equalsIgnoreCase(getResources().getString(R.string.horizontalchart))) {
            mapFragment.getView().setVisibility(View.INVISIBLE);
            chart1.setVisibility(View.GONE);
            barchart.setVisibility(View.GONE);
            piechart.setVisibility(View.GONE);
            textViewAnalytics.setVisibility(View.GONE);
            setUpHorizontalChart();
        } else if (titleString.equalsIgnoreCase(getResources().getString(R.string.piechart))) {
            mapFragment.getView().setVisibility(View.INVISIBLE);
            chart1.setVisibility(View.GONE);
            barchart.setVisibility(View.GONE);
            horozontalBarChart.setVisibility(View.GONE);
            textViewAnalytics.setVisibility(View.GONE);
            setUpPieChart();
        }

    }


}
