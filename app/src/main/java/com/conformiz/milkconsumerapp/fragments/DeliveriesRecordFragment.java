package com.conformiz.milkconsumerapp.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.activities.MainActivity;
import com.conformiz.milkconsumerapp.adapters.DeliveriesRecordAdapter;
import com.conformiz.milkconsumerapp.callbacks.OnItemClick;
import com.conformiz.milkconsumerapp.models.response.DeliveriesRecordRootResponse;
import com.conformiz.milkconsumerapp.models.response.DeliveriesRecordRootResponseData;
import com.conformiz.milkconsumerapp.network.INetworkListener;
import com.conformiz.milkconsumerapp.network.NetworkOperations;
import com.conformiz.milkconsumerapp.utils.Constants;
import com.conformiz.milkconsumerapp.utils.MonthYearPicker;
import com.conformiz.milkconsumerapp.utils.SharedPreferenceUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Fahad.Munir on 09-May-17.
 */

public class DeliveriesRecordFragment extends Fragment implements INetworkListener, View.OnClickListener, OnItemClick {

    String TAG = "DeliveriesRecord";
    private TextView monthTV;
    private MonthYearPicker myp;

    private RecyclerView mRecordsRV;

    private ProgressDialog dialog;
    private ArrayList<DeliveriesRecordRootResponseData> mData = new ArrayList<>();
    private DeliveriesRecordAdapter mRecordAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deliveries_record, container, false);

        view.findViewById(R.id.btn_back_deliveries_record).setOnClickListener(this);
        view.findViewById(R.id.rl_show_month).setOnClickListener(this);
        view.findViewById(R.id.btn_select_month).setOnClickListener(this);

        monthTV = (TextView) view.findViewById(R.id.tv_show_month);


        final Date today = new Date();
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);

        Date lastDayOfMonth = calendar.getTime();

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Today            : " + sdf.format(today));
        System.out.println("Last Day of Month: " + sdf.format(lastDayOfMonth));

        myp = new MonthYearPicker(getActivity());
        Log.i(TAG, "onCreateView: month " + myp.getCurrentMonth());
        Log.i(TAG, "onCreateView: year " + myp.getCurrentYear());
        Log.i(TAG, "onCreateView: year " + today.toString());

        myp.build(new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                monthTV.setText("Select Month: " + myp.getSelectedMonthName() + " " + myp.getSelectedYear());
                Log.i(TAG, "onClick: " + myp.getSelectedMonth());
                Log.i(TAG, "onClick: " + myp.getSelectedYear());
                final Date today = new Date();
                final Calendar calendar = Calendar.getInstance();
                calendar.setTime(today);

                calendar.set(Calendar.MONTH, myp.getSelectedMonth());
                calendar.set(Calendar.YEAR, myp.getSelectedYear());
                calendar.add(Calendar.MONTH, 1);
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                calendar.add(Calendar.DATE, -1);

                Date lastDayOfMonth = calendar.getTime();

                DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                System.out.println("Today            : " + sdf.format(today));
                System.out.println("Last Day of Month: " + sdf.format(lastDayOfMonth));


                JSONObject request = new JSONObject();
                try {
                    request.put("client_id", SharedPreferenceUtil.getInstance(getActivity()).getClientId() + "");
                    request.put("start_date", "" + "" + myp.getSelectedYear() + "-" + myp.getSelectedMonth() + "-01");
                    request.put("end_date", "" + sdf.format(lastDayOfMonth));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i(TAG, "onCreateView: request: " + request.toString());
                NetworkOperations.getInstance().postData
                        (getContext(), Constants.ACTION_POST_DELIVERIES_BETWEEN_DATE_RANGE,
                                request, DeliveriesRecordFragment.this, DeliveriesRecordRootResponse.class);


            }
        }, null);

        Log.i(TAG, "onCreateView: " + Calendar.getInstance().get(Calendar.MONTH));
        monthTV.setText("Select Month: " + myp.getMonthName(Calendar.getInstance().get(Calendar.MONTH)) + " " + Calendar.getInstance().get(Calendar.YEAR));
        mRecordsRV = (RecyclerView) view.findViewById(R.id.rv_product_deliveries_records);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        mRecordAdapter = new DeliveriesRecordAdapter(mData, this);
        mRecordsRV.setLayoutManager(linearLayoutManager);
        mRecordsRV.setAdapter(mRecordAdapter);


        JSONObject request = new JSONObject();
        try {
            request.put("client_id", SharedPreferenceUtil.getInstance(getActivity()).getClientId() + "");
            request.put("start_date", "" + sdf.format(today).toString().substring(0, sdf.format(today).toString().length() - 1));
            request.put("end_date", "" + sdf.format(lastDayOfMonth));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "onCreateView: request: " + request.toString());
        NetworkOperations.getInstance().postData
                (getContext(), Constants.ACTION_POST_DELIVERIES_BETWEEN_DATE_RANGE,
                        request, this, DeliveriesRecordRootResponse.class);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back_deliveries_record:
                ((MainActivity) getActivity()).onBackPressed();
                break;

            case R.id.rl_show_month:
                myp.show();
                break;

            case R.id.btn_select_month:
                myp.show();
                break;
        }

    }

    @Override
    public void onPreExecute() {
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading Delivery Records....");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onPostExecute(Object result) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

        if (result != null && result instanceof DeliveriesRecordRootResponse) {

            DeliveriesRecordRootResponse response = (DeliveriesRecordRootResponse) result;
            if (response.getSuccess()) {

                mData.clear();

                if (response.getData().size() == 0) {
                    mData.clear();
                    mRecordAdapter.notifyDataSetChanged();
                    Toast.makeText(getActivity(), "No Deliveries Found for this Month", Toast.LENGTH_SHORT).show();
                } else {
                    mData.addAll(response.getData());
                    mRecordAdapter.addDataToPlansList(mData);
                }
            } else {
                Toast.makeText(getActivity(), "Deliveries Data Not Found", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(getActivity(), "Error from server", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void doInBackground() {

    }

    @Override
    public void onClick(int pPosition, int id) {

        Log.i(TAG, "onClick: ");
    }

}
