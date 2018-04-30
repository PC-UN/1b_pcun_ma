package com.pcun.b1.a1b_pcun_ma;

import android.os.Bundle;
import android.service.autofill.Dataset;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;


import java.util.ArrayList;
import java.util.List;

public class StatActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_stat);

        BarChart chart = (BarChart) findViewById(R.id.chart);

        List<BarEntry> dataSet = new ArrayList<>();
        dataSet.add(new BarEntry(1f, 1, "a"));
        dataSet.add(new BarEntry(2f, 2, "b"));
        dataSet.add(new BarEntry(3f, 3, "c"));
        dataSet.add(new BarEntry(4f, 3, "d"));
        dataSet.add(new BarEntry(5f, 4, "e"));
        dataSet.add(new BarEntry(6f, 2, "f"));
        dataSet.add(new BarEntry(7f, 3, "g"));
        dataSet.add(new BarEntry(8f, 1, "h"));
        dataSet.add(new BarEntry(9f, 5, "i"));
        dataSet.add(new BarEntry(10f, 4, "j"));
        dataSet.add(new BarEntry(11f, 2, "k"));
        dataSet.add(new BarEntry(12f, 7, "l"));



        List<String> labels = new ArrayList<>();
            labels.add("xxxxxxxx");
            labels.add("xxxxxxxx");
            labels.add("xxxxxxxx");
            labels.add("xxxxxxxx");
            labels.add("xxxxxxxx");
            labels.add("xxxxxxxx");
            labels.add("xxxxxxxx");
            labels.add("xxxxxxxx");
            labels.add("xxxxxxxx");
            labels.add("xxxxxxxx");
            labels.add("xxxxxxxx");
            labels.add("xxxxxxxx");

        BarDataSet set = new BarDataSet(dataSet, "Cant. Personas por Punto");
        BarData data = new BarData(set);
        chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        // data.setValueTextSize(16f);
        chart.setData(data);
        chart.invalidate();
    }
}
