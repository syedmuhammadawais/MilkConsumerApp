package com.conformiz.milkconsumerapp.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;

import com.conformiz.milkconsumerapp.R;

/**
 * Created by Fahad.Munir on 19-Jun-17.
 */

public class ManageOrderInstructionsFragment extends DialogFragment implements View.OnClickListener {


    CheckBox dontShowAgainCB;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dont_show_again_dilaog_check_box, container, false);
        dontShowAgainCB = (CheckBox) view.findViewById(R.id.cb_skip);
//
//        WebView myWebView = (WebView)view.findViewById(R.id.wv_manage_orders_instructions);
//        myWebView.loadUrl("file:///android_asset/manage_order_instructions.html");
//        myWebView.setLayerType(LAYER_TYPE_SOFTWARE,null);


       // view.findViewById(R.id.btn_ok_mange_order_dialog).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btn_ok_mange_order_dialog:
//
//                if (dontShowAgainCB.isChecked()) {
//                    SharedPreferenceUtil.getInstance(getActivity()).setCheckBoxValue(false);
//                } else {
//                    SharedPreferenceUtil.getInstance(getActivity()).setCheckBoxValue(true);
//                }
//
//                getActivity().onBackPressed();
//                break;
//        }
    }




    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

}
