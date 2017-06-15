package com.conformiz.milkconsumerapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.mainfragmentmanager.MyFragmentManager;

/**
 * Created by Fahad.Munir on 13-Jun-17.
 */

public class HelpFragment extends Fragment implements View.OnClickListener {




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help,container,false);


        view.findViewById(R.id.btn_back_help).setOnClickListener(this);
        view.findViewById(R.id.btn_help_how_to_pay).setOnClickListener(this);
        view.findViewById(R.id.btn_help_faqs).setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        switch (v.getId()){

            case R.id.btn_help_how_to_pay:
                HowToPayFragment  howToPayFragment = new HowToPayFragment();
                MyFragmentManager.getInstance().addFragment(howToPayFragment);
                transaction.replace(R.id.fragment_container,howToPayFragment).commit();
                break;

            case R.id.btn_help_faqs:
                FaqFragment faqFragment = new FaqFragment();
                MyFragmentManager.getInstance().addFragment(faqFragment);
                transaction.replace(R.id.fragment_container,faqFragment).commit();
                break;

            case R.id.btn_back_help:
            getActivity().onBackPressed();
                break;
        }
    }
}
