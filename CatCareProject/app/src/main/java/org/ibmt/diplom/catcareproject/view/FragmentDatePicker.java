package org.ibmt.diplom.catcareproject.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Anna on 21.12.2016.
 */

public class FragmentDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {



    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c= Calendar.getInstance();
        DatePickerDialog dpd = new DatePickerDialog(getActivity(),this, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

        dpd.getDatePicker().setCalendarViewShown(true);
        dpd.getDatePicker().setSpinnersShown(false);
        return dpd;
    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
//        FragmentAddMeal.tvDate.setText(year + "-" + (month <= 9? "0"+ month : month) + "-"+ (dayOfMonth <= 9? "0" + dayOfMonth : dayOfMonth));

        Calendar c = Calendar.getInstance();
        c.set(year, month, dayOfMonth);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(c.getTime());
        FragmentAddMeal.tvDate.setText(formattedDate);
    }
}
