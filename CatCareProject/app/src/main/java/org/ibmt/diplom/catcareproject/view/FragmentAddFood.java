package org.ibmt.diplom.catcareproject.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.anna.catcareproject.R;

import org.ibmt.diplom.catcareproject.Consts;
import org.ibmt.diplom.catcareproject.ConstsDB;
import org.ibmt.diplom.catcareproject.control.FragmentChangeListener;
import org.ibmt.diplom.catcareproject.dao.FoodHelper;
import org.ibmt.diplom.catcareproject.model.Food;
import org.ibmt.diplom.catcareproject.view.adapter.FoodAdapter;
import org.ibmt.diplom.catcareproject.ws.PetCareWebServiceSoap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Аня on 18.12.2016.
 */

public class FragmentAddFood extends Fragment implements Consts, ConstsDB, View.OnClickListener {

    EditText foodName, foodState;
    Button addF, bSync;
    Food food;
    ListView lw;
    List<Food> arrayFoods;
    List<Food> foodsToDisplay;
    FoodAdapter adapter;


    FoodHelper helper;
    long newRowId;

    FragmentChangeListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // This makes sure that the container activity has implemented the callback interface.
        try {
            mCallback = (FragmentChangeListener) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_food, container, false);
        addF = (Button) v.findViewById(R.id.button_add_new_food);
        addF.setOnClickListener(this);
        foodName = (EditText) v.findViewById(R.id.et_food_name);
        foodState = (EditText) v.findViewById(R.id.et_food_state);
        lw = (ListView) v.findViewById(R.id.list_view_foods);
        bSync = (Button) v.findViewById(R.id.button_sync_food);
        bSync.setOnClickListener(this);

        helper = new FoodHelper(getActivity());
        arrayFoods = helper.getAllFoods();
        foodsToDisplay = new ArrayList<>(arrayFoods);

//        adapter = new ArrayAdapter<Food>(getContext(), android.R.layout.simple_list_item_1, foodsToDisplay);
        adapter = new FoodAdapter(getContext(), foodsToDisplay);

        lw.setAdapter(adapter);
        registerForContextMenu(lw);

        //update foodsToDisplay on input
        foodName.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
                foodsToDisplay.clear();
                String toCompareString1 = foodName.getText().toString().toLowerCase();

                for (int i = 0; i < arrayFoods.size(); i++) {
                    String temp = arrayFoods.get(i).getName().toLowerCase();
                    if (temp.length() >= toCompareString1.length()) {
                        String toCompareString2 = temp.substring(0, toCompareString1.length());
                        if (toCompareString2.contains(toCompareString1))
                            foodsToDisplay.add(arrayFoods.get(i));
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        foodName.setText("");
        foodState.setText("");
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.context_menu_foods, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        food = foodsToDisplay.get(info.position);
        switch (item.getItemId()) {
            case R.id.update_food:
                // Send the event to the host activity
                mCallback.changeToUpdate(food.getID(), new FragmentUpdateFood());
                return true;
            case R.id.delete_food:
                // delete cat selected from list
                int number = helper.deleteFood(food.getID());
                Toast.makeText(getActivity(), getResources().getString(R.string.toastFoodDeleted) + number, Toast.LENGTH_SHORT).show();
                adapter.clear();
                foodsToDisplay = helper.getAllFoods();
                adapter.addAll(foodsToDisplay);
                adapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == addF) {
            String newFName = foodName.getText().toString();
            String newFState = foodState.getText().toString();

            if (newFName.equals("") || newFState.equals("")) {
                Toast.makeText(getActivity(), R.string.toast_fill_all_fields, Toast.LENGTH_LONG).show();
                return;
            }

            for (int i = 0; i < foodsToDisplay.size(); i++) {
                Food existingFood = foodsToDisplay.get(i);
                if (ifExists(newFName, newFState, existingFood)) {
                    Toast.makeText(getActivity(), R.string.toast_food_exists, Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            food = new Food(newFName, newFState, null, null, null, null);
            newRowId = helper.addFood(food);
            Toast.makeText(getActivity(), getResources().getString(R.string.toastNewFood) + newRowId, Toast.LENGTH_SHORT).show();

            // Send the event to the host activity
            mCallback.changeToUpdate(newRowId, new FragmentUpdateFood());
        } else if (v == bSync) {
            addFromWS();
        }
    }

    private boolean ifExists(String newFName, String newFState, Food food) {
        String tempN = food.getName();
        String tempS = food.getState();
        return newFName.equalsIgnoreCase(tempN) && newFState.equalsIgnoreCase(tempS);
    }

    private void addFromWS() {
        PetCareWebServiceSoap soapClient = new PetCareWebServiceSoap();
        try {
            List<Food> foods = soapClient.getFoods();
            boolean added = false;

            int count = 0;
            for (Food food : foods) {
                String newFName = food.getName();
                String newFState = food.getState();
                boolean exists = false;

                for (int i = 0; i < foodsToDisplay.size(); i++) {
                    Food existingFood = foodsToDisplay.get(i);
                    if (ifExists(newFName, newFState, existingFood)) {
                        exists = true;
                        break;
                    }
                }

                if (!exists) {
                    helper.addFood(food);
                    foodsToDisplay.add(food);
                    count++;
                    added = true;
                }
            }

            if (added) {
                adapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), getResources().getString(R.string.toast_foods_added) + count, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception exc) {
            Toast.makeText(getActivity(), exc.getMessage(), Toast.LENGTH_SHORT).show();
            exc.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        helper.close();
        super.onDestroyView();
    }
}
