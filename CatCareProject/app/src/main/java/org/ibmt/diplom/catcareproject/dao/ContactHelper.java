package org.ibmt.diplom.catcareproject.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import org.ibmt.diplom.catcareproject.model.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 04.03.2017.
 */

public class ContactHelper extends DbHelper {

    public ContactHelper(Context context) {
        super(context);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
    // Adding new contact
    public long addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CONTACTS_COLUMN_NAME, contact.getName());
        values.put(CONTACTS_COLUMN_DESCRIPTION, contact.getDiscription());
        values.put(CONTACTS_COLUMN_NUMBER, contact.getNumber());
        // Inserting Row
        long newRowId = db.insert(CONTACTS_TABLE_NAME, null, values);
        return newRowId;
    }

    // Updating single contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CONTACTS_COLUMN_NAME, contact.getName());
        values.put(CONTACTS_COLUMN_DESCRIPTION, contact.getDiscription());
        values.put(CONTACTS_COLUMN_NUMBER, contact.getNumber());
        // updating row
        long id = contact.getId();
        String selection = CONTACTS_COLUMN_ID + "=?";
        String[] selectionArg = new String[]{String.valueOf(id)};
        int numberOfRowsAffected = db.update(CONTACTS_TABLE_NAME, values, selection, selectionArg);
        return numberOfRowsAffected;
    }

    // Deleting single contact
    public int deleteContact(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int numAffected = db.delete(CONTACTS_TABLE_NAME, CONTACTS_COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        return numAffected;
    }

    // Getting single contact
    public Contact getContact(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Contact contact = null;
        String[] fields = new String[]{
                CONTACTS_COLUMN_ID, CONTACTS_COLUMN_NAME, CONTACTS_COLUMN_DESCRIPTION, CONTACTS_COLUMN_NUMBER};
        String selection = CONTACTS_COLUMN_ID + "=?";
        String[] selectionArg = new String[]{String.valueOf(id)};
        Cursor c = db.query(CONTACTS_TABLE_NAME, fields, selection, selectionArg, null, null, null);
        if (c != null) {
            c.moveToFirst();
            if ((!c.isAfterLast())) {
                contact = createContactFromDB(c);
            }
            c.close();
        }
        return contact;
    }

    // Getting All contacts
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        SQLiteDatabase db = this.getReadableDatabase();
        String orderBy = CONTACTS_COLUMN_NAME;
        Cursor c = db.query(CONTACTS_TABLE_NAME, null, null, null, null, null, orderBy);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Contact contact = createContactFromDB(c);
                // Adding contact to list
                contactList.add(contact);
            } while (c.moveToNext());
        }
        c.close();
        // return contacts list
        return contactList;
    }

    @NonNull
    private Contact createContactFromDB(Cursor c) {
        return new Contact(
                c.getLong(c.getColumnIndex(CONTACTS_COLUMN_ID)),
                c.getString(c.getColumnIndex(CONTACTS_COLUMN_NAME)),
                c.getString(c.getColumnIndex(CONTACTS_COLUMN_DESCRIPTION)),
                c.getString(c.getColumnIndex(CONTACTS_COLUMN_NUMBER)));
    }

    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + CONTACTS_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
