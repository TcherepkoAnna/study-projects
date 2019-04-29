package org.ibmt.diplom.catcareproject.view;

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
import org.ibmt.diplom.catcareproject.model.ReportMealValues;

import java.util.List;

public class ReportMealsActivity extends WithActionBarActivity implements Consts {

    ListView lv;
    long catID;
    String catName;
    ReportMealValues reportM;
    ReportHelper reportHelper;
    MealsAdapter adapter;
    List<ReportMealValues> mealsArray = null;

    class MealsAdapter extends ArrayAdapter<ReportMealValues> {

        Context context;

        public MealsAdapter(Context context) {
            super(context, android.R.layout.simple_list_item_1);
            this.context = context;
        }

        @Override
        public int getCount() {
            return mealsArray.size();
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.report_meals_line_template, parent, false);

            TextView tvDate = (TextView) layout.findViewById(R.id.tv_meal_date);
            TextView tvName = (TextView) layout.findViewById(R.id.tv_meal_name);
            TextView tvQuan = (TextView) layout.findViewById(R.id.tv_meal_quantity);

            reportM = mealsArray.get(position);
            tvDate.setText(reportM.getDate());
            tvName.setText(reportM.getFoodName() + ", " + reportM.getFoodState());
            tvQuan.setText(reportM.getQuantity().toString());

            return layout;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_meals);

        Intent fromfragmentIntent = getIntent();
        catID = fromfragmentIntent.getExtras().getLong(SOME_FRAG_ARGS);
        catName = fromfragmentIntent.getExtras().getString(CAT_NAME);
        super.getActionBar().setTitle(catName);
        reportHelper = new ReportHelper(this);
        mealsArray = reportHelper.getReportMValues(catID);

        lv = (ListView) findViewById(R.id.lv_report_meals);
        adapter = new MealsAdapter(this);
        lv.setAdapter(adapter);

        sendDataToActionBar(mealsArray);
    }

    private void sendDataToActionBar(List<ReportMealValues> array) {
        String out = "\n<" + getResources().getString(R.string.header_date) + ">\t\t\t__________________\t\t\t<" +getResources().getString(R.string.header_food_name) +", " + getResources().getString(R.string.header_quantity) + ">\n\n";
        for (ReportMealValues r : array) {
            out += "\n" + r.getDate() + "\t\t\t______________\t\t\t" + r.getFoodName() + ", " + r.getFoodState() + ", " + r.getQuantity() + "\n";
        }
        setTextToSend(out);
        setSubject(getResources().getString(R.string.text_report_meals) + ", " + catName);
    }

    @Override
    public void onDestroy() {
        reportHelper.close();
        super.onDestroy();
    }
}
 