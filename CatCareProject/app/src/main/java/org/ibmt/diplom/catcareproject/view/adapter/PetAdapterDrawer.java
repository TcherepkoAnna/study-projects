package org.ibmt.diplom.catcareproject.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.anna.catcareproject.R;

import org.ibmt.diplom.catcareproject.model.Cat;

import java.util.List;

/**
 * Created by Anna on 07.03.2017.
 */

public class PetAdapterDrawer extends ArrayAdapter<Cat> {


    Context context;
    List<Cat> array;

    public PetAdapterDrawer(Context context, List<Cat> array) {
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
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.drawer_1l_spinner_layout, parent, false);
//        TextView name = (TextView) layout.findViewById(R.id.name_info);
//        TextView bInfo = (TextView) layout.findViewById(R.id.basic_info);

        ImageView ivDrawerPet = (ImageView) layout.findViewById(R.id.drawer_image_view);
        Cat cat = array.get(position);
        ivDrawerPet.setImageURI(Uri.parse(cat.getImageUri()));

//        name.setText(cat.getName() );
//        bInfo.setText(cat.getGender() + ", "  + cat.getCondition() );

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

        Cat cat = array.get(position);
        name.setText(cat.getName() );
        bInfo.setText(cat.getGender() + ", " + cat.getSex() + ", " + context.getString(R.string.age) + " - " + cat.getAge() + ", " + context.getString(R.string.weight) + " - " + cat.getWeight()  + ", " + cat.getCondition());

        return layout;
    }
}

