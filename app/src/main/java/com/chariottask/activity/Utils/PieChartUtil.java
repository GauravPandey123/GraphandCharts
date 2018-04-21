package com.chariottask.activity.Utils;

import android.graphics.Color;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PieChartUtil {

  public static void showPiechart(PieChart pieChart)
  {
      ArrayList<Entry> yvalues = new ArrayList<Entry>();
      yvalues.add(new Entry(8f, 0));
      yvalues.add(new Entry(15f, 1));
      yvalues.add(new Entry(12f, 2));
      yvalues.add(new Entry(25f, 3));
      yvalues.add(new Entry(23f, 4));
      yvalues.add(new Entry(17f, 5));

      PieDataSet dataSet = new PieDataSet(yvalues, "Election Results");

      ArrayList<String> xVals = new ArrayList<String>();

      xVals.add("January");
      xVals.add("February");
      xVals.add("March");
      xVals.add("April");
      xVals.add("May");
      xVals.add("June");

      PieData data = new PieData(xVals, dataSet);
      data.setValueFormatter(new PercentFormatter());
      // Default value
      //data.setValueFormatter(new DefaultValueFormatter(0));
      pieChart.setData(data);
      pieChart.setDescription("This is Pie Chart");

      pieChart.setDrawHoleEnabled(true);
      pieChart.setTransparentCircleRadius(25f);
      pieChart.setHoleRadius(25f);

      dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
      data.setValueTextSize(13f);
      data.setValueTextColor(Color.DKGRAY);

      pieChart.animateXY(1400, 1400);
  }

}
