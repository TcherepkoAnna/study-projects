package org.ibmt.diplom.catcareproject.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.ibmt.diplom.catcareproject.Consts;
import org.ibmt.diplom.catcareproject.ConstsDB;
import com.example.anna.catcareproject.R;
import org.ibmt.diplom.catcareproject.dao.FoodHelper;
import org.ibmt.diplom.catcareproject.model.Food;


public class FragmentUpdateFood extends Fragment implements Consts, ConstsDB, View.OnClickListener {

    EditText foodName, foodState, fEnergy, fProts, fFats, fCarbs;
    TextInputLayout tilName, tilState, tilEnergy, tilProts, tilFats, tilCarbs;
    Button saveF;
    Food food;

    FoodHelper helper;
    long newRowId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_update_food, container, false);

        saveF = (Button) v.findViewById(R.id.button_save_food);
        saveF.setOnClickListener(this);
        foodName = (EditText) v.findViewById(R.id.et_food_name);
        foodState = (EditText) v.findViewById(R.id.et_food_state);
        fEnergy = (EditText) v.findViewById(R.id.et_food_energy);
        fProts = (EditText) v.findViewById(R.id.et_food_proteins);
        fFats = (EditText) v.findViewById(R.id.et_food_fats);
        fCarbs = (EditText) v.findViewById(R.id.et_food_carbs);
        tilEnergy = (TextInputLayout) v.findViewById(R.id.input_layout_food_energy);
        tilProts = (TextInputLayout) v.findViewById(R.id.input_layout_food_proteins);
        tilFats = (TextInputLayout) v.findViewById(R.id.input_layout_food_fats);
        tilCarbs = (TextInputLayout) v.findViewById(R.id.input_layout_food_carbs);
        tilName = (TextInputLayout) v.findViewById(R.id.input_layout_food_name);
        tilState = (TextInputLayout) v.findViewById(R.id.input_layout_food_state);

        helper = new FoodHelper(getActivity());

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            //getting foodToUpdate ID
            newRowId = bundle.getLong(SOME_FRAG_ARGS);
            //retieving food from DB by ID
            food = getFoodFromDB(newRowId);
        }
        return v;
    }

    private Food getFoodFromDB(long id) {
        Food f = helper.getFood(id);
        outputResultToFields(f);
        return f;
    }

    private void outputResultToFields(Food f) {
        if (f != null) {
            foodName.setText(f.getName());
            foodState.setText(f.getState());
            fEnergy.setText(f.getEnergy().toString());
            fProts.setText(f.getProteins().toString());
            fFats.setText(f.getFats().toString());
            fCarbs.setText(f.getCarbs().toString());
        }
    }

    @Override
    public void onClick(View v) {
        if (v == saveF) {
            //update food object from EditText fields
            food.setName(foodName.getText().toString());
            food.setState(foodState.getText().toString());
            food.setEnergy(Double.parseDouble(fEnergy.getText().toString()));
            food.setProteins(Double.parseDouble(fProts.getText().toString()));
            food.setFats(Double.parseDouble(fFats.getText().toString()));
            food.setCarbs(Double.parseDouble(fCarbs.getText().toString()));

            //update  in DB
            int number = helper.updateFood(food);
            String toToast = getResources().getString(R.string.toastFoodUpdated) + number;
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
