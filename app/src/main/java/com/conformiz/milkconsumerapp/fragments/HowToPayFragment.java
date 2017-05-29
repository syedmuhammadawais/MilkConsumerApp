package com.conformiz.milkconsumerapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.conformiz.milkconsumerapp.R;

/**
 * Created by Fahad.Munir on 24-May-17.
 */

public class HowToPayFragment extends Fragment implements View.OnClickListener {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_how_to_pay,container,false);
        view.findViewById(R.id.btn_back_how_to_pay).setOnClickListener(this);

        WebView myWebView = (WebView)view.findViewById(R.id.wv_how_to_pay);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl("file:///android_asset/how_to_pay.html");
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back_how_to_pay:
                getActivity().onBackPressed();
                break;

        }
    }
}
