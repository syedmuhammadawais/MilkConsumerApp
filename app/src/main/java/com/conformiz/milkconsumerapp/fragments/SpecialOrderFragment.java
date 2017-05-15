package com.conformiz.milkconsumerapp.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.activities.MainActivity;
import com.conformiz.milkconsumerapp.models.Request.CreateSpecialOrderRootRequest;
import com.conformiz.milkconsumerapp.models.response.PreferredTimeListRootResponse;
import com.conformiz.milkconsumerapp.models.response.PreferredTimeListRootResponseData;
import com.conformiz.milkconsumerapp.models.response.ProductWeeklyScheduleRootResponse;
import com.conformiz.milkconsumerapp.models.response.SaveDataArrayResponse;
import com.conformiz.milkconsumerapp.network.INetworkListener;
import com.conformiz.milkconsumerapp.network.NetworkOperations;
import com.conformiz.milkconsumerapp.utils.Constants;
import com.conformiz.milkconsumerapp.utils.SharedPreferenceUtil;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;


/**
 * Created by Fahad.Munir on 24-Apr-17.
 */

public class SpecialOrderFragment extends Fragment implements View.OnClickListener, INetworkListener {

    String TAG = "ProductSchedule ";
    private String mProductId = "0";
    private String mProductUnit = "";
    private String mProductName = "";

    private String mOrderStartDate = "";
    private String mPreferredTimeId = "1";

    int selectedIndex = 0;
    boolean isValidate = false;


    private ProgressDialog dialog;

    EditText time_sp_order;
    EditText quantity_sp_order;

    TextView orderStartDate;

    ArrayList<PreferredTimeListRootResponseData> preferredTimeList = new ArrayList<>();
    LinkedHashMap<String, String> preferredTimeId = new LinkedHashMap<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_special_order, container, false);


        ((TextView) view.findViewById(R.id.tv_special_product_name)).setText(mProductName);

        orderStartDate = (TextView) view.findViewById(R.id.et_start_date_sp_order);
        orderStartDate.setOnClickListener(this);

        view.findViewById(R.id.btn_back_special_order).setOnClickListener(this);

        time_sp_order = (EditText) view.findViewById(R.id.et_time_sp_order);
        time_sp_order.setOnClickListener(this);

        quantity_sp_order = (EditText) view.findViewById(R.id.et_quantity_sp_order);

        view.findViewById(R.id.btn_save_sp_order).setOnClickListener(this);

        ((TextView) view.findViewById(R.id.tv_unit_special_order)).setText(mProductUnit);

        NetworkOperations.getInstance().getPreferredTimeList(getActivity(), new INetworkListener<PreferredTimeListRootResponse>() {
            @Override
            public void onPreExecute() {

            }

            @Override
            public void onPostExecute(PreferredTimeListRootResponse result) {
                if (result != null) {

                    PreferredTimeListRootResponse response = (PreferredTimeListRootResponse) result;

                    if (response.getSuccess()) {
                        preferredTimeList.clear();
                        preferredTimeId.clear();
                        preferredTimeList = response.getData();
                        for (int i = 0; i < preferredTimeList.size(); i++) {
                            preferredTimeId.put(preferredTimeList.get(i).getPreferred_time_id(), preferredTimeList.get(i).getPreferred_time_name());
                        }
                    } else {

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        JSONObject postArgs = new JSONObject();
        try {
            postArgs.put("client_id", SharedPreferenceUtil.getInstance(getActivity()).getClientId());
            postArgs.put("product_id", mProductId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        NetworkOperations.getInstance().postData(getActivity(), Constants.ACTION_POST_WEEKLY_SCHEDULE, postArgs, this, ProductWeeklyScheduleRootResponse.class);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_save_sp_order:

                if (validation()) {
                    CreateSpecialOrderRootRequest request = new CreateSpecialOrderRootRequest();
                    request.setClient_id(SharedPreferenceUtil.getInstance(getActivity()).getClientId());
                    request.setProduct_id(mProductId);
                    request.setQuantity(quantity_sp_order.getText().toString());
                    request.setDelivery_on(mOrderStartDate);
                    request.setPreferred_time_id(mPreferredTimeId);
                    Log.i(TAG, "onClick: Json = " + new Gson().toJson(request));
                    NetworkOperations.getInstance().postData(getActivity(), Constants.ACTION_POST_CREATE_SPECIAL_ORDER, request, this, SaveDataArrayResponse.class);

                } else {
                    Toast.makeText(getActivity(), "Please Update Fileds with Errors", Toast.LENGTH_SHORT).show();
                }
                break;


            case R.id.et_start_date_sp_order:
                alertDatePicker();
                break;

            case R.id.et_time_sp_order:
                Log.i(TAG, "onClick: ");
                showPreferredTimeDialog(time_sp_order, 0);
                break;

            case R.id.btn_back_special_order:
                ((MainActivity) getActivity()).onBackPressed();
                break;


        }
    }


    public void setProductId(String productId) {
        mProductId = productId;
    }

    public void setProductUnit(String productUnit) {
        mProductUnit = productUnit;
    }

    public void setProductName(String productName) {
        mProductName = productName;
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

            if (result instanceof SaveDataArrayResponse) {
                SaveDataArrayResponse saveDataResponse = (SaveDataArrayResponse) result;
                if (saveDataResponse.getSuccess()) {
                    Toast.makeText(getActivity(), "Special Order Created/Updated Successfully", Toast.LENGTH_SHORT).show();
                    getActivity().onBackPressed();
                    getActivity().onBackPressed();
                } else {
                    Toast.makeText(getActivity(), "Could'nt Create/Update Special Order", Toast.LENGTH_SHORT).show();
                }
            }

        } else {
            Toast.makeText(getActivity(), "Could not found data", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void doInBackground() {

    }

    public void alertDatePicker() {

    /*
     * Inflate the XML view. activity_main is in res/layout/date_picker.xml
     */
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.date_picker, null, false);

        // the time picker on the alert dialog, this is how to get the value
        final DatePicker myDatePicker = (DatePicker) view.findViewById(R.id.myDatePicker);

        // so that the calendar view won't appear
        myDatePicker.setCalendarViewShown(false);
        myDatePicker.setMinDate(System.currentTimeMillis() - 1000);

        // the alert dialog
        new AlertDialog.Builder(getActivity()).setView(view)
                .setTitle("Select Date")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        int month = myDatePicker.getMonth() + 1;
                        int day = myDatePicker.getDayOfMonth();
                        int year = myDatePicker.getYear();
                        orderStartDate.setText("Order Required On: " + changeDateFormat(year + "-" + month + "-" + day));
                        mOrderStartDate = year + "-" + month + "-" + day;
                        dialog.cancel();

                    }

                }).show();
    }


    public void showPreferredTimeDialog(final EditText editText, final int index) {

        final String[] values = new String[preferredTimeList.size()];
        for (int i = 0; i < preferredTimeList.size(); i++) {
            values[i] = preferredTimeList.get(i).getPreferred_time_name();

        }

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select Preferred Timings")
                .setSingleChoiceItems(values, selectedIndex, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub

                        selectedIndex = arg1;
                        editText.setText(values[arg1]);

                        for (int i = 0; i < preferredTimeList.size(); i++) {
                            if (preferredTimeList.get(i).getPreferred_time_name().equalsIgnoreCase(values[arg1])) {
                                Log.i(TAG, "Selected: " + preferredTimeList.get(i).getPreferred_time_id());
                                mPreferredTimeId = preferredTimeList.get(i).getPreferred_time_id();

                                break;
                            }
                        }
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        editText.setText(values[selectedIndex]);

                        Log.i(TAG, "onClick: in ok " + selectedIndex);

                    }
                });
        AlertDialog dialog = builder.create();

        dialog.show();
    }


    public boolean validation() {

        if (TextUtils.isEmpty(orderStartDate.getText().toString())) {
            isValidate = false;
            orderStartDate.setError("Please Select Order Start date");
        } else {
            isValidate = true;
        }

        if (TextUtils.isEmpty(quantity_sp_order.getText().toString())) {
            isValidate = false;
            quantity_sp_order.setError("Please Select Quantity");
        } else isValidate = true;

        if (TextUtils.isEmpty(time_sp_order.getText().toString())) {
            isValidate = false;
            time_sp_order.setError("Please Select Preferred Time");
        } else isValidate = true;

        return isValidate;
    }

    public String changeDateFormat(String strDate) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMM d, yyyy");
        Date date = null;
        String str = null;
        if (strDate.trim().length() > 0) {

            try {
                date = inputFormat.parse(strDate);
                str = outputFormat.format(date);
                orderStartDate.setText("Order Start Date: " + str);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return str;
    }

}