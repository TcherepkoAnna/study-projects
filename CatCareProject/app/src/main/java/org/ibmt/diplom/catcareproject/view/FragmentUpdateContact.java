package org.ibmt.diplom.catcareproject.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anna.catcareproject.R;

import org.ibmt.diplom.catcareproject.Consts;
import org.ibmt.diplom.catcareproject.ConstsDB;
import org.ibmt.diplom.catcareproject.dao.ContactHelper;
import org.ibmt.diplom.catcareproject.model.Contact;

import java.util.List;

/**
 * Created by Anna on 04.03.2017.
 */

public class FragmentUpdateContact extends Fragment implements Consts, ConstsDB, View.OnClickListener {

    Button btnSave;
    EditText edtNmae, edtDescr, edtNumber;
    ContactHelper helper;
    Contact contact;
    List<Contact> contactsToDisplay;

    long newRowId;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_update_contact, container, false);
        btnSave = (Button) v.findViewById(R.id.button_contact_save);
        btnSave.setOnClickListener(this);
        edtNmae = (EditText) v.findViewById(R.id.editText_contact_name);
        edtDescr = (EditText) v.findViewById(R.id.editText_contact_descr);
        edtNumber = (EditText) v.findViewById(R.id.editText_contact_number);

        helper = new ContactHelper(getActivity());

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            //getting contact ID
            newRowId = bundle.getLong(SOME_FRAG_ARGS);
            if (newRowId != 0) {
                //retieving contact from DB by ID
                contact = getContactFromDB(newRowId);
            } else {
                contact = new Contact("", "", "");
            }
        }
        return v;
    }

    private Contact getContactFromDB(long id) {
        Contact contact = helper.getContact(id);
        outputResultToFields(contact);
        return contact;
    }

    private void outputResultToFields(Contact contact) {
        edtNmae.setText(contact.getName());
        edtNumber.setText(contact.getNumber());
        edtDescr.setText(contact.getDiscription());
    }


    @Override
    public void onClick(View v) {
        if (v == btnSave) {
            //update contact object from EditText fields
            contact.setName(edtNmae.getText().toString().trim());
            contact.setNumber(edtNumber.getText().toString().trim());
            contact.setDiscription(edtDescr.getText().toString().trim());

            if (contact.getName().equals("") || contact.getNumber().equals("")) {
                Toast.makeText(getActivity(), R.string.toast_fill_all_fields, Toast.LENGTH_LONG).show();
                return;
            }

            String toToast = "";

            if (contact.getId() != 0) {
                //update contact in DB
                int number = helper.updateContact(contact);
                toToast = getResources().getString(R.string.toastContactUpdated) + number;
            } else {
                //check if exists
                contactsToDisplay = helper.getAllContacts();
                for (int i = 0; i < contactsToDisplay.size(); i++) {
                    String tempName = contactsToDisplay.get(i).getName();
                    String tempNumber = contactsToDisplay.get(i).getNumber();
                    if (contact.getName().equalsIgnoreCase(tempName) && contact.getNumber().equalsIgnoreCase(tempNumber)) {
                        Toast.makeText(getActivity(), R.string.toast_contact_exists, Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                //add contact to DB
                long number = helper.addContact(contact);
                toToast = getResources().getString(R.string.toastNewContact) + number;
            }


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