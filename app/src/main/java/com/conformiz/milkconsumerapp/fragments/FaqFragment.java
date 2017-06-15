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
 * Created by Fahad.Munir on 13-Jun-17.
 */

public class FaqFragment extends Fragment implements View.OnClickListener{

    WebView myWebView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faq,container,false);

        myWebView = (WebView)view.findViewById(R.id.wv_faqs);
        myWebView.loadUrl("file:///android_asset/faqs.html");

        view.findViewById(R.id.btn_back_faq).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_back_faq:
                getActivity().onBackPressed();
                break;
        }
    }
}
