package org.ibmt.diplom.catcareproject.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.anna.catcareproject.R;

/**
 * Created by Anna on 19.12.2016.
 */
public class FragmentAbout extends android.support.v4.app.Fragment {

    ImageView ivCat;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_about, null);
        ivCat = (ImageView) v.findViewById(R.id.image_view_cat);

        return v;
    }
}
