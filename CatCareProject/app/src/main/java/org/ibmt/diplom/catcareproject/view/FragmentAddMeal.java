package org.ibmt.diplom.catcareproject.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.ibmt.diplom.catcareproject.Consts;
import org.ibmt.diplom.catcareproject.ConstsDB;
import com.example.anna.catcareproject.R;
import org.ibmt.diplom.catcareproject.dao.CalendarHelper;
import org.ibmt.diplom.catcareproject.dao.CatHelper;
import org.ibmt.diplom.catcareproject.dao.FoodHelper;
import org.ibmt.diplom.catcareproject.dao.MealHelper;
import org.ibmt.diplom.catcareproject.model.Calendar;
import org.ibmt.diplom.catcareproject.model.Cat;
import org.ibmt.diplom.catcareproject.model.Food;
import org.ibmt.diplom.catcareproject.model.Meal;
import org.ibmt.diplom.catcareproject.view.adapter.FoodAdapter;
import org.ibmt.diplom.catcareproject.view.adapter.PetAdapter;

import java.util.List;

/**
 * Created by Anna on 19.12.2016.
 */
public class FragmentAddMeal extends Fragment implements Consts, ConstsDB, View.OnClickListener {

    public static TextView tvDate;
    TextView tvCatN;
    Spinner spFoods, spCats;
    EditText etQuantity;
    Button btSaveMeal;
    List<Food> foodsToDisplay;
    List<Cat> catsToDisplay;
    FoodAdapter adapterFoods;
    PetAdapter adapterCats;


    CatHelper cHelper;
    FoodHelper fHelper;
    MealHelper mHelper;
    CalendarHelper calHelper;
    Cat cat;
    long catID;
    Meal meal;
    Food food;
    Double quantity;
    String date;
    Calendar calendar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_meal, container, false);
        btSaveMeal = (Button) v.findViewById(R.id.bt_meal_save);
        btSaveMeal.setOnClickListener(this);
        tvDate = (TextView) v.findViewById(R.id.tv_meal_date);
        tvDate.setOnClickListener(this);
//        tvCatN = (TextView) v.findViewById(R.id.tv_meal_cat);
        etQuantity = (EditText) v.findViewById(R.id.et_meal_quantity);
        spFoods = (Spinner) v.findViewById(R.id.sp_meal_foods);
        spCats = (Spinner) v.findViewById(R.id.sp_meals_cat);

        mHelper = new MealHelper(getActivity());


        //getting list of cats
        cHelper = new CatHelper(getActivity());
        catsToDisplay = cHelper.getAllCats();
        adapterCats = new PetAdapter(getContext(), catsToDisplay);
        spCats.setAdapter(adapterCats);
        //retrieving selected cat
        spCats.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cat = catsToDisplay.get(position);
//                tvCatN.setText(cat.getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            //getting catToUpdate ID
            catID = bundle.getLong(GLOBAL_PET);
            //retieving cat from DB by ID
            cat = cHelper.getCat(catID);
            //seting cat's name to TextView and spinner
            spCats.setSelection(getIndex(spCats, cat.toString() ));
        }

        //getting list of foods to spinner
        fHelper = new FoodHelper(getActivity());
        foodsToDisplay = fHelper.getAllFoods();
//        adapterFoods = new ArrayAdapter<Food>(getContext(), android.R.layout.simple_list_item_1, foodsToDisplay);
        adapterFoods = new FoodAdapter(getContext(), foodsToDisplay);
        spFoods.setAdapter(adapterFoods);
        //retrieving selected food
        spFoods.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                food = foodsToDisplay.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return v;
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
        if (v == tvDate) {
            //fallout datePicker dialog
            DialogFragment mdp = new FragmentDatePicker();
            mdp.show(getFragmentManager(), "date_picker");
        }
        if (v == btSaveMeal) {
            //check if all fields are filled
            if (etQuantity.getText().toString().trim().equals("")) {
                Toast.makeText(getActivity(), R.string.toast_fill_all_fields, Toast.LENGTH_LONG).show();
                return;
            }
            quantity = Double.parseDouble(etQuantity.getText().toString().trim());
            date = tvDate.getText().toString();
            if ( quantity.compareTo(0.0) == 0 ||
                    date.equals(getResources().getString(R.string.button_choose_meal_date))
//                    || tvCatN.getText().equals(getResources().getString(R.string.hint_chosen_cat))
                    ) {
                Toast.makeText(getActivity(), R.string.toast_fill_all_fields, Toast.LENGTH_LONG).show();
                return;
            }
            //add meal entry to DB
            meal = new Meal(food.getID(), food.getName(), food.getState(), quantity);
            long mealID = mHelper.addMeal(meal);
            //add calendar entry to DB
            calendar = new Calendar(date, mealID, cat.getId());
            calHelper = new CalendarHelper(getActivity());
            long calID = calHelper.addCalendar(calendar);
            Toast.makeText(getActivity(), getResources().getString(R.string.toastNewCalendar) + calID, Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onDestroyView() {
        cHelper.close();
        fHelper.close();
        mHelper.close();
        super.onDestroyView();
    }
}

