package org.ibmt.diplom.catcareproject.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import org.ibmt.diplom.catcareproject.Consts;
import org.ibmt.diplom.catcareproject.ConstsDB;
import org.ibmt.diplom.catcareproject.control.FragmentChangeListener;

import com.example.anna.catcareproject.R;

import org.ibmt.diplom.catcareproject.dao.CatHelper;
import org.ibmt.diplom.catcareproject.model.Cat;
import org.ibmt.diplom.catcareproject.view.adapter.PetAdapter;

import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Anna on 02.12.2016.
 */

public class FragmentAddCat extends Fragment implements Consts, ConstsDB, View.OnClickListener {

    Button btnAdd;
    EditText etName;
    Cat cat;
    ListView lw;
    List<Cat> catsToDisplay;
    PetAdapter adapter;

    CatHelper helper;
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
        View v = inflater.inflate(R.layout.fragment_add_cat, null);
        btnAdd = (Button) v.findViewById(R.id.button_addCat);
        btnAdd.setOnClickListener(this);
        etName = (EditText) v.findViewById(R.id.editText_newCatName);
        lw = (ListView) v.findViewById(R.id.list_view_cats);

        helper = new CatHelper(getActivity());
        catsToDisplay = helper.getAllCats();
        adapter = new PetAdapter(getContext(), catsToDisplay);
        lw.setAdapter(adapter);
        registerForContextMenu(lw);

        return v;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.context_menu_cats, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        cat = catsToDisplay.get(info.position);
        switch (item.getItemId()) {
            case R.id.update_cat:
                // Send the event to the host activity
                mCallback.changeToUpdate(cat.getId(), new FragmentUpdateCat());
                return true;
            case R.id.delete_cat:
                int number = helper.deleteCat(cat.getId());
                Toast.makeText(getActivity(), getResources().getString(R.string.toastCatDeleted) + number, Toast.LENGTH_SHORT).show();
                adapter.clear();
                catsToDisplay = helper.getAllCats();
                adapter.addAll(catsToDisplay);
                return true;
//            case R.id.add_meal_cat:
//                // Send the event to the host activity
//                mCallback.changeToUpdate(cat.getId(), new FragmentAddMeal());
//                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        etName.setText("");
    }

    @Override
    public void onClick(View v) {
        if (v == btnAdd) {
            String newCatName = etName.getText().toString();
            if (newCatName.equals("")) {
                Toast.makeText(getActivity(), R.string.toast_fill_the_field, Toast.LENGTH_LONG).show();
                return;
            }
            cat = new Cat(newCatName);
            newRowId = helper.addCat(cat);
            Toast tv = Toast.makeText(getActivity(), getResources().getString(R.string.toastNewCat) + newRowId, Toast.LENGTH_SHORT);
            tv.setGravity(Gravity.CENTER, 0, 0);
            tv.show();
            // Send the event to the host activity
            mCallback.changeToUpdate(newRowId, new FragmentUpdateCat());
        }
    }

    @Override
    public void onDestroyView() {
        helper.close();
        super.onDestroyView();
    }

}
