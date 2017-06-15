package com.conformiz.milkconsumerapp.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.network.INetworkListener;
import com.conformiz.milkconsumerapp.utils.Utility;

import it.beppi.tristatetogglebutton_library.TriStateToggleButton;

/**
 * Created by Fahad.Munir on 15-Jun-17.
 */

public class ManageSMSFragmnet extends Fragment implements View.OnClickListener, INetworkListener {


    private TriStateToggleButton receiveDailyTB1, receiveNewsTB2;
    Button saveSmsBT;
    private ProgressDialog mProgressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_sms, container, false);


        receiveDailyTB1 = (TriStateToggleButton) view.findViewById(R.id.tb_sms_1);
        receiveNewsTB2 = (TriStateToggleButton) view.findViewById(R.id.tb_sms_2);

        saveSmsBT = (Button) view.findViewById(R.id.btn_save_sms_setting);
        mProgressDialog = new ProgressDialog(getActivity());

        receiveDailyTB1.setOnClickListener(this);
        receiveNewsTB2.setOnClickListener(this);

        view.findViewById(R.id.ll_sms_1).setOnClickListener(this);
        view.findViewById(R.id.ll_sms_2).setOnClickListener(this);

        view.findViewById(R.id.btn_back_sms).setOnClickListener(this);

        saveSmsBT.setOnClickListener(this);

        Utility.getInstance().buttonEffect(saveSmsBT, R.color.app_brown_light);

        return view;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.ll_sms_1:
            case R.id.tb_sms_1:
                if ((receiveDailyTB1.getToggleStatus()).toString().equalsIgnoreCase("off")) {
                    receiveDailyTB1.setToggleStatus(2, true);
                } else {
                    receiveDailyTB1.setToggleStatus(0, true);
                }

                Log.i("sMS", "onClick: SMS1");
                break;

            case R.id.ll_sms_2:
            case R.id.tb_sms_2:
                if ((receiveNewsTB2.getToggleStatus()).toString().equalsIgnoreCase("off")) {
                    receiveNewsTB2.setToggleStatus(2, true);
                } else {
                    receiveNewsTB2.setToggleStatus(0, true);
                }

                Log.i("sMS", "onClick: SMS2");
                break;


            case R.id.btn_save_sms_setting:

                break;

            case R.id.btn_back_sms:
                getActivity().onBackPressed();
                break;

        }
    }

    @Override
    public void onPreExecute() {
        mProgressDialog.setMessage("Getting Notification Settings");
        mProgressDialog.show();
    }

    @Override
    public void onPostExecute(Object result) {

        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }

        if (result != null) {

        }
    }

    @Override
    public void doInBackground() {

    }
}
