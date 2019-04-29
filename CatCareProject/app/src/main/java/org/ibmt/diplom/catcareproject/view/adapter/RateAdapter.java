package org.ibmt.diplom.catcareproject.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.ibmt.diplom.catcareproject.Consts;
import com.example.anna.catcareproject.R;
import org.ibmt.diplom.catcareproject.model.IntakeRate;

import java.util.List;

/**
 * Created by Anna on 22.02.2017.
 */

public class RateAdapter extends ArrayAdapter<IntakeRate>  {

//    TextView name, bInfo;

    Context context;
    List<IntakeRate> array;

    public RateAdapter(Context context, List<IntakeRate> array) {
        super(context, android.R.layout.simple_list_item_1, array);
        this.context = context;
        this.array = array;
    }

    @Override
    public int getCount() {
        return array.size();
    }


    //for the first line of the spinner
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.adapter_2line_layout, parent, false);
        TextView name = (TextView) layout.findViewById(R.id.name_info);
        TextView bInfo = (TextView) layout.findViewById(R.id.basic_info);

        IntakeRate iRate = array.get(position);
        name.setText(iRate.getName() );
        bInfo.setText(iRate.getGender()  + ", " + iRate.getSex() + ", " + iRate.getCondition() );

        return layout;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LinearLayout layout;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = (LinearLayout) inflater.inflate(R.layout.adapter_2line_layout, parent, false);
        } else {
            layout = (LinearLayout) convertView;
        }

        TextView name = (TextView) layout.findViewById(R.id.name_info);
        TextView bInfo = (TextView) layout.findViewById(R.id.basic_info);

        IntakeRate iRate = array.get(position);
        name.setText(iRate.getName() );
        bInfo.setText(iRate.getGender() + ", " + context.getString(R.string.age) + " - " + iRate.getAge() + ", " + context.getString(R.string.weight) + " - " + iRate.getWeight() + ", " + iRate.getSex() + ", " + iRate.getCondition() );

        return layout;
    }
}
