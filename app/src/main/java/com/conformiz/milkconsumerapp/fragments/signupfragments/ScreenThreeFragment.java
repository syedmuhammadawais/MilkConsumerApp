package com.conformiz.milkconsumerapp.fragments.signupfragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.models.SaveDataObjectResponse;
import com.conformiz.milkconsumerapp.network.INetworkListener;
import com.conformiz.milkconsumerapp.network.NetworkOperations;
import com.conformiz.milkconsumerapp.utils.Constants;
import com.conformiz.milkconsumerapp.utils.SharedPreferenceUtil;
import com.conformiz.milkconsumerapp.utils.Utility;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Fahad.Munir on 12-Apr-17.
 */

public class ScreenThreeFragment extends Fragment implements View.OnClickListener, INetworkListener {

    public boolean isValidate = false;
    private String mPhoneNumber = "";
    private EditText verificationCode;

    private ProgressDialog mProgressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_screen_three,container,false);
        verificationCode = (EditText)  view.findViewById(R.id.et_enter_code_sign_up);


        String msg = "We have sent SMS on +92"+mPhoneNumber+". Please enter the verification code below to complete the sign-up process.";
        ((TextView)view.findViewById(R.id.tv_fs3)).setText(""+ msg);

        Utility.getInstance().buttonEffect(((Button)view.findViewById(R.id.btn_resend_code)), R.color.app_brown_light);
        view.findViewById(R.id.btn_resend_code).setOnClickListener(this);
        return view;
    }

    public String getAuthCode(){
        return verificationCode.getText().toString();
    }

    public boolean isValidationTrue(){
        if(TextUtils.isEmpty(verificationCode.getText().toString())){
            verificationCode.setError("Please Enter Verification Code");
            Toast.makeText(getActivity(),"Please Enter Verification Code",Toast.LENGTH_SHORT).show();
        return false;
        }
        return true;
    }


    public String setPhoneNumber(String phone){
        mPhoneNumber = phone;
        return mPhoneNumber;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_resend_code:

                JSONObject request = new JSONObject();
                try {
                    request.put("client_id", SharedPreferenceUtil.getInstance(getActivity()).getClientId());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                NetworkOperations.getInstance().postData(getActivity(), Constants.ACTION_POST_RESEND_CODE,request,this, SaveDataObjectResponse.class);

                break;
        }
    }

    @Override
    public void onPreExecute() {
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Resending Verification Code...");
        mProgressDialog.show();
    }

    @Override
    public void onPostExecute(Object result) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }

        if (result != null) {
            if(result instanceof SaveDataObjectResponse) {
                SaveDataObjectResponse response = (SaveDataObjectResponse) result;
                if (response.getSuccess()) {
                    showMessageDialog("" + response.getMessage());
                } else {
                    Toast.makeText(getActivity(), "Server Error", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void doInBackground() {

    }

    public void showMessageDialog(String msg) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
        builder.setTitle(msg).setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                dialog.cancel();
            }
        });

        android.app.AlertDialog alert = builder.create();
        alert.show();
    }

}
