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

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.network.INetworkListener;

import it.beppi.tristatetogglebutton_library.TriStateToggleButton;

/**
 * Created by Fahad.Munir on 27-Apr-17.
 */

public class RecoverPasswordFragment extends Fragment implements
        View.OnClickListener,
        TriStateToggleButton.OnToggleChanged,
        View.OnFocusChangeListener, INetworkListener {

    private boolean isValidate = false;
    private ProgressDialog mProgressDialog;

    private EditText emailOrPhoneET;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recover_password, container, false);

        emailOrPhoneET = (EditText) view.findViewById(R.id.et_email_forget_pass);
        emailOrPhoneET.setOnFocusChangeListener(this);

        ((TriStateToggleButton)view.findViewById(R.id.tb_email_or_password)).setOnToggleChanged(this);
        return view;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_recover_password:

                break;
        }

    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        switch (v.getId()) {

            case R.id.et_email_forget_pass:
                isValidate = !hasFocus && !TextUtils.isEmpty(emailOrPhoneET.getText().toString());
                break;
        }
    }

    @Override
    public void onPreExecute() {
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Recovering Password...");
        mProgressDialog.show();

    }

    @Override
    public void onPostExecute(Object result) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }


    }

    @Override
    public void doInBackground() {

    }

    @Override
    public void onToggle(TriStateToggleButton.ToggleStatus toggleStatus, boolean b, int i) {

        switch (i){
            case 2:
                emailOrPhoneET.setText("");
                emailOrPhoneET.setHint("Phone: 03xx1234567");
                break;

            case 0:
                emailOrPhoneET.setText("");
                emailOrPhoneET.setHint("Enter Email Address");
                break;

        }
    }
}
