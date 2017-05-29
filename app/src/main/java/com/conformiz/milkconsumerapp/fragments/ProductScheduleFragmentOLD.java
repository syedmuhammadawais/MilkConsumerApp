package com.conformiz.milkconsumerapp.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
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
import com.conformiz.milkconsumerapp.mainfragmentmanager.MyFragmentManager;
import com.conformiz.milkconsumerapp.models.response.PreferredTimeListRootResponse;
import com.conformiz.milkconsumerapp.models.response.PreferredTimeListRootResponseData;
import com.conformiz.milkconsumerapp.models.response.ProductWeeklyScheduleRootResponse;
import com.conformiz.milkconsumerapp.models.response.ProductWeeklyScheduleRootResponseData;
import com.conformiz.milkconsumerapp.models.response.SaveDataResponse;
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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import it.beppi.tristatetogglebutton_library.TriStateToggleButton;

/**
 * Created by Fahad.Munir on 24-May-17.
 */

public class ProductScheduleFragmentOLD  extends Fragment implements View.OnClickListener, View.OnFocusChangeListener, INetworkListener {

    String TAG = "ProductSchedule ";
    private String mProductId = "0";
    private String mProductUnit = "";
    private String mProductName = "";
    private String mProductPrice = "";



    private int selectedIndexReferredTime = 0;
    private String mClientId = "";
    private ProgressDialog dialog;

    int autoUpdateUI = 0;

    // 0 for new 1 for update
    int isPlanUpdate = 0;

    TriStateToggleButton tb_Monday, tb_Tuesday, tb_Wednesday, tb_Thursday, tb_Friday, tb_Saturday, tb_Sunday;
    EditText time_et_monday, time_et_tuesday, time_et_wednesday, time_et_thursday, time_et_friday, time_et_saturday, time_et_sunday;
    EditText quantity_et_monday, quantity_et_tuesday, quantity_et_wednesday, quantity_et_thursday, quantity_et_friday, quantity_et_saturday, quantity_et_sunday;

    TextView orderStartDate;

    ArrayList<ProductWeeklyScheduleRootResponseData> mProductWeekSchedule = new ArrayList<>();
    ArrayList<PreferredTimeListRootResponseData> preferredTimeList = new ArrayList<>();
    HashMap<String, String> preferredTimeId = new HashMap<>();
    ProductWeeklyScheduleRootResponse mResponse = new ProductWeeklyScheduleRootResponse();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_schedule_old, container, false);

        ((TextView) view.findViewById(R.id.tv_weekly_product_name)).setText(mProductName);
        ((TextView) view.findViewById(R.id.tv_rate_ps)).setText("Rs "+mProductPrice+" per "+mProductUnit);

        orderStartDate = (TextView) view.findViewById(R.id.et_start_date);
        orderStartDate.setOnClickListener(this);

        view.findViewById(R.id.btn_special_order).setOnClickListener(this);
        view.findViewById(R.id.btn_back_schedule).setOnClickListener(this);

        ((TextView) view.findViewById(R.id.tv_unit_monday_0)).setText(mProductUnit);
        ((TextView) view.findViewById(R.id.tv_unit_tuesday_1)).setText(mProductUnit);
        ((TextView) view.findViewById(R.id.tv_unit_wednesday_2)).setText(mProductUnit);
        ((TextView) view.findViewById(R.id.tv_unit_thursday_3)).setText(mProductUnit);
        ((TextView) view.findViewById(R.id.tv_unit_friday_4)).setText(mProductUnit);
        ((TextView) view.findViewById(R.id.tv_unit_saturday_5)).setText(mProductUnit);
        ((TextView) view.findViewById(R.id.tv_unit_sunday_6)).setText(mProductUnit);


        tb_Monday = (TriStateToggleButton) view.findViewById(R.id.tb_fps_monday_0);
        tb_Monday.setOnToggleChanged(new TriStateToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(TriStateToggleButton.ToggleStatus toggleStatus, boolean b, int i) {

                switch (i) {
                    case 0:
                        mProductWeekSchedule.get(0).setIsSelected("0");
                        break;
                    case 2:
                        mProductWeekSchedule.get(0).setIsSelected("1");
                        break;
                }
            }
        });

        tb_Tuesday = (TriStateToggleButton) view.findViewById(R.id.tb_fps_tuesday_1);
        tb_Tuesday.setOnToggleChanged(new TriStateToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(TriStateToggleButton.ToggleStatus toggleStatus, boolean b, int i) {
                switch (i) {
                    case 0:
                        mProductWeekSchedule.get(1).setIsSelected("0");
                        break;
                    case 2:
                        mProductWeekSchedule.get(1).setIsSelected("1");
                        break;
                }
            }
        });

        tb_Wednesday = (TriStateToggleButton) view.findViewById(R.id.tb_fps_wednesday_2);
        tb_Wednesday.setOnToggleChanged(new TriStateToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(TriStateToggleButton.ToggleStatus toggleStatus, boolean b, int i) {
                switch (i) {
                    case 0:
                        mProductWeekSchedule.get(2).setIsSelected("0");
                        break;
                    case 2:
                        mProductWeekSchedule.get(2).setIsSelected("1");
                        break;
                }
            }
        });

        tb_Thursday = (TriStateToggleButton) view.findViewById(R.id.tb_fps_thursday_3);
        tb_Thursday.setOnToggleChanged(new TriStateToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(TriStateToggleButton.ToggleStatus toggleStatus, boolean b, int i) {
                switch (i) {
                    case 0:
                        mProductWeekSchedule.get(3).setIsSelected("0");
                        break;
                    case 2:
                        mProductWeekSchedule.get(3).setIsSelected("1");
                        break;
                }
            }
        });

        tb_Friday = (TriStateToggleButton) view.findViewById(R.id.tb_fps_friday_4);
        tb_Friday.setOnToggleChanged(new TriStateToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(TriStateToggleButton.ToggleStatus toggleStatus, boolean b, int i) {
                switch (i) {
                    case 0:
                        mProductWeekSchedule.get(4).setIsSelected("0");
                        break;
                    case 2:
                        mProductWeekSchedule.get(4).setIsSelected("1");
                        break;
                }
            }
        });

        tb_Saturday = (TriStateToggleButton) view.findViewById(R.id.tb_fps_saturday_5);
        tb_Saturday.setOnToggleChanged(new TriStateToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(TriStateToggleButton.ToggleStatus toggleStatus, boolean b, int i) {
                switch (i) {
                    case 0:
                        mProductWeekSchedule.get(5).setIsSelected("0");
                        break;
                    case 2:
                        mProductWeekSchedule.get(5).setIsSelected("1");
                        break;
                }
            }
        });

        tb_Sunday = (TriStateToggleButton) view.findViewById(R.id.tb_fps_sunday_6);
        tb_Sunday.setOnToggleChanged(new TriStateToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(TriStateToggleButton.ToggleStatus toggleStatus, boolean b, int i) {
                switch (i) {
                    case 0:
                        mProductWeekSchedule.get(6).setIsSelected("0");
                        break;
                    case 2:
                        mProductWeekSchedule.get(6).setIsSelected("1");
                        break;
                }
            }
        });

        time_et_monday = (EditText) view.findViewById(R.id.et_time_monday_0);
        time_et_tuesday = (EditText) view.findViewById(R.id.et_time_tuesday_1);
        time_et_wednesday = (EditText) view.findViewById(R.id.et_time_wednesday_2);
        time_et_thursday = (EditText) view.findViewById(R.id.et_time_thursday_3);
        time_et_friday = (EditText) view.findViewById(R.id.et_time_friday_4);
        time_et_saturday = (EditText) view.findViewById(R.id.et_time_saturday_5);
        time_et_sunday = (EditText) view.findViewById(R.id.et_time_sunday_6);

        time_et_monday.setOnClickListener(this);
        time_et_tuesday.setOnClickListener(this);
        time_et_wednesday.setOnClickListener(this);
        time_et_thursday.setOnClickListener(this);
        time_et_friday.setOnClickListener(this);
        time_et_thursday.setOnClickListener(this);
        time_et_saturday.setOnClickListener(this);
        time_et_sunday.setOnClickListener(this);

        quantity_et_monday = (EditText) view.findViewById(R.id.et_quantity_monday_0);
        quantity_et_tuesday = (EditText) view.findViewById(R.id.et_quantity_tuesday_1);
        quantity_et_wednesday = (EditText) view.findViewById(R.id.et_quantity_wednesday_2);
        quantity_et_thursday = (EditText) view.findViewById(R.id.et_quantity_thursday_3);
        quantity_et_friday = (EditText) view.findViewById(R.id.et_quantity_friday_4);
        quantity_et_saturday = (EditText) view.findViewById(R.id.et_quantity_saturday_5);
        quantity_et_sunday = (EditText) view.findViewById(R.id.et_quantity_sunday_6);

        quantity_et_monday.setOnFocusChangeListener(this);
        quantity_et_tuesday.setOnFocusChangeListener(this);
        quantity_et_wednesday.setOnFocusChangeListener(this);
        quantity_et_thursday.setOnFocusChangeListener(this);
        quantity_et_friday.setOnFocusChangeListener(this);
        quantity_et_saturday.setOnFocusChangeListener(this);
        quantity_et_sunday.setOnFocusChangeListener(this);


        view.findViewById(R.id.btn_fps_save_plan).setOnClickListener(this);

        NetworkOperations.getInstance().getPreferredTimeList(getActivity(), new INetworkListener<PreferredTimeListRootResponse>() {
            @Override
            public void onPreExecute() {

            }

            @Override
            public void onPostExecute(PreferredTimeListRootResponse result) {
                if (result instanceof PreferredTimeListRootResponse) {

                    PreferredTimeListRootResponse response = (PreferredTimeListRootResponse) result;

                    if (response.getSuccess()) {
                        preferredTimeList.clear();
                        preferredTimeId.clear();
                        preferredTimeList = response.getData();
                        for (int i = 0; i < preferredTimeList.size(); i++) {
                            preferredTimeId.put(preferredTimeList.get(i).getPreferred_time_id(), preferredTimeList.get(i).getPreferred_time_name());
                        }
                    } else {

                        Toast.makeText(getActivity(), "Could'nt download Preffered time List", Toast.LENGTH_SHORT).show();
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

        mClientId = SharedPreferenceUtil.getInstance(getActivity()).getClientId();
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

            case R.id.btn_special_order:
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                SpecialOrderFragment specialOrderFragment = new SpecialOrderFragment();
//                specialOrderFragment.setProductId(mProductId);
//                specialOrderFragment.setProductUnit(mProductUnit);
//                specialOrderFragment.setProductName(mProductName);

                MyFragmentManager.getInstance().addFragment(specialOrderFragment);
                transaction.replace(R.id.fragment_container, specialOrderFragment).commit();
                break;


            case R.id.btn_back_schedule:
                ((MainActivity) getActivity()).onBackPressed();
                break;


            case R.id.btn_fps_save_plan:

                //checkValuesAreNotSelectedOrZero();
                boolean savePlan = true;
                Log.i(TAG, "onClick: isAnyDayOn() " + isAnyDayOn());

                if (!isAnyDayOn()) {
                    Log.i(TAG, "onClick: in true ");
                    updateAllElements(1);
                    if (checkValuesAreNotSelectedOrZero()) {
                        showMessageDialogWithValues("Please Update Values for Time or quantity for selected day(s)");
                        savePlan = false;
                    } else {
                        savePlan = true;
                    }

                } else {
                    showMessageDialog("Please Select schedule for one day before save plan");
                    savePlan = false;
                }

//                if (checkValuesAreNotSelectedOrZero()) {
//                    showMessageDialogWithValues("Preferred time not selected or quantity is '0' will not be saved in plan ");
//
//                    savePlan = false;
//                }

                if (savePlan) {
                    Log.i(TAG, "onClick: save plan");
                    savePlan();
                }
                break;


            case R.id.et_start_date:
                if (isPlanUpdate == 0)
                    alertDatePicker();
                break;

            case R.id.et_time_monday_0:
                Log.i(TAG, "onClick: ");
                showPreferredTimeDialog(time_et_monday, 0);
                break;

            case R.id.et_time_tuesday_1:
                showPreferredTimeDialog(time_et_tuesday, 1);
                break;

            case R.id.et_time_wednesday_2:
                showPreferredTimeDialog(time_et_wednesday, 2);
                break;

            case R.id.et_time_thursday_3:
                showPreferredTimeDialog(time_et_thursday, 3);
                break;

            case R.id.et_time_friday_4:
                showPreferredTimeDialog(time_et_friday, 4);
                break;

            case R.id.et_time_saturday_5:
                showPreferredTimeDialog(time_et_saturday, 5);
                break;

            case R.id.et_time_sunday_6:
                showPreferredTimeDialog(time_et_sunday, 6);
                break;

        }
    }


    public void setProductWeeklyData(ArrayList<ProductWeeklyScheduleRootResponseData> productWeekSchedule) {
        mProductWeekSchedule.clear();
        mProductWeekSchedule = productWeekSchedule;
    }


    public void setProductId(String productId) {
        mProductId = productId;
    }

    public void setProductUnit(String productUnit) {
        mProductUnit = productUnit;
    }

    public void setProductName(String productName){
        mProductName = productName;
    }


    public void setProductPrice(String productPrice){
        mProductPrice = productPrice;
    }


    private void savePlan() {
        mResponse.getData().clear();
        updateAllElements(1);// 1 for update
        mResponse.setData(mProductWeekSchedule);

        Log.i(TAG, "onClick: Json = " + new Gson().toJson(mResponse));

        mResponse.setClientId(mClientId);
        mResponse.setProductId(mProductId);
        NetworkOperations.getInstance().postData(getActivity(), Constants.ACTION_POST_SAVE_UPDATE_WEEKLY_PLAN, mResponse, this, SaveDataResponse.class);

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

            if (result instanceof ProductWeeklyScheduleRootResponse && ((ProductWeeklyScheduleRootResponse) result).getSuccess()) {

                mResponse = (ProductWeeklyScheduleRootResponse) result;

                if (mResponse.getData() != null && mResponse.getData().size() > 0) {
                    mProductWeekSchedule.clear();
                    mProductWeekSchedule.addAll(mResponse.getData());

                    SimpleDateFormat outputFormat = new SimpleDateFormat("MMM d, yyyy");

                    if (mResponse.getOrderStartDate().trim().length() > 0) {

                        orderStartDate.setText("Order Start Date: " + changeDateFormat(mResponse.getOrderStartDate()));

                    } else {
                        Calendar cal = Calendar.getInstance();
                        String todaysDate = outputFormat.format(cal.getTime());
                        orderStartDate.setText("Order Start Date: " + todaysDate);
                    }
                    // 0 for display as it is values from get Api
                    updateAllElements(0);
                }

            }


            if (result instanceof SaveDataResponse) {
                SaveDataResponse saveDataResponse = (SaveDataResponse) result;

                if (saveDataResponse.getSuccess()) {
                    Toast.makeText(getActivity(), "Weekly Plan Created/Updated Successfully", Toast.LENGTH_SHORT).show();
                    getActivity().onBackPressed();
                } else {
                    Toast.makeText(getActivity(), "Could'nt Create/Update Weekly Plan", Toast.LENGTH_SHORT).show();
                }
            }

        } else {
            Toast.makeText(getActivity(), "Could not found data", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void doInBackground() {

    }

    // 1 for update 0 for load defaults
    public void updateAllElements(int isUpdate) {

        Log.i(TAG, "updateAllElements: size is " + mProductWeekSchedule.size());
        for (int i = 0; i < mProductWeekSchedule.size(); i++) {

            int dayNumber = Integer.parseInt(mProductWeekSchedule.get(i).getFrequency_id());
            dayNumber--;
            Log.i(TAG, "updateAllElements: frequency id " + dayNumber);
            switch (dayNumber) {
                case 0:
                    updateToggleTimeQuantity(i, tb_Monday, time_et_monday, quantity_et_monday, isUpdate);
//                    if(mProductWeekScgit remote rm originhedule.get(i).getIsSelected().equalsIgnoreCase("1")) {
//                        tb_Monday.setToggleStatus(2, true);
//                    }
//                        time_et_monday.setText(mProductWeekSchedule.get(i).getPreferredTime());
//                        quantity_et_monday.setError(mProductWeekSchedule.get(i).getQuantity());

                    break;

                case 1:
                    updateToggleTimeQuantity(i, tb_Tuesday, time_et_tuesday, quantity_et_tuesday, isUpdate);
                    break;

                case 2:
                    updateToggleTimeQuantity(i, tb_Wednesday, time_et_wednesday, quantity_et_wednesday, isUpdate);
                    break;

                case 3:
                    updateToggleTimeQuantity(i, tb_Thursday, time_et_thursday, quantity_et_thursday, isUpdate);
                    break;

                case 4:
                    updateToggleTimeQuantity(i, tb_Friday, time_et_friday, quantity_et_friday, isUpdate);
                    break;

                case 5:
                    updateToggleTimeQuantity(i, tb_Saturday, time_et_saturday, quantity_et_saturday, isUpdate);
                    break;

                case 6:
                    updateToggleTimeQuantity(i, tb_Sunday, time_et_sunday, quantity_et_sunday, isUpdate);
                    break;

            }
        }

    }

    public void updateToggleTimeQuantity(int index, TriStateToggleButton toggle, EditText time, EditText quantity, int isUpdate) {

        if (isUpdate == 0) {
            if (mProductWeekSchedule.get(index).getIsSelected().equalsIgnoreCase("1")) {
                toggle.setToggleStatus(2, true);
                String timeValue = mProductWeekSchedule.get(index).getPreferredTime();
                time.setText(timeValue.trim().length() == 0 || timeValue == null || timeValue.equalsIgnoreCase("0") ? "Select Time" : "" + preferredTimeId.get(timeValue));

                if (mProductWeekSchedule.get(index).getQuantity().equalsIgnoreCase("0"))
                    quantity.setText("");
                else quantity.setText(mProductWeekSchedule.get(index).getQuantity());
            }
        } else {

            if ((toggle.getToggleStatus()).toString().equalsIgnoreCase("off")) {
                mProductWeekSchedule.get(index).setIsSelected("0");
            } else
                mProductWeekSchedule.get(index).setIsSelected("1");
            //  mProductWeekSchedule.get(index).setPreferredTime(time.getText().toString());
            if (quantity.getText().toString().equalsIgnoreCase("0") || quantity.getText().toString().trim().length() == 0)
                mProductWeekSchedule.get(index).setQuantity("0");
            else mProductWeekSchedule.get(index).setQuantity(quantity.getText().toString());

        }
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
                        orderStartDate.setText("Order Start Date: " + changeDateFormat(year + "-" + month + "-" + day));
                        mResponse.setOrderStartDate(year + "-" + month + "-" + day);
                        dialog.cancel();

                    }

                }).show();
    }

    public void showPreferredTimeDialog(final EditText editText, final int index) {

        if (mProductWeekSchedule.get(index).getIsSelected().equalsIgnoreCase("0")) {
            showMessageDialog("Please Turn on the deliver schedule for this day");
            return;
        }
        int selectedIndex = -1;
        final String[] values = new String[preferredTimeList.size()];
        for (int i = 0; i < preferredTimeList.size(); i++) {
            values[i] = preferredTimeList.get(i).getPreferred_time_name();
        }


        String preferredTimeName = "";
        if (mProductWeekSchedule.size() > 0)
            preferredTimeName = preferredTimeId.get(mProductWeekSchedule.get(index).getPreferredTime());

        Log.i(TAG, "showPreferredTimeDialog: HasMap = " + preferredTimeName);
        for (int i = 0; i < values.length; i++) {
            if (values[i].equalsIgnoreCase(preferredTimeName)) {
                selectedIndex = i;
                break;
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select Preferred Timings")
                .setSingleChoiceItems(values, selectedIndex, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub

                        // editText.setText(values[arg1]);

                        selectedIndexReferredTime = arg1;
//                        for (int i = 0; i < preferredTimeList.size(); i++) {
//                            if (preferredTimeList.get(i).getPreferred_time_name().equalsIgnoreCase(values[arg1])) {
//                                mProductWeekSchedule.get(index).setPreferredTime(preferredTimeList.get(i).getPreferred_time_id());
//                                Log.i(TAG, "Selected: " + mProductWeekSchedule.get(index).getPreferredTime());
//                                break;
//                            }
//                        }
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        editText.setText(values[selectedIndexReferredTime]);

                        for (int i = 0; i < preferredTimeList.size(); i++) {
                            if (preferredTimeList.get(i).getPreferred_time_name().equalsIgnoreCase(values[selectedIndexReferredTime])) {
                                mProductWeekSchedule.get(index).setPreferredTime(preferredTimeList.get(i).getPreferred_time_id());
                                Log.i(TAG, "Selected: " + mProductWeekSchedule.get(index).getPreferredTime());
                                break;
                            }
                        }

                        if(autoUpdateUI == 0){

                            autoUpdateUI = 1;
                            autoUpdatePreferredTime();
                        }


//                        Log.i(TAG, "onClick: which in ok "+which);
//                        editText.setText(values[which]);
//
//                        for (int i = 0; i < preferredTimeList.size(); i++) {
//                            if (preferredTimeList.get(i).getPreferred_time_name().equalsIgnoreCase(values[i])) {
//                                mProductWeekSchedule.get(index).setPreferredTime(preferredTimeList.get(i).getPreferred_time_id());
//                                Log.i(TAG, "Selected: "+mProductWeekSchedule.get(index).getPreferredTime());
//                                break;
//                            }
//                        }
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();

        dialog.show();


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


    public void autoUpdatePreferredTime(){

    }

    public boolean isAnyDayOn() {
        boolean selected = true;
        for (int i = 0; i < mProductWeekSchedule.size(); i++) {
            if (mProductWeekSchedule.get(i).getIsSelected().equalsIgnoreCase("1")) {
                selected = false;
            }
        }
        return selected;
    }

    public boolean checkValuesAreNotSelectedOrZero() {

        boolean showMsg = false;
        for (int i = 0; i < mProductWeekSchedule.size(); i++) {

            if (mProductWeekSchedule.get(i).getIsSelected().equalsIgnoreCase("1")) {

                if (mProductWeekSchedule.get(i).getPreferredTime().equalsIgnoreCase("0")
                        || mProductWeekSchedule.get(i).getQuantity().equalsIgnoreCase("0") ||
                        mProductWeekSchedule.get(i).getQuantity().trim().length() <= 0) {

                    showMsg = true;
                }
            }


//            if (mProductWeekSchedule.get(i).getIsSelected().equalsIgnoreCase("1") &&
//                    mProductWeekSchedule.get(i).getPreferredTime().equalsIgnoreCase("0")) {
//                // week day is on but preferred time not selected
//                mProductWeekSchedule.get(i).setIsSelected("0");
//                showMsg = true;
//            }
//            if (mProductWeekSchedule.get(i).getIsSelected().equalsIgnoreCase("1") &&
//                    mProductWeekSchedule.get(i).getQuantity().equalsIgnoreCase("0")) {
//                // week day is on but quantity is zero
//                mProductWeekSchedule.get(i).setIsSelected("0");
//                showMsg = true;
//
//            }
//
//            if (mProductWeekSchedule.get(i).getIsSelected().equalsIgnoreCase("0") &&
//                    !mProductWeekSchedule.get(i).getPreferredTime().equalsIgnoreCase("0")) {
//                // week day is off but preferred time selected
//                mProductWeekSchedule.get(i).setPreferredTime("0");
//                showMsg = true;
//
//            }

        }

        return showMsg;
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

    public void showMessageDialogWithValues(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("" + msg)
                .setIcon(R.drawable.product_info)
                .setNegativeButton("Update Plan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
//                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                      //  savePlan();
//                        dialog.cancel();
//
//
//                    }
//                });

        builder.show();
    }

    public void showMessageDialogOnEditText(String msg, final View editText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("" + msg)
                .setIcon(R.drawable.product_info)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        editText.setOnFocusChangeListener(null);
                        editText.clearFocus();
                    }
                });

        builder.show();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        switch (v.getId()) {

            case R.id.et_quantity_monday_0:
                if (hasFocus) {
                    if (mProductWeekSchedule.get(0).getIsSelected().equalsIgnoreCase("0")) {
                        showMessageDialogOnEditText("Please first turn on this day deliver to save values", v);
                    }
                }
                break;

            case R.id.et_quantity_tuesday_1:
                if (hasFocus) {
                    if (mProductWeekSchedule.get(1).getIsSelected().equalsIgnoreCase("0")) {
                        showMessageDialogOnEditText("Please first turn on this day deliver to save values", v);
                    }
                }
                break;

            case R.id.et_quantity_wednesday_2:
                if (hasFocus) {
                    if (mProductWeekSchedule.get(2).getIsSelected().equalsIgnoreCase("0")) {
                        showMessageDialogOnEditText("Please first turn on this day deliver to save values", v);
                    }
                }
                break;

            case R.id.et_quantity_thursday_3:
                if (hasFocus) {
                    if (mProductWeekSchedule.get(3).getIsSelected().equalsIgnoreCase("0")) {
                        showMessageDialogOnEditText("Please first turn on this day deliver to save values", v);
                    }
                }
                break;

            case R.id.et_quantity_friday_4:
                if (hasFocus) {
                    if (mProductWeekSchedule.get(4).getIsSelected().equalsIgnoreCase("0")) {
                        showMessageDialogOnEditText("Please first turn on this day deliver to save values", v);
                    }
                }
                break;

            case R.id.et_quantity_saturday_5:
                if (hasFocus) {
                    if (mProductWeekSchedule.get(5).getIsSelected().equalsIgnoreCase("0")) {
                        showMessageDialogOnEditText("Please first turn on this day deliver to save values", v);
                    }
                }
                break;

            case R.id.et_quantity_sunday_6:
                if (hasFocus) {
                    if (mProductWeekSchedule.get(6).getIsSelected().equalsIgnoreCase("0")) {
                        showMessageDialogOnEditText("Please first turn on this day deliver to save values", v);
                    }
                }
                break;


        }
    }

    public void setIsUpdate(int updatePlan) {
        isPlanUpdate = updatePlan;
    }
}

