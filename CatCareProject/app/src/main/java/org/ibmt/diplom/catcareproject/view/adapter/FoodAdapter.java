package org.ibmt.diplom.catcareproject.view.adapter;

import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.ibmt.diplom.catcareproject.Consts;
import com.example.anna.catcareproject.R;
import org.ibmt.diplom.catcareproject.model.Food;

import java.util.List;

/**
 * Created by Anna on 22.02.2017.
 */

public class FoodAdapter extends ArrayAdapter<Food>  {

//    TextView name, bInfo;

    Context context;
    List<Food> array;

    public FoodAdapter(Context context, List<Food> array) {
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

        Food food = array.get(position);
        name.setText(food.getName() );
        bInfo.setText(food.getState() );

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

        Food food = array.get(position);
        name.setText(food.getName() );
        bInfo.setText(food.getState() );

        return layout;
    }
}
