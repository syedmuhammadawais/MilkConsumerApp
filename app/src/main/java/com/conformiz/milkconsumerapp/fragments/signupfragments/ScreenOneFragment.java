package com.conformiz.milkconsumerapp.fragments.signupfragments;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.models.Request.SignUpUserRootRequest;
import com.conformiz.milkconsumerapp.models.response.ZoneListRootResponse;
import com.conformiz.milkconsumerapp.models.response.ZoneListRootResponseData;
import com.conformiz.milkconsumerapp.network.INetworkListener;
import com.conformiz.milkconsumerapp.network.NetworkOperations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Fahad.Munir on 12-Apr-17.
 */

public class ScreenOneFragment extends Fragment implements View.OnClickListener,
        View.OnFocusChangeListener,
        Spinner.OnItemSelectedListener,
        INetworkListener {

    private EditText firstNameET;
    private EditText lastNameET;
    private EditText areaET;
    private EditText addressET;
   // private EditText floorET;

    private TextView spinnerLabelArea, addDobTV;

    private ProgressDialog dialog;
    private DatePickerDialog datePickerDialog;


    private Spinner zoneListSP;
    ArrayAdapter spinnerAdapter;

    ArrayList<ZoneListRootResponseData> mZoneListData = new ArrayList<>();

    int selectedZoneId = 0;
    public boolean isValidate = false;
    String dateOfBirth = "";

    public ScreenOneFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_screen_one, container, false);

        firstNameET = (EditText) view.findViewById(R.id.et_first_name_sign_up);
        lastNameET = (EditText) view.findViewById(R.id.et_last_name_sign_up);
       // areaET = (EditText) view.findViewById(R.id.et_area_sign_up);
        addressET = (EditText) view.findViewById(R.id.et_address_sign_up);
        addDobTV = (TextView) view.findViewById(R.id.tv_dob_sign_up_label);
        spinnerLabelArea = (TextView) view.findViewById(R.id.sp_zone_label);
        spinnerLabelArea.setOnClickListener(this);

        zoneListSP = (Spinner) view.findViewById(R.id.sp_zone_sign_up);
        spinnerAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, mZoneListData);
        zoneListSP.setAdapter(spinnerAdapter);
        zoneListSP.setOnItemSelectedListener(this);

        firstNameET.setOnClickListener(this);
        lastNameET.setOnClickListener(this);
        addDobTV.setOnClickListener(this);
        addressET.setOnClickListener(this);
        view.findViewById(R.id.tv_dob_sign_up_label).setOnClickListener(this);
        //floorET.setOnClickListener(this);

        firstNameET.setOnFocusChangeListener(this);
        lastNameET.setOnFocusChangeListener(this);
     //   areaET.setOnFocusChangeListener(this);
        addressET.setOnFocusChangeListener(this);
       // floorET.setOnFocusChangeListener(this);

        NetworkOperations.getInstance().getZoneList(getActivity(),this);
        return view;

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.sp_zone_label:
                zoneListSP.performClick();
                break;

            case R.id.et_last_name_sign_up:
                break;

//            case R.id.et_area_sign_up:
//                break;

            case R.id.tv_dob_sign_up_label:
            case R.id.tv_dob_sign_up:
                alertDatePicker();
                break;

        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        switch (v.getId()) {

            case R.id.et_first_name_sign_up:
                if (!hasFocus) {
                    if (TextUtils.isEmpty(firstNameET.getText().toString().trim())) {
                        firstNameET.setError("Please Enter FirstName");
                        isValidate = false;
                    } else {
                        isValidate = true;
                    }
                }
                break;

            case R.id.et_last_name_sign_up:
                if (!hasFocus) {
                    if (TextUtils.isEmpty(lastNameET.getText().toString().trim())) {
                        lastNameET.setError("Please Enter LastName");

                        isValidate = false;
                    } else {
                        isValidate = true;
                    }
                }
                break;

//            case R.id.et_area_sign_up:
//                if (!hasFocus) {
//                    if (TextUtils.isEmpty(areaET.getText().toString().trim())) {
//                        areaET.setError("Please Enter Area");
//                        isValidate = false;
//                    } else {
//                        isValidate = true;
//                    }
//                }
//                break;

            case R.id.et_address_sign_up:
                if (!hasFocus) {
                    if (TextUtils.isEmpty(addressET.getText().toString().trim())) {
                        addressET.setError("Please Enter Address");

                        isValidate = false;
                    } else {
                        isValidate = true;
                    }
                }
                break;

            case R.id.et_floor_sign_up:
//                if (!hasFocus) {
//                    if (TextUtils.isEmpty(floorET.getText().toString().trim())) {
//                      //  floorET.setError("Please Enter Address");
//
//                       // isValidate = false;
//                    } else {
//                      //  isValidate = true;
//                    }
//                }
                break;

        }
    }


    public void addDataToRequestObject(SignUpUserRootRequest request) {

        request.setFullName(firstNameET.getText().toString() + " " + lastNameET.getText().toString());
        request.setFather_or_husband_name(lastNameET.getText().toString());
       // request.setAddress(addressET.getText().toString() + " " + floorET.getText().toString());
        request.setAddress(addressET.getText().toString()+"");
        request.setZone_id(selectedZoneId + "");
        request.setDate_of_birth(dateOfBirth);


        request.setCell_no_2("");
        request.setArea("");
        request.setCnic("");
        request.setCity("");
        request.setResidence_phone_no("");


    }

    @Override
    public void onPreExecute() {
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading Zones....");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onPostExecute(Object result) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

        if (result != null) {

            if (result instanceof ZoneListRootResponse ) {

                ZoneListRootResponse response = (ZoneListRootResponse) result;

                if(response.getSuccess() && response.getData()!=null && response.getData().size()> 0){

                    mZoneListData.clear();
                    mZoneListData.addAll(response.getData());
                    spinnerAdapter.notifyDataSetChanged();

                }


            }
        }
    }

    @Override
    public void doInBackground() {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        selectedZoneId = Integer.parseInt(mZoneListData.get(position).getZone_id());

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public boolean checkValidation(){

        if(TextUtils.isEmpty(firstNameET.getText().toString())){
            isValidate = false;
        } else if(TextUtils.isEmpty(addressET.getText().toString())){
            isValidate = false;
        }
       // else if(TextUtils.isEmpty(floorET.getText().toString())){
           // isValidate = false;
       // }
        else if(mZoneListData.size() == 0){
            isValidate = false;
        } else isValidate = true;

        return isValidate;
    }

    public void alertDatePicker() {

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.date_picker, null, false);
        final Calendar c = Calendar.getInstance();
        final DatePicker myDatePicker = (DatePicker) view.findViewById(R.id.myDatePicker);
        myDatePicker.setCalendarViewShown(false);

        if(dateOfBirth.trim().length()>0){

            String yearMonthDay[] = dateOfBirth.split("-");
            myDatePicker.updateDate(Integer.parseInt(yearMonthDay[0]),Integer.parseInt(yearMonthDay[2])-1,Integer.parseInt(yearMonthDay[1]));
        }


        // the alert dialog
        new AlertDialog.Builder(getActivity()).setView(view)
                .setTitle("Select Date")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        int month = myDatePicker.getMonth() + 1;
                        c.set(Calendar.MONTH,month-1);
                        int day = myDatePicker.getDayOfMonth();
                        int year = myDatePicker.getYear();

                        dateOfBirth = year+"-"+day+"-"+month;
                        addDobTV.setText(day+"/"+c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                        +"/"+year);

                    }

                }).show();
    }


}
