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
import com.conformiz.milkconsumerapp.models.SaveDataObjectResponse;
import com.conformiz.milkconsumerapp.models.response.AllProductsRootResponseData;
import com.conformiz.milkconsumerapp.models.response.PreferredTimeListRootResponseData;
import com.conformiz.milkconsumerapp.models.response.SaveDataArrayResponse;
import com.conformiz.milkconsumerapp.network.INetworkListener;
import com.conformiz.milkconsumerapp.network.NetworkOperations;
import com.conformiz.milkconsumerapp.utils.Constants;
import com.conformiz.milkconsumerapp.utils.SharedPreferenceUtil;
import com.conformiz.milkconsumerapp.utils.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 * Created by Fahad.Munir on 25-May-17.
 */

public class PauseDeliveriesFragment extends Fragment implements View.OnClickListener, INetworkListener {

    String TAG = "PauseSchedule ";

    AllProductsRootResponseData mSelectedProductData = new AllProductsRootResponseData();

    private String mOrderStartDate = "";
    private String mOrderEndDate = "";
    private String mDateValue = "";
    private String mPreferredTimeId = "1";

    int selectedIndex = 0;
    boolean isValidate = false;

    private ProgressDialog dialog;

    // EditText time_sp_order;
    // EditText quantity_sp_order;

    TextView orderStartDate, orderEndDate;

    ArrayList<PreferredTimeListRootResponseData> preferredTimeList = new ArrayList<>();
    LinkedHashMap<String, String> preferredTimeId = new LinkedHashMap<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pause_deliveries, container, false);

        ((TextView) view.findViewById(R.id.tv_pause_product_name)).setText(mSelectedProductData.getProduct_name());

        orderStartDate = (TextView) view.findViewById(R.id.et_start_date_pause_order);
        orderEndDate = (TextView) view.findViewById(R.id.et_end_date_pause_order);

        orderStartDate.setOnClickListener(this);
        orderEndDate.setOnClickListener(this);

        view.findViewById(R.id.btn_back_pause_order).setOnClickListener(this);
        Utility.getInstance().buttonEffect(view.findViewById(R.id.btn_save_pause_order), R.color.app_brown_light);
        view.findViewById(R.id.btn_save_pause_order).setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        JSONObject postArgs = new JSONObject();
        try {
            postArgs.put("client_id", SharedPreferenceUtil.getInstance(getActivity()).getClientId());
            postArgs.put("product_id", mSelectedProductData.getProduct_id());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //  NetworkOperations.getInstance().postData(getActivity(), Constants.ACTION_POST_WEEKLY_SCHEDULE, postArgs, this, ProductWeeklyScheduleRootResponse.class);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_save_pause_order:

                if (validation()) {

                    JSONObject request = new JSONObject();
                    try {
                        request.put("start_date",mOrderStartDate);
                        request.put("end_date",mOrderEndDate);
                        request.put("client_id",SharedPreferenceUtil.getInstance(getActivity()).getClientId()+"");
                        request.put("product_id",mSelectedProductData.getProduct_id()+"");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    NetworkOperations.getInstance().postData(getActivity(), Constants.ACTION_POST_PAUSE_REGULAR_ORDERS, request, this, SaveDataObjectResponse.class);

                } else {
                    Toast.makeText(getActivity(), "Please Update Fields with Errors", Toast.LENGTH_SHORT).show();
                }
                break;


            case R.id.et_start_date_pause_order:
                // 1 for start date
                alertDatePicker(1);
                break;


            case R.id.et_end_date_pause_order:
                // 2 for end date
                if (mOrderStartDate == null || mOrderStartDate.trim().length() == 0) {
                    showMessageDialog("Please Select Start Date First");
                } else {
                    alertDatePicker(2);
                }
                break;

            case R.id.btn_back_pause_order:
                getActivity().onBackPressed();
                break;

        }
    }

    @Override
    public void onPreExecute() {
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Updating Order....");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onPostExecute(Object result) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

        if (result != null) {

            if (result instanceof SaveDataObjectResponse) {
                SaveDataObjectResponse saveDataResponse = (SaveDataObjectResponse) result;
                if (saveDataResponse.getSuccess()) {
                    Toast.makeText(getActivity(), "Order Paused Successfully", Toast.LENGTH_SHORT).show();
                    getActivity().onBackPressed();
                    getActivity().onBackPressed();
                } else {
                    Toast.makeText(getActivity(), "Could'nt Pause Order", Toast.LENGTH_SHORT).show();
                }
            }

        } else {
            Toast.makeText(getActivity(), "Could not found data Server Error", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void doInBackground() {

    }

    public void alertDatePicker(final int date) {

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.date_picker, null, false);

        final DatePicker myDatePicker = (DatePicker) view.findViewById(R.id.myDatePicker);
        myDatePicker.setCalendarViewShown(false);

        if(date == 1 && mOrderStartDate.trim().length() > 0){
            int []yearMonthDay = Utility.getInstance().getYearMonthDay(mOrderStartDate);

            if(yearMonthDay.length == 3) {
                myDatePicker.updateDate(yearMonthDay[0], yearMonthDay[1], yearMonthDay[2]);
                Log.i(TAG, "alertDatePicker: Year: "+yearMonthDay[0]+" Month: "+yearMonthDay[1]+" day: "+yearMonthDay[2]);
            }
        } else if(date == 2 && mOrderEndDate.trim().length() > 0){
            int []yearMonthDay = Utility.getInstance().getYearMonthDay(mOrderEndDate);
            if(yearMonthDay.length == 3) {
                myDatePicker.updateDate(yearMonthDay[0], yearMonthDay[1], yearMonthDay[2]);
                Log.i(TAG, "alertDatePicker: End Year: "+yearMonthDay[0]+" Month: "+yearMonthDay[1]+" day: "+yearMonthDay[2]);
            }
        } else {
            myDatePicker.setMinDate(System.currentTimeMillis() - 1000);
        }

        // the alert dialog
        new AlertDialog.Builder(getActivity()).setView(view)
                .setTitle("Select Date")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        int month = myDatePicker.getMonth() + 1;
                        int day = myDatePicker.getDayOfMonth();
                        int year = myDatePicker.getYear();

                        if (date == 1) {
                            mOrderStartDate = year + "-" + month + "-" + day;
                            orderStartDate.setText(Utility.getInstance().changeDateFormat(mOrderStartDate));
//                            orderEndDate.setText("");
//                            dialog.cancel();
                        } else {
                            mOrderEndDate = year + "-" + month + "-" + day;
                            if(Utility.getInstance().compareTwoDates(mOrderStartDate, mOrderEndDate)){
                                orderEndDate.setText(Utility.getInstance().changeDateFormat(mOrderEndDate));
                                isValidate = true;
                                dialog.cancel();
                            }else {
                                isValidate = false;
                                showMessageDialog("End Date Should Greater or Equal to Start Date");
                            }
                        }

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
            orderStartDate.setError("Please Select Start date");
        } else {
            isValidate = true;
        }

        if (TextUtils.isEmpty(orderEndDate.getText().toString())) {
            isValidate = false;
            orderStartDate.setError("Please Select End date");
        } else {
            isValidate = true;
        }

        if(Utility.getInstance().compareTwoDates(mOrderStartDate, mOrderEndDate)){
            isValidate = true;
        } else {
            isValidate = false;
            orderEndDate.setError("End Date Should Greater or Equal to Start Date");
        }

        return isValidate;
    }

    public void setSelectedProductData(AllProductsRootResponseData data) {
        mSelectedProductData = data;
    }

    public void showMessageDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("" + msg)
                .setIcon(R.drawable.product_info)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();


                    }
                });

        builder.show();
    }


}