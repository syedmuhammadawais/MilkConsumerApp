package com.conformiz.milkconsumerapp.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.activities.MainActivity;
import com.conformiz.milkconsumerapp.network.INetworkListener;

/**
 * Created by Fahad.Munir on 09-May-17.
 */

public class ProfileEditFragment extends Fragment implements INetworkListener, View.OnClickListener {


    EditText usernameET, idCardET, emailET, phoneET, addressET;

    ProgressDialog dialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_edit, container, false);
        usernameET = (EditText) view.findViewById(R.id.et_profile_name);
        idCardET = (EditText) view.findViewById(R.id.et_profile_cnic);
        emailET = (EditText) view.findViewById(R.id.et_profile_email);
        phoneET = (EditText) view.findViewById(R.id.et_profile_phone);
        addressET = (EditText) view.findViewById(R.id.et_profile_address);



        return view;
    }

    @Override
    public void onPreExecute() {
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Updating Profile Info....");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onPostExecute(Object result) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void doInBackground() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back_profile_edit:

                ((MainActivity)getActivity()).onBackPressed();
                break;
        }
    }
}
