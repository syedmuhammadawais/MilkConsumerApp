package com.conformiz.milkconsumerapp.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.activities.MainActivity;
import com.conformiz.milkconsumerapp.adapters.ProductsListAdapter;
import com.conformiz.milkconsumerapp.callbacks.OnItemClick;
import com.conformiz.milkconsumerapp.mainfragmentmanager.MyFragmentManager;
import com.conformiz.milkconsumerapp.models.response.AllProductsRootResponse;
import com.conformiz.milkconsumerapp.models.response.AllProductsRootResponseData;
import com.conformiz.milkconsumerapp.network.INetworkListener;
import com.conformiz.milkconsumerapp.network.NetworkOperations;
import com.conformiz.milkconsumerapp.utils.Constants;
import com.conformiz.milkconsumerapp.utils.SharedPreferenceUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Fahad.Munir on 13-Apr-17.
 */

public class ProductsFragment extends Fragment implements INetworkListener, OnItemClick, Constants, View.OnClickListener {

    private String TAG = "ProductsFragment ";

    ArrayList<AllProductsRootResponseData> productsDataList = new ArrayList<>();
    RecyclerView productsRV;
    ProgressDialog dialog;
    ProductsListAdapter mProductsListAdapter;

    public ProductsFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products_list, container, false);

        //  ((TextView) getActivity().findViewById(R.id.tv_screen_header)).setText("Select Product");

        view.findViewById(R.id.tv_all_plans).setOnClickListener(this);
        view.findViewById(R.id.btn_back_products).setOnClickListener(this);


        productsRV = (RecyclerView) view.findViewById(R.id.rv_products_list);
        mProductsListAdapter = new ProductsListAdapter(productsDataList, this);


        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        productsRV.setLayoutManager(mLayoutManager);
        productsRV.setItemAnimator(new DefaultItemAnimator());
        productsRV.setAdapter(mProductsListAdapter);

        JSONObject request = new JSONObject();
        try {
            request.put("client_id", SharedPreferenceUtil.getInstance(getActivity()).getClientId() + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        NetworkOperations.getInstance().postData(getActivity(), Constants.ACTION_POST_ALL_PRODUCT, request, this, AllProductsRootResponse.class);
        //  NetworkOperations.getInstance().getProductsList(getActivity(), this);
        return view;
    }

    @Override
    public void onPreExecute() {
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading Products....");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onPostExecute(Object result) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

        if (result != null) {
            if (result instanceof AllProductsRootResponse && ((AllProductsRootResponse) result).getSuccess()) {
                AllProductsRootResponse productsRootResponse = (AllProductsRootResponse) result;
                productsDataList.clear();
                productsDataList.addAll(productsRootResponse.getData());
                mProductsListAdapter.addProductsToList(productsDataList);
            } else {
                Toast.makeText(getActivity(), "Could not get products list ", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(getActivity(), "Could not found data of null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void doInBackground() {

    }

    @Override
    public void onClick(int pPosition, int id) {
        Log.i(TAG, "onClick: " + productsDataList.get(pPosition).getProduct_name());
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();


        switch (id) {
            case ON_CLICK_PRODUCT:
                String clientId = SharedPreferenceUtil.getInstance(getActivity()).getClientId();
//                String productId = productsDataList.get(pPosition).getProduct_id();
//                String productUnit = productsDataList.get(pPosition).getUnit();
//                String productName = productsDataList.get(pPosition).getProduct_name();
//                String productPrice = productsDataList.get(pPosition).getPrice();

                    if(productsDataList.get(pPosition).getIs_selected().equalsIgnoreCase("0")) {
                        ProductScheduleFragment productScheduleFragment = new ProductScheduleFragment();
                        productScheduleFragment.setSelectedProductData(productsDataList.get(pPosition));
//                    productScheduleFragment.setProductId(productId);
//                    productScheduleFragment.setProductUnit(productUnit);
//                    productScheduleFragment.setProductName(productName);
//                    productScheduleFragment.setProductPrice(productPrice);

                        MyFragmentManager.getInstance().addFragment(productScheduleFragment);
                        transaction.replace(R.id.fragment_container, productScheduleFragment).commit();
                    } else{
                        PlanSelectionFragment planSelectionFragment = new PlanSelectionFragment();
                        planSelectionFragment.setSelectedProductData(productsDataList.get(pPosition));
                        MyFragmentManager.getInstance().addFragment(planSelectionFragment);
                        transaction.replace(R.id.fragment_container,planSelectionFragment).commit();
                    }
//                    if (intent.getStringExtra("selected_plan").equalsIgnoreCase(Constants.ADD_SPECIAL_ORDER + "")) {
//
//                        SpecialOrderFragment specialOrderFragment = new SpecialOrderFragment();
//                        specialOrderFragment.setProductId(productId);
//                        MyFragmentManager.getInstance().addFragment(specialOrderFragment);
//                        transaction.replace(R.id.fragment_container, specialOrderFragment).commit();
//
//                    } else if (intent.getStringExtra("selected_plan").equalsIgnoreCase(Constants.ADD_NEW_WEEKLY_PLAN + "")) {
//
//                        ProductScheduleFragment productScheduleFragment = new ProductScheduleFragment();
//                        productScheduleFragment.setProductId(productId);
//                        MyFragmentManager.getInstance().addFragment(productScheduleFragment);
//                        transaction.replace(R.id.fragment_container, productScheduleFragment).commit();
//                    }


                break;

            case ON_CLICK_PRODUCT_INFO:
                showProductInfoDialog(pPosition);
                break;

        }

    }

    public void showProductInfoDialog(int position) {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Product Info: " + productsDataList.get(position).getProduct_name());
        alertDialog.setIcon(R.drawable.product_info);
        alertDialog.setMessage("Measured in Ltr: 1\nPrice: " + productsDataList.get(position).getPrice());
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_all_plans:

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                ClientPlansFragment clientPlansFragment = new ClientPlansFragment();
                MyFragmentManager.getInstance().addFragment(clientPlansFragment);
                transaction.replace(R.id.fragment_container, clientPlansFragment);
                transaction.commit();
                break;

            case R.id.btn_back_products:
                ((MainActivity) getActivity()).onBackPressed();
                break;
        }
    }
}
