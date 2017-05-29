package com.conformiz.milkconsumerapp.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.activities.MainActivity;
import com.conformiz.milkconsumerapp.models.response.ProfileViewRootResponse;
import com.conformiz.milkconsumerapp.models.response.ProfileViewRootResponseData;
import com.conformiz.milkconsumerapp.models.response.ZoneListRootResponse;
import com.conformiz.milkconsumerapp.models.response.ZoneListRootResponseData;
import com.conformiz.milkconsumerapp.network.INetworkListener;
import com.conformiz.milkconsumerapp.network.NetworkOperations;
import com.conformiz.milkconsumerapp.utils.Constants;
import com.conformiz.milkconsumerapp.utils.SharedPreferenceUtil;
import com.conformiz.milkconsumerapp.utils.Utility;

import java.util.ArrayList;

/**
 * Created by Fahad.Munir on 09-May-17.
 */

public class ProfileEditFragment extends Fragment implements INetworkListener, View.OnClickListener, Spinner.OnItemSelectedListener {


    EditText usernameET, idCardET, emailET, phoneET, addressET;

    ProgressDialog dialog;
    Spinner zoneListSP;

    int selectedZoneId = 0;

    boolean isValidate = false;


    ArrayAdapter spinnerAdapter;
    ArrayList<ZoneListRootResponseData> mZoneListData = new ArrayList<>();
    ProfileViewRootResponseData mData = new ProfileViewRootResponseData();



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_edit, container, false);
        usernameET = (EditText) view.findViewById(R.id.et_profile_name);
        idCardET = (EditText) view.findViewById(R.id.et_profile_cnic);
        emailET = (EditText) view.findViewById(R.id.et_profile_email);
        phoneET = (EditText) view.findViewById(R.id.et_profile_phone);
        addressET = (EditText) view.findViewById(R.id.et_profile_address);

        view.findViewById(R.id.btn_update_profile).setOnClickListener(this);
        view.findViewById(R.id.btn_back_profile_edit).setOnClickListener(this);

        usernameET.setText(mData.getFullname());
        idCardET.setText(mData.getCnic());
        emailET.setText(mData.getEmail());
        phoneET.setText(mData.getCell_no_1());
        phoneET.setEnabled(false);
        phoneET.setFocusableInTouchMode(false);
        addressET.setText(mData.getAddress());

        zoneListSP = (Spinner) view.findViewById(R.id.sp_zone_profile_edit);
        spinnerAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, mZoneListData);
        zoneListSP.setAdapter(spinnerAdapter);
        zoneListSP.setOnItemSelectedListener(this);
        dialog = new ProgressDialog(getActivity());

        NetworkOperations.getInstance().getZoneList(getActivity(), new INetworkListener<ZoneListRootResponse>() {
            @Override
            public void onPreExecute() {
                dialog.setMessage("Getting Zones List....");
                dialog.setCancelable(false);
                dialog.show();
            }

            @Override
            public void onPostExecute(ZoneListRootResponse result) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }

                if (result != null) {
                    if (result.getSuccess() && result.getData() != null && result.getData().size() > 0) {
                        mZoneListData.clear();
                        mZoneListData.addAll(result.getData());
                        spinnerAdapter.notifyDataSetChanged();

                        for (int i = 0; i < mZoneListData.size(); i++) {
                            if (mZoneListData.get(i).getZone_id().equalsIgnoreCase(mData.getZone_id())) {
                                zoneListSP.setSelection(i);
                                spinnerAdapter.notifyDataSetChanged();
                                break;

                            }
                        }
                    }
                }
            }

            @Override
            public void doInBackground() {

            }
        });

        return view;
    }

    @Override
    public void onPreExecute() {
        dialog.setMessage("Updating Profile Info....");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onPostExecute(Object result) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

        if (result != null && result instanceof ProfileViewRootResponse) {
            if (((ProfileViewRootResponse) result).getSuccess()) {

                Toast.makeText(getActivity(), "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
            } else {
                Toast.makeText(getActivity(), "Unable to Update Profile", Toast.LENGTH_SHORT).show();

            }
        } else {
            Toast.makeText(getActivity(), "Unable to Update Profile", Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public void doInBackground() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back_profile_edit:

                ((MainActivity) getActivity()).onBackPressed();
                break;

            case R.id.btn_update_profile:

                if (checkValidation()) {
                    mData.setClient_id(SharedPreferenceUtil.getInstance(getActivity()).getClientId());
                    Log.i("username", "onClick: " + usernameET.getText().toString());
                    mData.setFullname(usernameET.getText().toString());
                    mData.setAddress(addressET.getText().toString());
                    mData.setCell_no_1(phoneET.getText().toString());
                    mData.setCnic(idCardET.getText().toString());
                    mData.setEmail(emailET.getText().toString());
                    mData.setZone_id(selectedZoneId + "");

                    NetworkOperations.getInstance().postData(getActivity(), Constants.ACTION_POST_UPDATE_CUSTOMER_PROFILE, mData, this, ProfileViewRootResponse.class);

                }
                break;

        }
    }


    public void setData(ProfileViewRootResponseData data) {
        mData = data;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        selectedZoneId = Integer.parseInt(mZoneListData.get(position).getZone_id());
        Log.i(" zone ", "onItemSelected: " + position + " id " + id + " Zone ID" + selectedZoneId);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public boolean checkValidation() {
        isValidate = false;

        if (TextUtils.isEmpty(usernameET.getText().toString())) {
            usernameET.setError("Please Enter Username");
        } else {
            isValidate = true;
        }

        if (TextUtils.isEmpty(idCardET.getText().toString())) {
            idCardET.setError("Please Enter CNIC Number");
        } else {
            isValidate = true;
        }

        if (TextUtils.isEmpty(emailET.getText().toString())) {
            emailET.setError("Please Enter Email Address");
        } else if (!(Utility.getInstance().isValidEmail(emailET.getText().toString()))) {
            isValidate = false;
            emailET.setError("Please Enter a valid email address format");
        } else {
            isValidate = true;
        }

        if (TextUtils.isEmpty(addressET.getText().toString())) {
            addressET.setError("Please Enter Address");
        } else {
            isValidate = true;
        }


        return isValidate;
    }
}
