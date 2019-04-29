package org.ibmt.diplom.catcareproject.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.graphics.Color;
import android.widget.Toast;

import org.ibmt.diplom.catcareproject.Consts;
import org.ibmt.diplom.catcareproject.dao.ReportHelper;
import org.ibmt.diplom.catcareproject.model.ReportMealValues;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.example.anna.catcareproject.R;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;
//import com.jjoe64.graphview.CustomLabelFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ReportQuantityDailyGraphActivity extends Activity implements Consts {


    GraphView graph;
//    List<ReportFoodQuantityByDate> arrayQuantities = null;
    List<ReportMealValues> arrayVaQuas = null;
    ReportMealValues reportM;
    ReportHelper reportHelper;
    long catID;
    String catName= "";
    LineGraphSeries<DataPoint> seriesQua = null, seriesProt = null, seriesCarbs = null, seriesFats = null, seriesEnergy = null;
    DataPoint[] dp;
    final SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/''yy");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_quantity_daily_graph);

        graph = (GraphView) findViewById(R.id.gr_quantity_daily);

        Intent fromfragmentIntent = getIntent();
        catID = fromfragmentIntent.getExtras().getLong(SOME_FRAG_ARGS);
        catName = fromfragmentIntent.getExtras().getString(CAT_NAME);
        reportHelper = new ReportHelper(this);
        getActionBar().setTitle(catName);
        arrayVaQuas = reportHelper.getReportMValues(catID);


//        BarGraphSeries<DataPoint> series2 = new BarGraphSeries<>();
//        series2.setSpacing(1);
//        series2.setDataWidth(0.5);

        seriesQua = new LineGraphSeries<>(getDataPoints(R.string.item__quantity));
        seriesProt = new LineGraphSeries<>(getDataPoints(R.string.item__proteins));

        seriesProt.setColor(Color.rgb(255, 123, 0));
        seriesProt.setDrawDataPoints(true);

        setupSeriesQuantity(seriesQua, formatter2, graph);

        graph.addSeries(seriesQua);
        graph.addSeries(seriesProt);

        setupGraphView();


    }

    private void setupGraphView() {
        // set date label formatter
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this));
        graph.getGridLabelRenderer().setLabelFormatter(
                new DateAsXAxisLabelFormatter(this, formatter2) {
                    @Override
                    public String formatLabel(double value, boolean isValueX) {
                        if (isValueX)
                            return formatter2.format(value);
                        else
                            return super.formatLabel(value, isValueX);
                    }
                });

        graph.getGridLabelRenderer().setNumHorizontalLabels(dp.length/4);

        // as we use dates as labels, the human rounding to nice readable numbers is not necessary
        graph.getGridLabelRenderer().setHumanRounding(false);

        // set manual x bounds to have nice steps
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMaxX(dp[dp.length - 1].getX());
        graph.getViewport().setMinX(dp[0].getX());

        // set manual y bounds to have nice steps
        graph.getViewport().setYAxisBoundsManual(true);
        double array[] = new double[dp.length];
        for (int i = 0; i < dp.length; i++) {
            array[i] = dp[i].getY();
        }
        Arrays.sort(array);
        graph.getViewport().setMaxY(array[array.length - 1]*2);
        graph.getViewport().setMinY(0);

        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);

        graph.setTitle(getResources().getString(R.string.text_report_quantity) + getResources().getString(R.string.text_report_prot));
    }

    private static void setupSeriesQuantity(LineGraphSeries<DataPoint> seriesQua, final SimpleDateFormat formatter2, final GraphView graph) {
        //+transparency
        seriesQua.setColor(Color.argb(99, 1, 123, 147));
        seriesQua.setThickness(7);
        seriesQua.setDrawBackground(true);
        //fill area below the line
        seriesQua.setBackgroundColor(Color.argb(70, 2, 238, 255));
        seriesQua.setDrawDataPoints(true);
        seriesQua.setDataPointsRadius(12);

        seriesQua.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Date pointDate = new Date((long) dataPoint.getX());
                String reportDate = formatter2.format(pointDate);
                Toast.makeText(graph.getContext(), "" + reportDate + "\n" + dataPoint.getY(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private DataPoint[] getDataPoints(int iSeries) {
        dp = new DataPoint[arrayVaQuas.size()];

        switch (iSeries){
            case R.string.item__quantity:
                for (int i = 0; i < arrayVaQuas.size(); i++) {
                    reportM = arrayVaQuas.get(i);
                    String sdate = reportM.getDate();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date date = (Date) formatter.parse(sdate);
                        dp[i] = new DataPoint(date, reportM.getQuantity());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                return dp;
            case R.string.item__proteins:
                for (int i = 0; i < arrayVaQuas.size(); i++) {
                    reportM = arrayVaQuas.get(i);
                    String sdate = reportM.getDate();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date date = (Date) formatter.parse(sdate);
                        dp[i] = new DataPoint(date, reportM.getProteinValue());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                return dp;
            default:
                for (int i = 0; i < arrayVaQuas.size(); i++) {
                    reportM = arrayVaQuas.get(i);
                    String sdate = reportM.getDate();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date date = (Date) formatter.parse(sdate);
                        dp[i] = new DataPoint(date, reportM.getQuantity());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                return dp;
        }

    }


}
