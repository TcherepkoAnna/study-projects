package org.ibmt.diplom.catcareproject.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.anna.catcareproject.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import org.ibmt.diplom.catcareproject.Consts;
import org.ibmt.diplom.catcareproject.dao.ReportHelper;
import org.ibmt.diplom.catcareproject.model.ReportMealValuesByDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReportValuesDailyGraphActivity extends Activity implements Consts {

    GraphView graph;
    private CheckBox cbQua, cbEnergy, cbProt, cbFats, cbCarbs;
    List<ReportMealValuesByDate> arrayVaDay = null;
    ReportMealValuesByDate reportVDay;
    ReportHelper reportHelper;
    long catID;
    String catName = "";
    LineGraphSeries<DataPoint> seriesQua = null, seriesProt = null, seriesCarbs = null, seriesFats = null, seriesEnergy = null;
    DataPoint[] dp;
    final SimpleDateFormat formatter4DataPoints = new SimpleDateFormat("dd/MM/''yy");
    OnDataPointTapListener tapListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_values_daily_graph);

        cbQua = (CheckBox) findViewById(R.id.cb_quantity);
        cbEnergy = (CheckBox) findViewById(R.id.cb_energy);
        cbProt = (CheckBox) findViewById(R.id.cb_proteins);
        cbFats = (CheckBox) findViewById(R.id.cb_fats);
        cbCarbs = (CheckBox) findViewById(R.id.cb_carbs);
        graph = (GraphView) findViewById(R.id.gr_values_daily);

        Intent fromfragmentIntent = getIntent();
        catID = fromfragmentIntent.getExtras().getLong(SOME_FRAG_ARGS);
        catName = fromfragmentIntent.getExtras().getString(CAT_NAME);
        super.getActionBar().setTitle(catName);

        checkAllBoxes();
        addListenersToCheckBoxes();
        createSeries();
        addAllSeriesToGraph();
        setupGraphView();

    }

    private void addAllSeriesToGraph() {
        graph.addSeries(seriesQua);
        graph.addSeries(seriesEnergy);
        graph.addSeries(seriesProt);
        graph.addSeries(seriesFats);
        graph.addSeries(seriesCarbs);
    }

    private void checkAllBoxes() {
        cbQua.setChecked(true);
        cbEnergy.setChecked(true);
        cbProt.setChecked(true);
        cbFats.setChecked(true);
        cbCarbs.setChecked(true);
    }

    private void createSeries() {
        seriesQua = new LineGraphSeries<>(getDataPoints(R.string.item__quantity));
        seriesEnergy = new LineGraphSeries<>(getDataPoints(R.string.item__energy));
        seriesProt = new LineGraphSeries<>(getDataPoints(R.string.item__proteins));
        seriesFats = new LineGraphSeries<>(getDataPoints(R.string.item__fats));
        seriesCarbs = new LineGraphSeries<>(getDataPoints(R.string.item__carbs));

        setupSeriesQuantity(seriesQua);
        setupSeriesEnergy(seriesEnergy);
        setupSeriesProteins(seriesProt);
        setupSeriesFats(seriesFats);
        setupSeriesCarbs(seriesCarbs);
    }

    private void addListenersToCheckBoxes() {


        cbQua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    seriesQua = new LineGraphSeries<>(getDataPoints(R.string.item__quantity));
                    setupSeriesQuantity(seriesQua);
                    graph.addSeries(seriesQua);
                }
                if (!((CheckBox) v).isChecked()) {
                    graph.removeSeries(seriesQua);
                }
            }
        });

        cbEnergy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    seriesEnergy = new LineGraphSeries<>(getDataPoints(R.string.item__energy));
                    setupSeriesEnergy(seriesEnergy);
                    graph.addSeries(seriesEnergy);
                }
                if (!((CheckBox) v).isChecked()) {
                    graph.removeSeries(seriesEnergy);
                }
            }
        });

        cbProt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    seriesProt = new LineGraphSeries<>(getDataPoints(R.string.item__proteins));
                    setupSeriesProteins(seriesProt);
                    graph.addSeries(seriesProt);
                }
                if (!((CheckBox) v).isChecked()) {
                    graph.removeSeries(seriesProt);
                }
            }
        });

        cbFats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    seriesFats = new LineGraphSeries<>(getDataPoints(R.string.item__fats));
                    setupSeriesFats(seriesFats);
                    graph.addSeries(seriesFats);
                }
                if (!((CheckBox) v).isChecked()) {
                    graph.removeSeries(seriesFats);
                }
            }
        });

        cbCarbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    seriesCarbs = new LineGraphSeries<>(getDataPoints(R.string.item__carbs));
                    setupSeriesCarbs(seriesCarbs);
                    graph.addSeries(seriesCarbs);
                }
                if (!((CheckBox) v).isChecked()) {
                    graph.removeSeries(seriesCarbs);
                }
            }
        });

    }

    private void setupSeriesQuantity(LineGraphSeries<DataPoint> someSeries) {
        //blue +transparency
        someSeries.setColor(Color.argb(99, 1, 123, 147));
        someSeries.setThickness(7);
        someSeries.setDrawBackground(true);
        //fill area below the line
        someSeries.setBackgroundColor(Color.argb(70, 2, 238, 255));
        someSeries.setDrawDataPoints(true);
        someSeries.setDataPointsRadius(12);
        someSeries.setOnDataPointTapListener(tapListener);
        someSeries.setTitle(getResources().getString(R.string.item__quantity));
    }

    private void setupSeriesEnergy(LineGraphSeries<DataPoint> someSeries) {
        //red
        someSeries.setColor(Color.rgb(239, 2, 26));
        someSeries.setDrawDataPoints(true);
        someSeries.setOnDataPointTapListener(tapListener);
        someSeries.setTitle(getResources().getString(R.string.item__energy));
    }

    private void setupSeriesProteins(LineGraphSeries<DataPoint> someSeries) {
        //orange
        someSeries.setColor(Color.rgb(255, 123, 0));
        someSeries.setDrawDataPoints(true);
        someSeries.setOnDataPointTapListener(tapListener);
        someSeries.setTitle(getResources().getString(R.string.item__proteins));
    }

    private void setupSeriesFats(LineGraphSeries<DataPoint> someSeries) {
        //yellow
        someSeries.setColor(Color.rgb(255, 212, 0));
        someSeries.setDrawDataPoints(true);
        someSeries.setOnDataPointTapListener(tapListener);
        someSeries.setTitle(getResources().getString(R.string.item__fats));
    }

    private void setupSeriesCarbs(LineGraphSeries<DataPoint> someSeries) {
        //green
        someSeries.setColor(Color.rgb(2, 155, 0));
        someSeries.setDrawDataPoints(true);
        someSeries.setOnDataPointTapListener(tapListener);
        someSeries.setTitle(getResources().getString(R.string.item__carbs));
    }

    private void setupGraphView() {
        // set date label formatter
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this));
        graph.getGridLabelRenderer().setLabelFormatter(
                new DateAsXAxisLabelFormatter(this, formatter4DataPoints) {
                    @Override
                    public String formatLabel(double value, boolean isValueX) {
                        if (isValueX)
                            return formatter4DataPoints.format(value);
                        else
                            return super.formatLabel(value, isValueX);
                    }
                });

//        graph.getGridLabelRenderer().setNumHorizontalLabels(dp.length / 4);
        graph.getGridLabelRenderer().setNumHorizontalLabels(2);

        // as we use dates as labels, the human rounding to nice readable numbers is not necessary
        graph.getGridLabelRenderer().setHumanRounding(false);

        // set manual x bounds to have nice steps
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMaxX(dp[dp.length - 1].getX());
        graph.getViewport().setMinX(dp[0].getX());

        // set manual y bounds to have nice steps
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
//        double array[] = new double[dp.length];
//        for (int i = 0; i < dp.length; i++) {
//            array[i] = dp[i].getY();
//        }
//        Arrays.sort(array);
////        graph.getViewport().setMaxY(array[array.length - 1] * 2);

        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);

        manageLegend();

        graph.setTitle(getResources().getString(R.string.text_report_value_date));

        tapListener = new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Date pointDate = new Date((long) dataPoint.getX());
                String reportDate = formatter4DataPoints.format(pointDate);
                Toast.makeText(graph.getContext(), "" + reportDate + "\n" + dataPoint.getY(), Toast.LENGTH_SHORT).show();
            }
        };

    }

    private void manageLegend() {
        graph.getLegendRenderer().resetStyles();
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.MIDDLE);
        graph.getLegendRenderer().setBackgroundColor(Color.argb(0, 0, 0, 0));
    }


    private DataPoint[] getDataPoints(int iSeries) {
        reportHelper = new ReportHelper(this);
        arrayVaDay = reportHelper.getReportMValuesByDate(catID);
        dp = new DataPoint[arrayVaDay.size()];

        switch (iSeries) {
            case R.string.item__quantity:
                for (int i = 0; i < arrayVaDay.size(); i++) {
                    reportVDay = arrayVaDay.get(i);
                    String sdate = reportVDay.getDate();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date date = (Date) formatter.parse(sdate);
                        dp[i] = new DataPoint(date, reportVDay.getQuantity());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                return dp;
            case R.string.item__energy:
                for (int i = 0; i < arrayVaDay.size(); i++) {
                    reportVDay = arrayVaDay.get(i);
                    String sdate = reportVDay.getDate();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date date = (Date) formatter.parse(sdate);
                        dp[i] = new DataPoint(date, reportVDay.getEnergyValue());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                return dp;
            case R.string.item__proteins:
                for (int i = 0; i < arrayVaDay.size(); i++) {
                    reportVDay = arrayVaDay.get(i);
                    String sdate = reportVDay.getDate();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date date = (Date) formatter.parse(sdate);
                        dp[i] = new DataPoint(date, reportVDay.getProteinValue());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                return dp;
            case R.string.item__fats:
                for (int i = 0; i < arrayVaDay.size(); i++) {
                    reportVDay = arrayVaDay.get(i);
                    String sdate = reportVDay.getDate();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date date = (Date) formatter.parse(sdate);
                        dp[i] = new DataPoint(date, reportVDay.getFatsValue());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                return dp;
            case R.string.item__carbs:
                for (int i = 0; i < arrayVaDay.size(); i++) {
                    reportVDay = arrayVaDay.get(i);
                    String sdate = reportVDay.getDate();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date date = (Date) formatter.parse(sdate);
                        dp[i] = new DataPoint(date, reportVDay.getCarbsValue());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                return dp;
            default:
                for (int i = 0; i < arrayVaDay.size(); i++) {
                    reportVDay = arrayVaDay.get(i);
                    String sdate = reportVDay.getDate();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date date = (Date) formatter.parse(sdate);
                        dp[i] = new DataPoint(date, reportVDay.getQuantity());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                return dp;
        }

    }

}
