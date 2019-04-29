package org.ibmt.diplom.catcareproject.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.anna.catcareproject.R;

import org.ibmt.diplom.catcareproject.Consts;
import org.ibmt.diplom.catcareproject.ConstsDB;
import org.ibmt.diplom.catcareproject.control.FragmentChangeListener;
import org.ibmt.diplom.catcareproject.dao.ContactHelper;
import org.ibmt.diplom.catcareproject.model.Contact;
import org.ibmt.diplom.catcareproject.view.adapter.ContactAdapter;

import java.util.List;

/**
 * Created by Anna on 19.12.2016.
 */
public class FragmentContacts extends Fragment implements Consts, ConstsDB, View.OnClickListener {


    Button btnAdd;
    ListView lw;
    List<Contact> contactsToDisplay;
    ContactAdapter adapter;
    Contact contact;

    ContactHelper helper;
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
        View v = inflater.inflate(R.layout.fragment_contacts, null);
        btnAdd = (Button) v.findViewById(R.id.button_add_contact);
        btnAdd.setOnClickListener(this);
        lw = (ListView) v.findViewById(R.id.lv_contacts);

        helper = new ContactHelper(getActivity());
        contactsToDisplay = helper.getAllContacts();

        adapter = new ContactAdapter(getContext(), contactsToDisplay);
        lw.setAdapter(adapter);
        registerForContextMenu(lw);

        return v;
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.contex_menu_contacts, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        contact = contactsToDisplay.get(info.position);
        switch (item.getItemId()) {
            case R.id.update_contact:
                // Send the event to the host activity
                mCallback.changeToUpdate(contact.getId(), new FragmentUpdateContact());
                return true;
            case R.id.delete_contact:
                int number = helper.deleteContact(contact.getId());
                Toast.makeText(getActivity(), getResources().getString(R.string.toastContactDeleted) + number, Toast.LENGTH_SHORT).show();
                adapter.clear();
                contactsToDisplay = helper.getAllContacts();
                adapter.addAll(contactsToDisplay);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnAdd) {
            mCallback.changeToUpdate(0, new FragmentUpdateContact());
        }
    }
}
