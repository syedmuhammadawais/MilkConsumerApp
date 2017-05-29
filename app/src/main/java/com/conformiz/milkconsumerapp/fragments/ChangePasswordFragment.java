package com.conformiz.milkconsumerapp.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.models.response.SaveDataArrayResponse;
import com.conformiz.milkconsumerapp.network.INetworkListener;
import com.conformiz.milkconsumerapp.network.NetworkOperations;
import com.conformiz.milkconsumerapp.utils.Constants;
import com.conformiz.milkconsumerapp.utils.SharedPreferenceUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Fahad.Munir on 19-May-17.
 */

public class ChangePasswordFragment extends Fragment implements INetworkListener<SaveDataArrayResponse>, View.OnClickListener {


    EditText oldPasswordET;
    EditText newPasswordET;

    ProgressDialog dialog;


    boolean isValidate = false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_password,container,false);

        oldPasswordET = (EditText) view.findViewById(R.id.et_change_password_old);
        newPasswordET = (EditText) view.findViewById(R.id.et_change_password_new);

        view.findViewById(R.id.btn_back_change_password).setOnClickListener(this);
        view.findViewById(R.id.btn_change_password).setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_back_change_password:
                getActivity().onBackPressed();
                break;


            case  R.id.btn_change_password:

                if(checkValidation()){

                    JSONObject request = new JSONObject();
                    try {
                        request.put("client_id", SharedPreferenceUtil.getInstance(getActivity()).getClientId()+"");
                        request.put("oldPassword",oldPasswordET.getText().toString());
                        request.put("newPassword",newPasswordET.getText().toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    NetworkOperations.getInstance().postData(getActivity(), Constants.ACTION_POST_CHANGE_CUSTOMER_PASSWORD,request,this, SaveDataArrayResponse.class);

                }

                break;
        }
    }



    @Override
    public void onPreExecute() {
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Changing Password...");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onPostExecute(SaveDataArrayResponse result) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

        if(result != null && result.getSuccess()){
            Toast.makeText(getActivity(),result.getMessage()+"",Toast.LENGTH_SHORT).show();
            getActivity().onBackPressed();
        }else {
            Toast.makeText(getActivity(),result.getMessage()+"",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void doInBackground() {

    }




    public boolean checkValidation(){
        isValidate = false;

        if(TextUtils.isEmpty(oldPasswordET.getText().toString())){
            oldPasswordET.setError("Please Enter Old Password");
        } else {
            isValidate = true;
        }

        if(TextUtils.isEmpty(newPasswordET.getText().toString())){
            newPasswordET.setError("Please Enter New Password");
        }else {
            isValidate = true;
        }

        return isValidate;
    }
}
