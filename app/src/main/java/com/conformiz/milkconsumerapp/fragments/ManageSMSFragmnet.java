package com.conformiz.milkconsumerapp.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.models.SaveDataObjectResponse;
import com.conformiz.milkconsumerapp.models.response.ManageSmsAlertRootResponse;
import com.conformiz.milkconsumerapp.network.INetworkListener;
import com.conformiz.milkconsumerapp.network.NetworkOperations;
import com.conformiz.milkconsumerapp.utils.Constants;
import com.conformiz.milkconsumerapp.utils.SharedPreferenceUtil;
import com.conformiz.milkconsumerapp.utils.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import it.beppi.tristatetogglebutton_library.TriStateToggleButton;

/**
 * Created by Fahad.Munir on 15-Jun-17.
 */

public class ManageSMSFragmnet extends Fragment implements View.OnClickListener, INetworkListener {


    private TriStateToggleButton receiveDailyTB1, receiveNewsTB2;
    Button saveSmsBT;
    private ProgressDialog mProgressDialog;

    ManageSmsAlertRootResponse mResponse = new ManageSmsAlertRootResponse();
    private String dialogMsg = "";

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

        JSONObject request = new JSONObject();


        try {
            request.put("client_id", SharedPreferenceUtil.getInstance(getActivity()).getClientId());
            dialogMsg = "Getting SMS Alert Settings...";
            NetworkOperations.getInstance().postData(getActivity(), Constants.ACTION_POST_SMS_ALERT_SETTINGS,request,this, ManageSmsAlertRootResponse.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }


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

                if ((receiveNewsTB2.getToggleStatus()).toString().equalsIgnoreCase("off")) {
                    mResponse.getData().setAlert_new_product("0");

                } else {
                    mResponse.getData().setAlert_new_product("1");

                }


                if ((receiveDailyTB1.getToggleStatus()).toString().equalsIgnoreCase("off")) {
                    mResponse.getData().setDaily_delivery_sms("0");
                } else {
                    mResponse.getData().setDaily_delivery_sms("1");

                }

                dialogMsg = "Saving SMS alert settings...";
                mResponse.setClient_id(SharedPreferenceUtil.getInstance(getActivity()).getClientId());
                NetworkOperations.getInstance().postData(getActivity(),Constants.ACTION_POST_SAVE_SMS_ALERT_SETTINGS,mResponse,this, SaveDataObjectResponse.class);
                break;

            case R.id.btn_back_sms:
                getActivity().onBackPressed();
                break;

        }
    }

    @Override
    public void onPreExecute() {
        mProgressDialog.setMessage(""+dialogMsg);
        mProgressDialog.show();
    }

    @Override
    public void onPostExecute(Object result) {

        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }

        if (result != null) {

            if(result instanceof ManageSmsAlertRootResponse){
                mResponse = (ManageSmsAlertRootResponse) result;
                if(mResponse.getSuccess()){
                    if(mResponse.getData().getDaily_delivery_sms().equalsIgnoreCase("0")){
                        receiveDailyTB1.setToggleStatus(0,true);
                    } else{
                        receiveDailyTB1.setToggleStatus(2,true);
                    }

                    if(mResponse.getData().getAlert_new_product().equalsIgnoreCase("0")){
                        receiveNewsTB2.setToggleStatus(0,true);
                    } else{
                        receiveNewsTB2.setToggleStatus(2,true);
                    }

                } else {
                    Toast.makeText(getActivity(),"Could not get data",Toast.LENGTH_SHORT).show();
                }
            }
            if(result instanceof SaveDataObjectResponse){
                SaveDataObjectResponse response = (SaveDataObjectResponse) result;
                if(response.getSuccess()){
                    Toast.makeText(getActivity(),"SMS alert settings updated successfully",Toast.LENGTH_SHORT).show();
                    showMessageDialog("SMS settings updated successfully",getActivity());
                }else {
                    Toast.makeText(getActivity(),"SMS alert settings update failed",Toast.LENGTH_SHORT).show();

                }
            }

        }else {
            Toast.makeText(getActivity(),"Could not get data server error",Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void doInBackground() {

    }


    public void showMessageDialog(String msg, Context context) {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
        builder.setTitle("" + msg)
                .setIcon(R.drawable.ok_dialog_icon_36)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        getActivity().onBackPressed();


                    }
                });

        builder.show();
    }


}
