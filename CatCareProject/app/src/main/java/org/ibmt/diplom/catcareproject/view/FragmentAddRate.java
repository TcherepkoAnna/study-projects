package org.ibmt.diplom.catcareproject.view;

import android.content.Context;
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
import android.widget.ListView;
import android.widget.Toast;

import com.example.anna.catcareproject.R;

import org.ibmt.diplom.catcareproject.Consts;
import org.ibmt.diplom.catcareproject.ConstsDB;
import org.ibmt.diplom.catcareproject.control.FragmentChangeListener;
import org.ibmt.diplom.catcareproject.dao.IntakeRateHelper;
import org.ibmt.diplom.catcareproject.model.IntakeRate;
import org.ibmt.diplom.catcareproject.view.adapter.RateAdapter;
import org.ibmt.diplom.catcareproject.ws.PetCareWebServiceSoap;

import java.util.List;

/**
 * Created by Anna on 20.12.2016.
 */

public class FragmentAddRate extends Fragment implements Consts, ConstsDB, View.OnClickListener {

    Button btnAdd, bSync;
    EditText etName;
    IntakeRate intakeRate;
    ListView lw;
    List<IntakeRate> ratesToDisplay;
    RateAdapter adapter;

    IntakeRateHelper helper;
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
        View v = inflater.inflate(R.layout.fragment_add_rate, null);
        btnAdd = (Button) v.findViewById(R.id.button_add_rate);
        btnAdd.setOnClickListener(this);
        etName = (EditText) v.findViewById(R.id.et_newIRateName);
        lw = (ListView) v.findViewById(R.id.list_view_rates);
        registerForContextMenu(lw);
        bSync = (Button) v.findViewById(R.id.button_sync_rates);
        bSync.setOnClickListener(this);

        helper = new IntakeRateHelper(getActivity());
        ratesToDisplay = helper.getAllRates();

//        adapter = new ArrayAdapter<IntakeRate>(getContext(), android.R.layout.simple_list_item_1, ratesToDisplay);
        adapter = new RateAdapter(getContext(), ratesToDisplay);

        lw.setAdapter(adapter);

        return v;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.context_menu_rates, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        intakeRate = ratesToDisplay.get(info.position);
        switch (item.getItemId()) {
            case R.id.update_rate:
                // Send the event to the host activity
                mCallback.changeToUpdate(intakeRate.getID(), new FragmentUpdateRate());
                return true;
            case R.id.delete_rate:
                // delete cat selected from list
                int number = helper.deleteRate(intakeRate.getID());
                Toast.makeText(getActivity(), getResources().getString(R.string.toastRateDeleted) + number, Toast.LENGTH_SHORT).show();
                adapter.clear();
                ratesToDisplay = helper.getAllRates();
                adapter.addAll(ratesToDisplay);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


    @Override
    public void onClick(View v) {
        if (v == btnAdd) {
            String newRName = etName.getText().toString();
            if (newRName.equals("")) {
                Toast.makeText(getActivity(), R.string.toast_fill_the_field, Toast.LENGTH_LONG).show();
                return;
            }

            intakeRate = new IntakeRate(newRName);

            for (int i = 0; i < ratesToDisplay.size(); i++) {
                IntakeRate existingRate = ratesToDisplay.get(i);
                if (ifExists(intakeRate, existingRate)) {
                    Toast.makeText(getActivity(), R.string.toast_rate_exists, Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            newRowId = helper.addRate(intakeRate);
            Toast tv = Toast.makeText(getActivity(), getResources().getString(R.string.toastNewRate) + newRowId, Toast.LENGTH_SHORT);
            tv.setGravity(Gravity.CENTER, 0, 0);
            tv.show();
            // Send the event to the host activity
            mCallback.changeToUpdate(newRowId, new FragmentUpdateRate());
        } else if (v == bSync) {

            addFromWS();
        }
    }

    private boolean ifExists(IntakeRate rateToCompare, IntakeRate existingRate) {
        return rateToCompare.getName().equalsIgnoreCase(existingRate.getName())
                && rateToCompare.getGender().equalsIgnoreCase(existingRate.getGender())
                && rateToCompare.getSex().equalsIgnoreCase(existingRate.getSex())
                && rateToCompare.getCondition().equalsIgnoreCase(existingRate.getCondition())
                && rateToCompare.getWeight().compareTo(existingRate.getWeight()) == 0
                && rateToCompare.getAge() == existingRate.getAge();
    }

    private void addFromWS() {
        PetCareWebServiceSoap soapClient = new PetCareWebServiceSoap();
        try {
            List<IntakeRate> rates = soapClient.getIntakeRates();
            boolean added = false;

            int count = 0;
            for (IntakeRate rate : rates) {

                boolean exists = false;

                for (int i = 0; i < ratesToDisplay.size(); i++) {
                    IntakeRate existingRate = ratesToDisplay.get(i);
                    if (ifExists(rate, existingRate)) {
                        exists = true;
                        break;
                    }
                }

                if (!exists) {
                    helper.addRate(rate);
                    ratesToDisplay.add(rate);
                    count++;
                    added = true;
                }
            }

            if (added) {
                adapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), getResources().getString(R.string.toast_rates_added) + count, Toast.LENGTH_SHORT).show();
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
