package com.conformiz.milkconsumerapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.activities.LoginActivity;
import com.conformiz.milkconsumerapp.activities.MainActivity;
import com.conformiz.milkconsumerapp.mainfragmentmanager.MyFragmentManager;
import com.conformiz.milkconsumerapp.utils.SharedPreferenceUtil;

/**
 * Created by Fahad.Munir on 27-Apr-17.
 */

public class SettingsFragment extends Fragment implements View.OnClickListener {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        view.findViewById(R.id.ll_row_logout).setOnClickListener(this);
        view.findViewById(R.id.ll_profile_view).setOnClickListener(this);
        view.findViewById(R.id.ll_row_change_password).setOnClickListener(this);
        view.findViewById(R.id.ll_row_manage_sms).setOnClickListener(this);



        view.findViewById(R.id.btn_back_settings).setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        switch (v.getId()) {

            case R.id.ll_row_logout:

                SharedPreferenceUtil.getInstance(getActivity()).saveKeepSignInValue(false);
                SharedPreferenceUtil.getInstance(getActivity()).saveUsername("");
                SharedPreferenceUtil.getInstance(getActivity()).saveUserPassword("");

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();
                break;

            case  R.id.ll_profile_view:
                ProfileViewFragment profileViewFragment = new ProfileViewFragment();
                MyFragmentManager.getInstance().addFragment(profileViewFragment);
                transaction.replace(R.id.fragment_container,profileViewFragment).commit();

                break;

            case R.id.ll_row_change_password:
                ChangePasswordFragment changePasswordFragment = new ChangePasswordFragment();
                MyFragmentManager.getInstance().addFragment(changePasswordFragment);
                transaction.replace(R.id.fragment_container,changePasswordFragment).commit();
                break;


            case R.id.ll_row_manage_sms:
                ManageSMSFragmnet manageSMSFragmnet = new ManageSMSFragmnet();
                MyFragmentManager.getInstance().addFragment(manageSMSFragmnet);
                transaction.replace(R.id.fragment_container,manageSMSFragmnet).commit();
                break;



            case R.id.btn_back_settings:
                ((MainActivity)getActivity()).onBackPressed();
                break;






        }

    }
}
