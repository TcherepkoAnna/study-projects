package org.ibmt.diplom.catcareproject.view;

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

public class ReportQuantityDailyActivity extends WithActionBarActivity implements Consts, View.OnClickListener {

    ListView lv;
    Button btnGraph;
    long catID;
    String catName= "";
    ReportFoodQuantityByDate reportM;
    ReportHelper reportHelper;
    QuantitiesAdapter adapter;
    List<ReportFoodQuantityByDate> arrayQuantities = null;

    class QuantitiesAdapter extends ArrayAdapter<ReportMealValuesByDate> {

        Context context;

        public QuantitiesAdapter(Context context) {
            super(context, android.R.layout.simple_list_item_1);
            this.context = context;
        }

        @Override
        public int getCount() {
            return arrayQuantities.size();
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.report_quantity_line_template, parent, false);

            TextView tvDate = (TextView) layout.findViewById(R.id.tv_quantity_date);
            TextView tvQ = (TextView) layout.findViewById(R.id.tv_quantity_q);

            reportM = arrayQuantities.get(position);
            tvDate.setText(reportM.getDate());
            tvQ.setText(reportM.getQuantity().toString());

            return layout;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_quantity_daily);

        btnGraph = (Button) findViewById(R.id.btn_gr_quantaty_daily);
        btnGraph.setOnClickListener(this);

        Intent fromfragmentIntent = getIntent();
        catID = fromfragmentIntent.getExtras().getLong(SOME_FRAG_ARGS);
        catName = fromfragmentIntent.getExtras().getString(CAT_NAME);
        super.getActionBar().setTitle(catName);
        reportHelper = new ReportHelper(this);
        arrayQuantities = reportHelper.getReportFQuantityByDate(catID);

        lv = (ListView) findViewById(R.id.lv_report_quantity);
        adapter = new QuantitiesAdapter(this);
        lv.setAdapter(adapter);

        sendDataToActionBar(arrayQuantities);
    }

    private void sendDataToActionBar(List<ReportFoodQuantityByDate> arrayQuantities) {
        String out = "\n<" + getResources().getString(R.string.header_date) + ">\t\t\t__________________\t\t\t<" + getResources().getString(R.string.header_quantity_total) + ">\n\n";
        for (ReportFoodQuantityByDate r : arrayQuantities) {
            out += "\n" + r.getDate() + "\t\t\t______________\t\t\t" + r.getQuantity() + "\n";
        }
        setTextToSend(out);
        setSubject(getResources().getString(R.string.text_report_quantity) + ", " + catName);
    }

    @Override
    public void onClick(View v) {
        if (v == btnGraph) {
            Intent intentQuantityGraph = new Intent(this, ReportValuesDailyGraphActivity.class);
            intentQuantityGraph.putExtra(SOME_FRAG_ARGS, catID);
            intentQuantityGraph.putExtra(CAT_NAME, catName);
            startActivity(intentQuantityGraph);
        }
    }

    @Override
    public void onDestroy() {
        reportHelper.close();
        super.onDestroy();
    }
}
