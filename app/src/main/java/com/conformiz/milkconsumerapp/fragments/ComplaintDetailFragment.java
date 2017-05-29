package com.conformiz.milkconsumerapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.models.response.ViewCustomerComplaintsRootResponseData;
import com.conformiz.milkconsumerapp.utils.Utility;

/**
 * Created by Fahad.Munir on 19-May-17.
 */

public class ComplaintDetailFragment extends Fragment implements View.OnClickListener {


    ViewCustomerComplaintsRootResponseData mData = new ViewCustomerComplaintsRootResponseData();


    public void setData(ViewCustomerComplaintsRootResponseData data) {
        mData = data;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_complaint_detail, container, false);

        ((TextView) view.findViewById(R.id.tv_complaint_detail_date)).setText(Utility.getInstance().changeDateFormat(mData.getCreated_on()));
        ((TextView) view.findViewById(R.id.tv_complaint_detail_status)).setText(mData.getStatus_name());
        ((TextView) view.findViewById(R.id.tv_complaint_detail_reason)).setText(mData.getReason());
        ((TextView) view.findViewById(R.id.tv_complaint_detail_description)).setText(mData.getQuery_text());
        ((TextView) view.findViewById(R.id.tv_complaint_detail_response)).setText(mData.getResponse());

        view.findViewById(R.id.btn_back_complaint_detail).setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back_complaint_detail:
                getActivity().onBackPressed();
                break;
        }
    }
}
