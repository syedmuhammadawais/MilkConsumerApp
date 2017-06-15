package com.conformiz.milkconsumerapp.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.activities.MainActivity;
import com.conformiz.milkconsumerapp.callbacks.OnItemClick;
import com.conformiz.milkconsumerapp.utils.SharedPreferenceUtil;
import com.conformiz.milkconsumerapp.utils.Utility;

/**
 * Created by Fahad.Munir on 11-Apr-17.
 */

public class DashboardFragment extends Fragment implements View.OnClickListener {

    OnItemClick mClick;
    CheckBox dontShowAgainCB;

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
        complaintsRL = (ImageView) view.findViewById(R.id.iv_complaints);
        deliveredRL = (ImageView) view.findViewById(R.id.iv_deliveries_history);
        manageOrderRL = (ImageView) view.findViewById(R.id.iv_manager_order);
        settingRL = (ImageView) view.findViewById(R.id.iv_settings);
        paymentsRL = (ImageView) view.findViewById(R.id.iv_payments);
        howToPayIV = (ImageView) view.findViewById(R.id.iv_how_to_pay);

        complaintsRL.setOnClickListener(this);
        deliveredRL.setOnClickListener(this);
        manageOrderRL.setOnClickListener(this);
        settingRL.setOnClickListener(this);
        paymentsRL.setOnClickListener(this);
        howToPayIV.setOnClickListener(this);


        Utility.getInstance().imageViewClickEffect(complaintsRL, R.color.app_brown_light);
        Utility.getInstance().imageViewClickEffect(deliveredRL, R.color.app_brown_light);
        Utility.getInstance().imageViewClickEffect(manageOrderRL, R.color.app_brown_light);
        Utility.getInstance().imageViewClickEffect(settingRL, R.color.app_brown_light);
        Utility.getInstance().imageViewClickEffect(paymentsRL, R.color.app_brown_light);
        Utility.getInstance().imageViewClickEffect(howToPayIV, R.color.app_brown_light);


        String label = "Powered By <font color=#FFB30C>Conformiz</font>";
        TextView poweredBy = (TextView) view.findViewById(R.id.tv_powered_by);
        poweredBy.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            poweredBy.setText(Html.fromHtml(label, Html.FROM_HTML_OPTION_USE_CSS_COLORS));
        } else {
            poweredBy.setText(Html.fromHtml(label));
        }

        initManageOrderDialogUI();

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

        }

        if ((v.getId() == R.id.iv_manager_order || v.getId() == R.id.rl_manage_order)
                && SharedPreferenceUtil.getInstance(getActivity()).getCheckBoxValue()) {
            showMangeOrderDialogInfo(v.getId());
        } else {
            mClick.onClick(v.getId(), -1);
        }
    }


    public void initManageOrderDialogUI(){



    }

    public void showMangeOrderDialogInfo(final int id) {
        View dialogLayout;
        LayoutInflater dialogInflater;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        dialogInflater = LayoutInflater.from(getActivity());
        dialogLayout = dialogInflater.inflate(R.layout.dont_show_again_dilaog_check_box, null);
        dontShowAgainCB = (CheckBox) dialogLayout.findViewById(R.id.cb_skip);
//        WebView myWebView = (WebView)dialogLayout.findViewById(R.id.wv_manage_orders_instructions);
//        myWebView.loadUrl("file:///android_asset/manage_order_instructions.html");
        alertDialogBuilder.setView(dialogLayout);
        alertDialogBuilder.setIcon(R.drawable.product_info);
        alertDialogBuilder.setTitle("Manage Orders");

        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                if (dontShowAgainCB.isChecked()) {
                    SharedPreferenceUtil.getInstance(getActivity()).setCheckBoxValue(false);
                }
                dialog.cancel();
                mClick.onClick(id, -1);

            }
        });

        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                if (!dontShowAgainCB.isChecked()) {
                    SharedPreferenceUtil.getInstance(getActivity()).setCheckBoxValue(true);
                }                dialog.cancel();
            }
        });

        alertDialogBuilder.show();

    }




}

