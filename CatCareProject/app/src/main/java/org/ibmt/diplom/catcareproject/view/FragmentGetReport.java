package org.ibmt.diplom.catcareproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.ibmt.diplom.catcareproject.Consts;
import org.ibmt.diplom.catcareproject.ConstsDB;
import com.example.anna.catcareproject.R;
import org.ibmt.diplom.catcareproject.dao.CatHelper;
import org.ibmt.diplom.catcareproject.dao.ReportHelper;
import org.ibmt.diplom.catcareproject.model.Cat;
import org.ibmt.diplom.catcareproject.view.adapter.PetAdapter;

import java.util.List;


/**
 * Created by Anna on 20.12.2016.
 */
public class FragmentGetReport extends Fragment implements Consts, ConstsDB, ListView.OnItemClickListener{

    Spinner spCats;
    private String[] reportsTitles;
    ListView lvReports;
    List<Cat> catsToDisplay;
    CatHelper cHelper;
    Cat cat;
    long catID;
    ReportHelper reportHelper;
    PetAdapter petAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_get_report, container, false);

        reportHelper = new ReportHelper(getActivity());

        spCats = (Spinner) v.findViewById(R.id.sp_reports_cat);
        //getting list of cats
        cHelper = new CatHelper(getActivity());
        catsToDisplay = cHelper.getAllCats();
        petAdapter = new PetAdapter(getContext(), catsToDisplay);
//        adapterCats = new ArrayAdapter<Cat>(getContext(), android.R.layout.simple_list_item_1, catsToDisplay);
        spCats.setAdapter(petAdapter);
        //retrieving selected cat
        spCats.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cat = catsToDisplay.get(position);
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


        reportsTitles = getResources().getStringArray(R.array.reports_array);
        lvReports = (ListView) v.findViewById(R.id.lv_report_titles);
        lvReports.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, reportsTitles));
//        registerForContextMenu(lvReports);
        lvReports.setOnItemClickListener(this);


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

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        MenuInflater inflater = getActivity().getMenuInflater();
//        inflater.inflate(R.menu.context_menu_reports, menu);
//    }
//
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//        reportItem = reportsTitles[info.position];
//        switch (item.getItemId()) {
//            case R.id.show_report:
//
//                reportHelper.getReportRates(cat.getId());
//
//                return true;
//            default:
//                return super.onContextItemSelected(item);
//        }
//    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        catID = cat.getId();
        String[] some_array = getResources().getStringArray(R.array.reports_array);
        switch (position) {
            case 0:
                Intent intentRates= new Intent(getActivity(), ReportRatesActivity.class);
                intentRates.putExtra(SOME_FRAG_ARGS, catID);
                intentRates.putExtra(CAT_NAME, cat.getName());
                startActivity(intentRates);
                break;
            case 1:
                Intent intentValues= new Intent(getActivity(), ReportValuesDailyActivity.class);
                intentValues.putExtra(SOME_FRAG_ARGS, catID);
                intentValues.putExtra(CAT_NAME, cat.getName());
                startActivity(intentValues);
                break;
            case 2:
                Intent intentMeals= new Intent(getActivity(), ReportMealsActivity.class);
                intentMeals.putExtra(SOME_FRAG_ARGS, catID);
                intentMeals.putExtra(CAT_NAME, cat.getName());
                startActivity(intentMeals);
                break;

            case 3:
                Intent intentQuantity= new Intent(getActivity(), ReportQuantityDailyActivity.class);
                intentQuantity.putExtra(SOME_FRAG_ARGS, catID);
                intentQuantity.putExtra(CAT_NAME, cat.getName());
                startActivity(intentQuantity);
                break;
            case 4:
                String sAVG = reportHelper.getReportDailyAvgFood(catID);
                Toast.makeText(getActivity(), sAVG, Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(getActivity(), "default", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onDestroyView() {
        cHelper.close();
        reportHelper.close();
        super.onDestroyView();
    }
}
