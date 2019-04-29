package org.ibmt.diplom.catcareproject.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.ibmt.diplom.catcareproject.Consts;

import com.example.anna.catcareproject.R;

import org.ibmt.diplom.catcareproject.dao.ReportHelper;
import org.ibmt.diplom.catcareproject.model.ReportFoodQuantityByDate;
import org.ibmt.diplom.catcareproject.model.ReportMealValuesByDate;

import java.util.List;

public class ReportValuesDailyActivity extends WithActionBarActivity implements Consts, View.OnClickListener {

    ListView lv;
    long catID;
    Button btnGraph;
    String catName = "";
    ReportMealValuesByDate reportM;
    ReportHelper reportHelper;
    ValuesAdapter adapter;
    List<ReportMealValuesByDate> mArray = null;

    class ValuesAdapter extends ArrayAdapter<ReportMealValuesByDate> {

        Context context;

        public ValuesAdapter(Context context, List<ReportMealValuesByDate> array) {
            super(context, android.R.layout.simple_list_item_1, array);
            this.context = context;
        }

        @Override
        public int getCount() {
            return mArray.size();
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.report_values_line_template, parent, false);

            TextView tvDate = (TextView) layout.findViewById(R.id.tv_val_date);
            TextView tvEnergy = (TextView) layout.findViewById(R.id.tv_val_energy);
            TextView tvProteins = (TextView) layout.findViewById(R.id.tv_val_proteins);
            TextView tvFats = (TextView) layout.findViewById(R.id.tv_val_fats);
            TextView tvCarbs = (TextView) layout.findViewById(R.id.tv_val_carbs);

            reportM = mArray.get(position);
            tvDate.setText(reportM.getDate());
            tvEnergy.setText(getResources().getString(R.string.item__energy) + ": " + reportM.getEnergyValue().toString());
            tvProteins.setText(getResources().getString(R.string.item__proteins) + ": " + reportM.getProteinValue().toString());
            tvFats.setText(getResources().getString(R.string.item__fats) + ": " + reportM.getFatsValue().toString());
            tvCarbs.setText(getResources().getString(R.string.item__carbs) + ": " + reportM.getCarbsValue().toString());

            return layout;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_values_daily);

        btnGraph = (Button) findViewById(R.id.btn_gr_values_daily);
        btnGraph.setOnClickListener(this);

        Intent fromfragmentIntent = getIntent();
        catID = fromfragmentIntent.getExtras().getLong(SOME_FRAG_ARGS);
        catName = fromfragmentIntent.getExtras().getString(CAT_NAME);
        super.getActionBar().setTitle(catName);
        reportHelper = new ReportHelper(this);
        mArray = reportHelper.getReportMValuesByDate(catID);

        lv = (ListView) findViewById(R.id.lv_report_values);
        adapter = new ValuesAdapter(this, mArray);
        lv.setAdapter(adapter);

        sendDataToActionBar(mArray);

    }

    private void sendDataToActionBar(List<ReportMealValuesByDate> array) {
        String out = "\n<" + getResources().getString(R.string.header_date) + ">\t\t\t__________________\t\t\t<" + getResources().getString(R.string.header_intake_value) + ">\n\n";
        for (ReportMealValuesByDate r : array) {
            out += "\n" + r.getDate() + "\t\t\t______________\t\t\t" + getResources().getString(R.string.item__energy) + " - " + r.getEnergyValue() + "\n" + getResources().getString(R.string.item__proteins) + " - " + r.getProteinValue() + ", " + getResources().getString(R.string.item__fats) + " - " + r.getFatsValue() + ", " + getResources().getString(R.string.item__carbs) + " - " + r.getCarbsValue() + "\n";
        }

        setTextToSend(out);
        setSubject(getResources().getString(R.string.text_report_value_date) + ", " + catName);

    }

    @Override
    public void onClick(View v) {
        if (v == btnGraph) {
            Intent intentValuesGraph = new Intent(this, ReportValuesDailyGraphActivity.class);
            intentValuesGraph.putExtra(SOME_FRAG_ARGS, catID);
            intentValuesGraph.putExtra(CAT_NAME, catName);
            startActivity(intentValuesGraph);
        }
    }

    @Override
    public void onDestroy() {
        reportHelper.close();
        super.onDestroy();
    }
}
