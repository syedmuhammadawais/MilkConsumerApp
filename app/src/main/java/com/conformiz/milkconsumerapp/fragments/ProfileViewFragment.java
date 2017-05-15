package com.conformiz.milkconsumerapp.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.activities.MainActivity;
import com.conformiz.milkconsumerapp.mainfragmentmanager.MyFragmentManager;
import com.conformiz.milkconsumerapp.models.response.ProfileViewRootResponse;
import com.conformiz.milkconsumerapp.models.response.ProfileViewRootResponseData;
import com.conformiz.milkconsumerapp.network.INetworkListener;
import com.conformiz.milkconsumerapp.network.NetworkOperations;
import com.conformiz.milkconsumerapp.utils.Constants;
import com.conformiz.milkconsumerapp.utils.SharedPreferenceUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Fahad.Munir on 09-May-17.
 */

public class ProfileViewFragment extends Fragment implements INetworkListener, View.OnClickListener {

    private TextView userNameTV, emailTV, idCardTV, phoneTV, addressTV;
    ProgressDialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        userNameTV = (TextView) view.findViewById(R.id.tv_show_username);
        emailTV = (TextView) view.findViewById(R.id.tv_show_email);
        idCardTV = (TextView) view.findViewById(R.id.tv_show_cnic);
        phoneTV = (TextView) view.findViewById(R.id.tv_show_phone);
        addressTV = (TextView) view.findViewById(R.id.tv_show_address);

        view.findViewById(R.id.btn_back_profile_view).setOnClickListener(this);

        JSONObject request = new JSONObject();

        try {
            request.put("client_id", SharedPreferenceUtil.getInstance(getActivity()).getClientId()+"");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        NetworkOperations.getInstance().postData(getContext(), Constants.ACTION_GET_PROFILE_INFO,request,this, ProfileViewRootResponse.class);

        return view;
    }


    @Override
    public void onPreExecute() {
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading Profile Info....");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onPostExecute(Object result) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

        if(result!= null && result instanceof ProfileViewRootResponse){
            ProfileViewRootResponse response = (ProfileViewRootResponse)result;
            ProfileViewRootResponseData responseData = response.getData();
            userNameTV.setText(responseData.getFullname());
            emailTV.setText(responseData.getEmail());
            idCardTV.setText(responseData.getCnic());
            phoneTV.setText(responseData.getCell_no_1());
            addressTV.setText(responseData.getAddress());

        }

    }

    @Override
    public void doInBackground() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_profile_edit:
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                ProfileEditFragment profileEditFragment = new ProfileEditFragment();
                MyFragmentManager.getInstance().addFragment(profileEditFragment);
                transaction.replace(R.id.fragment_container, profileEditFragment).commit();
                break;

            case R.id.btn_back_profile_view:
                ((MainActivity) getActivity()).onBackPressed();
                break;

        }


    }
}
