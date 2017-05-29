package com.conformiz.milkconsumerapp.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.adapters.ViewComplaintsListAdapter;
import com.conformiz.milkconsumerapp.callbacks.OnItemClick;
import com.conformiz.milkconsumerapp.mainfragmentmanager.MyFragmentManager;
import com.conformiz.milkconsumerapp.models.response.SaveDataArrayResponse;
import com.conformiz.milkconsumerapp.models.response.ViewCustomerComplaintsRootResponse;
import com.conformiz.milkconsumerapp.models.response.ViewCustomerComplaintsRootResponseData;
import com.conformiz.milkconsumerapp.network.INetworkListener;
import com.conformiz.milkconsumerapp.network.NetworkOperations;
import com.conformiz.milkconsumerapp.utils.Constants;
import com.conformiz.milkconsumerapp.utils.SharedPreferenceUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Fahad.Munir on 18-May-17.
 */

public class ComplaintsViewFragment extends Fragment implements INetworkListener<ViewCustomerComplaintsRootResponse>, OnItemClick, View.OnClickListener {


    String TAG = "Customer Fragment";

    private ArrayList<ViewCustomerComplaintsRootResponseData> mData = new ArrayList<>();

    ViewComplaintsListAdapter  complaintsListAdapter;

    RecyclerView complaintsRV;
    ProgressDialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_complaints_view,container,false);

        complaintsRV = (RecyclerView) view.findViewById(R.id.rv_view_complaints);
        complaintsListAdapter = new ViewComplaintsListAdapter(mData,this);

        view.findViewById(R.id.btn_back_view_complaints).setOnClickListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        complaintsRV.setLayoutManager(linearLayoutManager);
        complaintsRV.setAdapter(complaintsListAdapter);

        JSONObject request = new JSONObject();
        try {
            request.put("client_id", SharedPreferenceUtil.getInstance(getActivity()).getClientId());
            NetworkOperations.getInstance().postData(getActivity(), Constants.ACTION_GET_CUSTOMER_COMPLAINTS, request,this, ViewCustomerComplaintsRootResponse.class);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onPreExecute() {
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading Complaints....");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onPostExecute(ViewCustomerComplaintsRootResponse result) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

        if(result !=null && result.getSuccess()){
            mData.clear();
            mData.addAll(result.getData());
            complaintsListAdapter.addDataToPlansList(mData);
        }

    }

    @Override
    public void doInBackground() {

    }

    @Override
    public void onClick(int pPosition, int id) {
        Log.i(TAG, "onClick: ");

        ComplaintDetailFragment complaintDetailFragment = new ComplaintDetailFragment();
        complaintDetailFragment.setData(mData.get(pPosition));
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        MyFragmentManager.getInstance().addFragment(complaintDetailFragment);
        transaction.replace(R.id.fragment_container,complaintDetailFragment).commit();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back_view_complaints:
                getActivity().onBackPressed();
                break;
        }
    }
}
