package org.ibmt.diplom.catcareproject.view;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.anna.catcareproject.R;

public class FragmentLabTests extends android.support.v4.app.Fragment {

    ImageView ivCat;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lab_tests, null);
        ivCat = (ImageView) v.findViewById(R.id.image_view_cat);

        return v;
    }
}