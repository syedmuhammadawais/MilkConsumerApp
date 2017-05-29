package com.conformiz.milkconsumerapp.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.activities.MainActivity;
import com.conformiz.milkconsumerapp.callbacks.OnItemClick;
import com.conformiz.milkconsumerapp.network.INetworkListener;
import com.conformiz.milkconsumerapp.utils.Utility;

import java.util.Observable;

/**
 * Created by Fahad.Munir on 11-Apr-17.
 */

public class DashboardFragment extends Fragment implements View.OnClickListener, INetworkListener {

    OnItemClick mClick;

    ImageView complaintsRL, deliveredRL, manageOrderRL, settingRL, paymentsRL, howToPayIV;

    public DashboardFragment() {
    }

    public void setOnItemClick(OnItemClick pItemClick) {
        mClick = pItemClick;
    }


    // dashboard icons 3X3
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        //view.findViewById(R.id.btn_back_main_menu);
        complaintsRL  = (ImageView) view.findViewById(R.id.iv_complaints);
        deliveredRL   = (ImageView) view.findViewById(R.id.iv_deliveries_history);
        manageOrderRL = (ImageView) view.findViewById(R.id.iv_manager_order);
        settingRL     = (ImageView) view.findViewById(R.id.iv_settings);
        paymentsRL    = (ImageView) view.findViewById(R.id.iv_payments);
        howToPayIV    = (ImageView) view.findViewById(R.id.iv_how_to_pay);

        complaintsRL.setOnClickListener(this);
        deliveredRL.setOnClickListener(this);
        manageOrderRL.setOnClickListener(this);
        settingRL.setOnClickListener(this);
        paymentsRL.setOnClickListener(this);
        howToPayIV.setOnClickListener(this);


        Utility.getInstance().imageViewClickEffect(complaintsRL,R.color.app_brown_light);
        Utility.getInstance().imageViewClickEffect(deliveredRL,R.color.app_brown_light);
        Utility.getInstance().imageViewClickEffect(manageOrderRL,R.color.app_brown_light);
        Utility.getInstance().imageViewClickEffect(settingRL,R.color.app_brown_light);
        Utility.getInstance().imageViewClickEffect(paymentsRL,R.color.app_brown_light);
        Utility.getInstance().imageViewClickEffect(howToPayIV,R.color.app_brown_light);


        String label = "Powered By <font color=#FFB30C>Conformiz</font>";
        TextView poweredBy = (TextView) view.findViewById(R.id.tv_powered_by);
        poweredBy.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            poweredBy.setText(Html.fromHtml(label, Html.FROM_HTML_OPTION_USE_CSS_COLORS));
        } else {
            poweredBy.setText(Html.fromHtml(label));
        }


//        if (SharedPreferenceUtil.getInstance(getActivity()).getEnrolled()) {
//            view.findViewById(R.id.rl_dashboard_messages).setOnClickListener(this);
//        } else {
//            view.findViewById(R.id.rl_dashboard_messages).setVisibility(View.INVISIBLE);
//        }
//
//        messageCount = (TextView) view.findViewById(R.id.tv_unread_count);
//
//        // New layout
//        view.findViewById(R.id.rl_more_supplies_new).setOnClickListener(this);
//        view.findViewById(R.id.rl_more_assessments_new).setOnClickListener(this);
//        view.findViewById(R.id.rl_more_notes).setOnClickListener(this);
//        view.findViewById(R.id.rl_more_cp).setOnClickListener(this);
//        view.findViewById(R.id.rl_more_ct).setOnClickListener(this);
//        view.findViewById(R.id.rl_more_insurance_new).setOnClickListener(this);
//
//        SharedPreferenceUtil mSharedPreferenceUtil = SharedPreferenceUtil.getInstance(getActivity());
//        String userFName = mSharedPreferenceUtil.getPatientFName() + "";
//        String userLName = mSharedPreferenceUtil.getPatientLName() + "";
//
//        ((TextView) view.findViewById(R.id.tv_dashboard_username)).setText(userFName + " " + userLName);


//        monthTV = (TextView) view.findViewById(R.id.tv_dashboard_month__);
//        dayTV = (TextView) view.findViewById(R.id.tv_dashboard_day_temp_4);
//        viewAllTV = (TextView) view.findViewById(R.id.tv_dashboard_view_all);
//        arrowIV = (ImageView) view.findViewById(R.id.iv_dashboard_arrow_right);
//
//        viewAllTV.setOnClickListener(this);
//        arrowIV.setOnClickListener(this);
//
//        DateFormat dateFormat = new SimpleDateFormat("MMM");
//        Date date = new Date();
//        monthTV.setText(dateFormat.format(date));
//        dateFormat = new SimpleDateFormat("dd");
//        dayTV.setText(dateFormat.format(date));
//        appointmentsRV = (RecyclerView) view.findViewById(R.id.rv_appointments);
//        appointmentsRV.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//        appointmentsAdaptor = new AppointmentsAdaptor(data, R.layout.temp_10);
//        appointmentsRV.setAdapter(appointmentsAdaptor);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_back_main_menu:
                ((MainActivity) getActivity()).onBackPressed();
                break;

            case R.id.tv_powered_by:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://conformiz.com"));
                startActivity(intent);
                break;
//            case R.id.rl_dashboard_messages:
//
////                getActivity().runOnUiThread(new Runnable() {
////                    @Override
////                    public void run() {
////                        messageCount.setVisibility(View.INVISIBLE);
////                    }
////                });
//
//                new Handler(Looper.getMainLooper()).post(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.d("UI thread", "I am the UI thread");
//                        messageCount.setVisibility(View.INVISIBLE);
//
//                    }
//                });
        }
        mClick.onClick(v.getId(), -1);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //    String key = SharedPreferenceUtil.getInstance(getActivity()).getPatientKey();
        //  NetworkOperations.getInstance().getAppointments(getActivity(), key, this);
    }

    @Override
    public void onPreExecute() {

    }

    @Override
    public void onPostExecute(Object result) {
//        if (result != null && result.isSuccess() && result.getData() != null) {
//            SimpleDateFormat currentDate = new SimpleDateFormat("yyyy-MM-dd");
//            Date today = new Date();
//            String todayStr = currentDate.format(today);
//            data.clear();
//
//            for (int i = 0; i < result.getData().size(); i++) {
//                AppointmentEntity entity = result.getData().get(i);
//                if (todayStr.equalsIgnoreCase(entity.getDate())) {
//                    data.add(entity);
//                }
//            }
//            if (data.size() > 0)
//                appointmentsRV.setVisibility(View.VISIBLE);
//            appointmentsAdaptor.notifyDataSetChanged();
        //       }
    }

    @Override
    public void doInBackground() {

    }

    /**
     * This method is called if the specified {@code Observable} object's
     * {@code notifyObservers} method is called (because the {@code Observable}
     * object has been updated.
     *
     * @param observable the {@link Observable} object.
     * @param data       the data passed to {@link Observable#notifyObservers(Object)}.
     */
//    @Override
//    public void update(Observable observable, Object data) {
//        Log.i("in", "update");
//        new Handler(Looper.getMainLooper()).post(new Runnable() {
//            @Override
//            public void run() {
//                Log.d("UI thread", "I am the UI thread");
//
//                messageCount.setVisibility(View.VISIBLE);
//
//            }
//        });
//
////        getActivity().runOnUiThread(new Runnable() {
////            @Override
////            public void run() {
////                messageCount.setVisibility(View.VISIBLE);
////            }
////        });
//
//
//    }
}

