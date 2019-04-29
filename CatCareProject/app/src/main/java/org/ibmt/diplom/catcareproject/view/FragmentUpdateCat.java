package org.ibmt.diplom.catcareproject.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.support.design.widget.TextInputLayout;

import org.ibmt.diplom.catcareproject.Consts;
import org.ibmt.diplom.catcareproject.ConstsDB;

import com.example.anna.catcareproject.R;

import org.ibmt.diplom.catcareproject.dao.CatHelper;
import org.ibmt.diplom.catcareproject.dao.IntakeRateHelper;
import org.ibmt.diplom.catcareproject.model.Cat;
import org.ibmt.diplom.catcareproject.model.IntakeRate;
import org.ibmt.diplom.catcareproject.view.adapter.RateAdapter;

import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * Created by Anna on 02.12.2016.
 */

public class FragmentUpdateCat extends Fragment implements Consts, ConstsDB, View.OnClickListener {

    EditText edtNmae, edtAge, edtWeight;
    TextInputLayout tilName, tilWeight, tilAge;
    Button btnSave;
    Cat cat;
    Spinner spSex, spRate, spCond, spGender;
    String sex, petGender, healthCond;
    List<IntakeRate> ratesToDisplay;

    CatHelper cHelper;
    IntakeRateHelper rHelper;
    IntakeRate rate;
    ArrayAdapter<IntakeRate> adapter;
    long newRowId;

    Uri uri;
    String stringUri = "";
    ImageView ivPet;


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_REQUEST) {
            if (resultCode == RESULT_OK) {
                if (!(data.getData().toString().equalsIgnoreCase(""))) {
                    uri = data.getData();
                    stringUri = uri.toString();
//                    uri = Uri.parse(stringUri);
                    ivPet.setImageURI(uri);
                }

            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_update_cat, container, false);
        btnSave = (Button) v.findViewById(R.id.button_updateCat);
        btnSave.setOnClickListener(this);
        edtNmae = (EditText) v.findViewById(R.id.editText_updateCatName);
        edtAge = (EditText) v.findViewById(R.id.editText_addCatAge);
        edtWeight = (EditText) v.findViewById(R.id.editText_addCatWeight);
        spSex = (Spinner) v.findViewById(R.id.spinner_cats_sex);
        spRate = (Spinner) v.findViewById(R.id.sp_cats_rate);
        spCond = (Spinner) v.findViewById(R.id.sp_cats_condition);
        spGender = (Spinner) v.findViewById(R.id.sp_cats_gender);
        tilName = (TextInputLayout) v.findViewById(R.id.input_layout_updateCatName);
        tilAge = (TextInputLayout) v.findViewById(R.id.input_layout_addCatAge);
        tilWeight = (TextInputLayout) v.findViewById(R.id.input_layout_addCatWeight);

        ivPet = (ImageView) v.findViewById(R.id.image_view_cat);
//        uri = Uri.parse(stringUri);
//        ivDrawerPet.setImageURI(uri);

        ivPet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent getImageIntent = new Intent(Intent.ACTION_PICK);
                getImageIntent.setType("image/*");
                startActivityForResult(getImageIntent, MY_REQUEST);
            }
        });

        spGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                petGender = spGender.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spSex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sex = spSex.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spCond.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                healthCond = spCond.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //getting list of rates to spinner
        rHelper = new IntakeRateHelper(getActivity());
        ratesToDisplay = rHelper.getAllRates();
//        adapter = new ArrayAdapter<IntakeRate>(getContext(), android.R.layout.simple_list_item_1, ratesToDisplay);
        adapter = new RateAdapter(getContext(), ratesToDisplay);
        spRate.setAdapter(adapter);
        spRate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //retrieving selected rate
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rate = ratesToDisplay.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        cHelper = new CatHelper(getActivity());

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            //getting catToUpdate ID
            newRowId = bundle.getLong(SOME_FRAG_ARGS);
            //retieving cat from DB by ID
            cat = getCatFromDB(newRowId);
        }
        return v;
    }

    private Cat getCatFromDB(long id) {
        Cat cat = cHelper.getCat(id);
        outputResultToFields(cat);
        return cat;
    }

    private void outputResultToFields(Cat cat) {
        edtNmae.setText(cat.getName());
        edtAge.setText(String.valueOf(cat.getAge()));
        edtWeight.setText(cat.getWeight().toString());
        spGender.setSelection(getIndex(spGender, cat.getGender().toString()));
        spSex.setSelection(getIndex(spSex, cat.getSex().toString()));
        spCond.setSelection(getIndex(spCond, cat.getCondition().toString()));
        //retrieving cat's full rate string (assigned previously) from DB to set into spinner
        rate = rHelper.getRate(cat.getIntakeRate());
        spRate.setSelection(getIndex(spRate, rate.toString()));
        if (!cat.getImageUri().equalsIgnoreCase("")) {
            ivPet.setImageURI(Uri.parse(cat.getImageUri()));
        }
    }

    private int getIndex(Spinner spinner, String myString) {
        int index = 0;
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public void onClick(View v) {
        if (v == btnSave) {
            //update cat object from EditText fields
            cat.setName(edtNmae.getText().toString());
            cat.setAge(Integer.parseInt(edtAge.getText().toString()));
            cat.setWeight(Double.parseDouble(edtWeight.getText().toString()));
            cat.setIntakeRate(rate.getID());
            cat.setGender(petGender);
            cat.setCondition(healthCond);
            cat.setSex(sex);
            if (!stringUri.equalsIgnoreCase("")){
                cat.setImageUri(stringUri);
            }

            //update cat in DB
            int number = cHelper.updateCat(cat);
            String toToast = getResources().getString(R.string.toastCatUpdated) + number;
            Toast tv = Toast.makeText(getActivity(), toToast, Toast.LENGTH_SHORT);
            tv.setGravity(Gravity.CENTER, 0, 0);
            tv.show();
        }
//        if (v == btnAddPh) {
//            Toast.makeText(getActivity(), "TO DO", Toast.LENGTH_SHORT).show();
//        }

    }

    @Override
    public void onDestroyView() {
        cHelper.close();
        rHelper.close();
        super.onDestroyView();
    }
}
