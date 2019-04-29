package org.ibmt.diplom.catcareproject.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.anna.catcareproject.R;

import org.ibmt.diplom.catcareproject.model.Contact;

import java.util.List;

/**
 * Created by Anna on 04.03.2017.
 */

public class ContactAdapter extends ArrayAdapter<Contact> {


    Context context;
    List<Contact> array;
    TextView number, name;
    Button bDial, bMessage;
    Contact contact;

    public ContactAdapter(Context context, List<Contact> array) {
        super(context, android.R.layout.simple_list_item_1, array);
        this.context = context;
        this.array = array;
    }
    @Override
    public int getCount() {
        return array.size();
    }


    //for the first line of the spinner
    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.adapter_contact_row, parent, false);
        name = (TextView) layout.findViewById(R.id.contact_name);
        number = (TextView) layout.findViewById(R.id.contact_number);
        bDial = (Button) layout.findViewById(R.id.button_call) ;
        bMessage = (Button) layout.findViewById(R.id.button_message) ;
        // Cache row position inside the button using `setTag`
        bDial.setTag(position);
        bMessage.setTag(position);

        contact = array.get(position);
        name.setText(contact.getName());
        number.setText(contact.getNumber());

        bDial.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int position = (Integer) view.getTag();
                // Access the row position here to get the correct data item
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+getPhoneNumber(position)));
                context.startActivity(callIntent);
            }
        });

        bMessage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                try {
                    int position = (Integer) view.getTag();
                    // Access the row position here to get the correct data item
                    Uri uri = Uri.parse("smsto:"+getPhoneNumber(position));
                    // No permisison needed
                    Intent smsIntent = new Intent(Intent.ACTION_SENDTO, uri);
                    context.startActivity(smsIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



        return layout;
    }

//    public void dialContact(View v) {
//        Intent callIntent = new Intent(Intent.ACTION_DIAL);
//        callIntent.setData(Uri.parse("tel:"+getPhoneNumber()));
//        context.startActivity(callIntent);
//    }
//
//    public void messageContact(View v) {
//        try {
//            Uri uri = Uri.parse("smsto:"+getPhoneNumber());
//            // No permisison needed
//            Intent smsIntent = new Intent(Intent.ACTION_SENDTO, uri);
//            context.startActivity(smsIntent);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @NonNull
    private String getPhoneNumber(Integer position){
        return getItem(position).getNumber().toString().trim();
    }






}
