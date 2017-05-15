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
import com.conformiz.milkconsumerapp.models.response.ComplaintsReasonsRootResponse;
import com.conformiz.milkconsumerapp.models.response.ComplaintsReasonsRootResponseData;
import com.conformiz.milkconsumerapp.models.response.SaveDataResponse;
import com.conformiz.milkconsumerapp.network.INetworkListener;
import com.conformiz.milkconsumerapp.network.NetworkOperations;
import com.conformiz.milkconsumerapp.utils.Constants;
import com.conformiz.milkconsumerapp.utils.SharedPreferenceUtil;
import com.conformiz.milkconsumerapp.utils.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Fahad.Munir on 18-Apr-17.
 */

public class ComplaintsFragment extends Fragment implements INetworkListener,
        View.OnFocusChangeListener, View.OnClickListener, Spinner.OnItemSelectedListener {

    String TAG = "ComplaintsFragment ";

    Spinner mComplaintSpinner;
    private ProgressDialog dialog;
    ArrayAdapter spinnerAdapter;
    EditText complaintTextArea;
    ArrayList<ComplaintsReasonsRootResponseData> mComplaintsData = new ArrayList<>();

    private String selectedReasonId = "0";
    boolean isValidate = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_complaints, container, false);

        mComplaintSpinner = (Spinner) view.findViewById(R.id.hintSpinner);
        spinnerAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, mComplaintsData);
        mComplaintSpinner.setAdapter(spinnerAdapter);
        complaintTextArea = (EditText) view.findViewById(R.id.et_complaint_text);
        complaintTextArea.setOnFocusChangeListener(this);

        view.findViewById(R.id.btn_submit_complaint).setOnClickListener(this);
        view.findViewById(R.id.btn_back_complaints).setOnClickListener(this);
        //((TextView)getActivity().findViewById(R.id.tv_screen_header)).setText("Complaints");
        mComplaintSpinner.setOnItemSelectedListener(this);
        dialog = new ProgressDialog(getActivity());
        NetworkOperations.getInstance().getComplaintsReasons(getActivity(), this);
        return view;
    }


    @Override
    public void onPreExecute() {
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading Plan Details....");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onPostExecute(Object result) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

        if (result != null) {
            if (result instanceof ComplaintsReasonsRootResponse && ((ComplaintsReasonsRootResponse) result).getSuccess()) {
                ComplaintsReasonsRootResponse response = (ComplaintsReasonsRootResponse) result;
                if (response.getData() != null && response.getData().size() > 0) {
                    mComplaintsData.clear();
                    mComplaintsData.addAll(response.getData());
                    spinnerAdapter.notifyDataSetChanged();
                }
            }

            if (result instanceof SaveDataResponse) {
                SaveDataResponse response = (SaveDataResponse) result;
                if (response.getSuccess()) {
                    Utility.getInstance().onSuccessSubmitDialog("Complaint Sent Successfully", getActivity());
                } else {
                    Toast.makeText(getActivity(), "" + response.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }


        } else {
            Toast.makeText(getActivity(), "Could not found data", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void doInBackground() {

    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        switch (v.getId()) {
            case R.id.et_complaint_text:
                if (!hasFocus) {
                    isValidate = complaintTextArea.getText().toString().trim().length() > 0;
                    if (!isValidate) {
                        complaintTextArea.setError("Please Write Some Detail");
                    }
                }
                break;
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_back_complaints:
                ((MainActivity) getActivity()).onBackPressed();
                break;

            case R.id.btn_submit_complaint:
                if (isValidated()) {
                    JSONObject requestJson = new JSONObject();
                    try {
                        requestJson.put("complain_type_id", selectedReasonId);
                        requestJson.put("client_id", SharedPreferenceUtil.getInstance(getActivity()).getClientId());
                        requestJson.put("query_text", complaintTextArea.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    NetworkOperations.getInstance().postData(getActivity(), Constants.ACTION_POST_SAVE_COMPLAINT, requestJson, this, SaveDataResponse.class);
                    Log.i(TAG, "onClick: Everything true");
                } else {
                    Toast.makeText(getActivity(), "Please Update Fields With Error Message", Toast.LENGTH_SHORT).show();
                }
        }
    }

    public boolean isValidated() {
        if (TextUtils.isEmpty(complaintTextArea.getText().toString().trim())) {
            complaintTextArea.setError("Please Write Some Detail");
            isValidate = false;
        } else {
            isValidate = true;
        }
        return isValidate;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedReasonId = mComplaintsData.get(position).getComplain_type_id();
        Log.i(TAG, "onItemClick: selected reason id " + position);
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
