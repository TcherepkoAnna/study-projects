package org.ibmt.diplom.catcareproject.control;

import android.support.v4.app.Fragment;

/**
 * Container Activity must implement this interface
 * Created by Anna on 19.12.2016.
 */
public interface FragmentChangeListener {
    public void changeToUpdate(long newRowId, Fragment someFragment);
}
