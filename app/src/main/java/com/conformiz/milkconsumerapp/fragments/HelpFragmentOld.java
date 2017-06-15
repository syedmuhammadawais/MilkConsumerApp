package com.conformiz.milkconsumerapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import com.conformiz.milkconsumerapp.R;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

/**
 * Created by Fahad.Munir on 13-Jun-17.
 */

public class HelpFragmentOld extends Fragment implements View.OnClickListener{


    ExpandableRelativeLayout expandableLayout1, expandableLayout2, expandableLayout3, expandableLayout4;
    Button expandBtn1, expandBtn2, expandBtn3, expandBtn4;
    WebView webView1, webView2, webView3,webView4;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.help_fragment_old,container,false);

        Log.i("Faqs", "onCreateView: ");
        expandableLayout1 = (ExpandableRelativeLayout) view.findViewById(R.id.expandableLayout1);
        expandableLayout2 = (ExpandableRelativeLayout) view.findViewById(R.id.expandableLayout2);
        expandableLayout3 = (ExpandableRelativeLayout) view.findViewById(R.id.expandableLayout3);
        expandableLayout4 = (ExpandableRelativeLayout) view.findViewById(R.id.expandableLayout4);

        expandableLayout1.collapse();
        expandableLayout2.collapse();
        expandableLayout3.collapse();
        expandableLayout4.collapse();

        expandableLayout1.toggle();

        expandBtn1 = (Button) view.findViewById(R.id.expandableButton1);
        expandBtn2 = (Button) view.findViewById(R.id.expandableButton2);
        expandBtn3 = (Button) view.findViewById(R.id.expandableButton3);
        expandBtn4 = (Button) view.findViewById(R.id.expandableButton4);


        expandBtn1.setOnClickListener(this);
        expandBtn2.setOnClickListener(this);
        expandBtn3.setOnClickListener(this);
        expandBtn4.setOnClickListener(this);

        view.findViewById(R.id.btn_back_faqs).setOnClickListener(this);


        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){



            case R.id.expandableButton1:
                webView1 = (WebView) getView().findViewById(R.id.wv_faq_how_to_pay);
                webView1.loadUrl("file:///android_asset/how_to_pay.html");
                if(expandableLayout1.isExpanded()){
                    expandableLayout1.collapse();
                }else {
                    expandableLayout1.expand();
                } // toggle expand and collapse
                break;


            case R.id.expandableButton2:
//                expandableLayout2 = (ExpandableRelativeLayout) getView().findViewById(R.id.expandableLayout2);
//                webView2 = (WebView) getView().findViewById(R.id.wv_faq_how_to_pay);
//                webView2.loadUrl("file:///android_asset/how_to_pay.html");
                expandableLayout2.toggle(); // toggle expand and collapse
                break;


            case R.id.expandableButton3:
                expandableLayout3.toggle(); // toggle expand and collapse
                break;


            case R.id.expandableButton4:
                expandableLayout4.toggle(); // toggle expand and collapse
                break;


            case R.id.btn_back_faqs:
                getActivity().onBackPressed();
                break;


        }
    }
}
