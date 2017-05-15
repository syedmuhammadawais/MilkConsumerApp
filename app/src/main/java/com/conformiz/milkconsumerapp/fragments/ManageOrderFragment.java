package com.conformiz.milkconsumerapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.conformiz.milkconsumerapp.R;

/**
 * Created by Fahad.Munir on 12-Apr-17.
 */

public class ManageOrderFragment extends Fragment {


    public ManageOrderFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_manage_order,container,false);



        return view;
    }
}
