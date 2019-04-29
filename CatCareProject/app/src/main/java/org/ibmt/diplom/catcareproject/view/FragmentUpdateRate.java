package org.ibmt.diplom.catcareproject.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.ibmt.diplom.catcareproject.Consts;
import org.ibmt.diplom.catcareproject.ConstsDB;
import com.example.anna.catcareproject.R;
import org.ibmt.diplom.catcareproject.dao.IntakeRateHelper;
import org.ibmt.diplom.catcareproject.model.IntakeRate;

/**
 * Created by Anna on 20.12.2016.
 */

public class FragmentUpdateRate extends Fragment implements Consts, ConstsDB, View.OnClickListener {

    EditText edtNmae, edtAge, edtWeight, edtCond, edtEnergy, edtProts, edtFats, edtCarbs;
    Spinner spSex, spGender, spCond;
    Button btnSave;
    IntakeRate rate;
    String sex, petGender, healthCond;
    private String[] sItems ;

    IntakeRateHelper helper;
    long newRowId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_update_rate, container, false);
        btnSave = (Button) v.findViewById(R.id.button_save_rate);
        btnSave.setOnClickListener(this);
        edtNmae = (EditText) v.findViewById(R.id.et_rate_name);
        edtAge = (EditText) v.findViewById(R.id.et_addAge);
        edtWeight = (EditText) v.findViewById(R.id.et_addWeight);
        spSex = (Spinner) v.findViewById(R.id.spinner_rates_sex);
        spCond = (Spinner) v.findViewById(R.id.sp_rates_condition);
        spGender = (Spinner) v.findViewById(R.id.sp_rates_pet);
        edtEnergy = (EditText) v.findViewById(R.id.et_rate_energy);
        edtProts = (EditText) v.findViewById(R.id.et_rate_proteins);
        edtFats = (EditText) v.findViewById(R.id.et_rate_fats);
        edtCarbs = (EditText) v.findViewById(R.id.et_rate_carbs);
        sItems = getResources().getStringArray(R.array.sex_array);

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

        helper = new IntakeRateHelper(getActivity());

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            //getting rateToUpdate ID
            newRowId = bundle.getLong(SOME_FRAG_ARGS);
            //retieving rate from DB by ID
            rate = getRateFromDB(newRowId);
        }
        return v;
    }

    private IntakeRate getRateFromDB(long id) {
        IntakeRate rate = helper.getRate(id);
        outputResultToFields(rate);
        return rate;
    }

    private void outputResultToFields(IntakeRate rate) {
        edtNmae.setText(rate.getName());
        edtAge.setText(String.valueOf(rate.getAge()));
        edtWeight.setText(rate.getWeight().toString());
        edtEnergy.setText(rate.getEnergy().toString());
        edtProts.setText(rate.getProteins().toString());
        edtFats.setText(rate.getFats().toString());
        edtCarbs.setText(rate.getCarbs().toString());
        spSex.setSelection(getIndex(spSex, rate.getSex().toString()));
        spGender.setSelection(getIndex(spGender, rate.getGender().toString()));
        spCond.setSelection(getIndex(spCond, rate.getCondition().toString()));
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
            //update rate object from EditText fields
            rate.setName(edtNmae.getText().toString());
            rate.setAge(Integer.parseInt(edtAge.getText().toString()));
            rate.setWeight(Double.parseDouble(edtWeight.getText().toString()));
            rate.setEnergy(Double.parseDouble(edtEnergy.getText().toString()));
            rate.setProteins(Double.parseDouble(edtProts.getText().toString()));
            rate.setFats(Double.parseDouble(edtFats.getText().toString()));
            rate.setCarbs(Double.parseDouble(edtCarbs.getText().toString()));
            rate.setSex(sex);
            rate.setGender(petGender);
            rate.setCondition(healthCond);

            //update rate in DB
            int number = helper.updateRate(rate);
            String toToast = getResources().getString(R.string.toastRateUpdated) + number;
            Toast tv = Toast.makeText(getActivity(), toToast, Toast.LENGTH_SHORT);
            tv.setGravity(Gravity.CENTER, 0, 0);
            tv.show();
        }

    }
    @Override
    public void onDestroyView() {
        helper.close();
        super.onDestroyView();
    }

}

