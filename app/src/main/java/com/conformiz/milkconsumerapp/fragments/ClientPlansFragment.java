package com.conformiz.milkconsumerapp.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.activities.MainActivity;
import com.conformiz.milkconsumerapp.adapters.ClientPlanListAdapter;
import com.conformiz.milkconsumerapp.callbacks.OnItemClick;
import com.conformiz.milkconsumerapp.mainfragmentmanager.MyFragmentManager;
import com.conformiz.milkconsumerapp.models.response.ClientPlansRootResponse;
import com.conformiz.milkconsumerapp.models.response.ClientPlansRootResponseData;
import com.conformiz.milkconsumerapp.network.INetworkListener;
import com.conformiz.milkconsumerapp.network.NetworkOperations;
import com.conformiz.milkconsumerapp.utils.Constants;
import com.conformiz.milkconsumerapp.utils.SharedPreferenceUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Fahad.Munir on 17-Apr-17.
 */

public class ClientPlansFragment extends Fragment implements OnItemClick, View.OnClickListener, INetworkListener {

    RecyclerView productsListRV;
    ClientPlanListAdapter mClientPlanListAdapter;
    ProgressDialog dialog;
    private ArrayList<ClientPlansRootResponseData> mData = new ArrayList<>();

    int selectedPlanType = 0;
    public String TAG = "Client Plans ";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_client_plans, container, false);

        // ((TextView)getActivity().findViewById(R.id.tv_screen_header)).setText("Plans");

        view.findViewById(R.id.btn_back_plans).setOnClickListener(this);
        productsListRV = (RecyclerView) view.findViewById(R.id.rv_product_plans);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        mClientPlanListAdapter = new ClientPlanListAdapter(mData, this);
        productsListRV.setLayoutManager(linearLayoutManager);
        productsListRV.setAdapter(mClientPlanListAdapter);

        view.findViewById(R.id.fab_btn_add_plan).setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("client_id", SharedPreferenceUtil.getInstance(getActivity()).getClientId() + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        NetworkOperations.getInstance().postData(getActivity(), Constants.ACTION_GET_CLIENT_PLANS, jsonObject, this, ClientPlansRootResponse.class);
    }

    @Override
    public void onClick(int pPosition, int id) {

        getActivity().getIntent().putExtra("selected_plan", Constants.UPDATE_WEEKLY_PLAN);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        ProductScheduleFragment productScheduleFragment = new ProductScheduleFragment();
        productScheduleFragment.setIsUpdate(1); // 1 for update 0 for new
        productScheduleFragment.setProductId(mData.get(pPosition).getProduct_id());

        MyFragmentManager.getInstance().addFragment(productScheduleFragment);
        transaction.replace(R.id.fragment_container, productScheduleFragment);
        transaction.commit();
    }

    @Override
    public void onPreExecute() {
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading Plans....");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onPostExecute(Object result) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }


        if (result != null) {
            if (result instanceof ClientPlansRootResponse && ((ClientPlansRootResponse) result).getSuccess()) {
                ClientPlansRootResponse productsRootResponse = (ClientPlansRootResponse) result;
                if (productsRootResponse.getData() != null && productsRootResponse.getData().size() > 0) {
                    mData.clear();
                    mData.addAll(productsRootResponse.getData());
                    mClientPlanListAdapter.addDataToPlansList(mData);
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
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.fab_btn_add_plan:

                showPlanSelectionDialog();
                break;

            case R.id.btn_back_plans:
                ((MainActivity) getActivity()).onBackPressed();
                break;
        }

    }


    void showPlanSelectionDialog() {

        AlertDialog.Builder planSelectionDialog = new AlertDialog.Builder(getActivity());
        planSelectionDialog.setIcon(R.drawable.product_info);
        planSelectionDialog.setTitle("Select a Group Name");
        final String[] planTypes = {"Add Weekly Plan", "Add Special Order"};
        planSelectionDialog.setSingleChoiceItems(planTypes, selectedPlanType, new DialogInterface
                .OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {

                selectedPlanType = item;
                Log.i(TAG, "dialog: " + item);
                Toast.makeText(getActivity(),
                        "Group Name = " + planTypes[item], Toast.LENGTH_SHORT).show();
                //dialog.dismiss();// dismiss the alertBox after chose option

            }
        }).setPositiveButton("Next", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG, "onClick: onnext click    " + which);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                ProductsFragment productsFragment = new ProductsFragment();

                switch (selectedPlanType) {
                    case 0:
                        Log.i(TAG, "onClick: weekly");
                        MyFragmentManager.getInstance().addFragment(productsFragment);
                        fragmentTransaction.replace(R.id.fragment_container, productsFragment);
                        getActivity().getIntent().putExtra("selected_plan", Constants.ADD_NEW_WEEKLY_PLAN + "");
                        fragmentTransaction.commit();


                        break;

                    case 1:
                        Log.i(TAG, "onClick: special");
                        MyFragmentManager.getInstance().addFragment(productsFragment);
                        fragmentTransaction.replace(R.id.fragment_container, productsFragment);
                        getActivity().getIntent().putExtra("selected_plan", Constants.ADD_SPECIAL_ORDER + "");
                        fragmentTransaction.commit();

                        break;
                }


            }
        });


        AlertDialog alert = planSelectionDialog.create();
        alert.show();


///// grpname is a array where data is stored...


    }
}
