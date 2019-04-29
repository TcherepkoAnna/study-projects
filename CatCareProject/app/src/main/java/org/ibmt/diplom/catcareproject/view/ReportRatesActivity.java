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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.ibmt.diplom.catcareproject.Consts;
import com.example.anna.catcareproject.R;
import org.ibmt.diplom.catcareproject.dao.ReportHelper;
import org.ibmt.diplom.catcareproject.model.ReportRates;

import java.util.ArrayList;

public class ReportRatesActivity extends Activity implements Consts {
    ListView lv;
    TextView tv;
    String catName;
    long catID;
    ReportRates reportR;
    ReportHelper reportHelper;
    ArrayList<String> arrayRR;
    String[] rrKeys;
    RatesAdapter adapter;


    class RatesAdapter extends ArrayAdapter<ReportRates> {

        Context context;

        public RatesAdapter(Context context) {
            super(context, android.R.layout.simple_list_item_1);
            this.context = context;
        }

        @Override
        public int getCount() {
            return arrayRR.size();
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.report_rates_line_template, parent, false);

            TextView tvKey = (TextView) layout.findViewById(R.id.rr_key);
            TextView tvValue = (TextView) layout.findViewById(R.id.rr_value);

            tvKey.setText(rrKeys[position]);
            tvValue.setText(arrayRR.get(position));

            return layout;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_rates);
        reportHelper = new ReportHelper(this);

        TextView tvKey = (TextView) findViewById(R.id.rr_key_head);
        TextView tvValue = (TextView) findViewById(R.id.rr_value_head);

        lv = (ListView) findViewById(R.id.lv_report_rates);
        tv = (TextView) findViewById(R.id.tv_report_rates);

        rrKeys = getResources().getStringArray(R.array.report_rates_keys_array);

        Intent fromfragmentIntent = getIntent();
        catID = fromfragmentIntent.getExtras().getLong(SOME_FRAG_ARGS);
        catName = fromfragmentIntent.getExtras().getString(CAT_NAME);
        super.getActionBar().setTitle(catName);

        reportR = reportHelper.getReportRates(catID);
        arrayRR = reportR.toSringArrayList();
        tvKey.setText(getResources().getString(R.string.item__name));
        tvValue.setText(reportR.getName());

        adapter = new RatesAdapter(this);
        lv.setAdapter(adapter);

//        Map <String, String> reportMap = reportR.convertToMap();
//        for(String key : reportMap.keySet()) {
//            String value = reportMap.get(key);
//            System.out.println(key + ":" + value);
//        }

    }
    @Override
    public void onDestroy() {
        reportHelper.close();
        super.onDestroy();
    }
}
