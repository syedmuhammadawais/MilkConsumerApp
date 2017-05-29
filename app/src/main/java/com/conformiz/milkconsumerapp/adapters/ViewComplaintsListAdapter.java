package com.conformiz.milkconsumerapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.callbacks.OnItemClick;
import com.conformiz.milkconsumerapp.models.response.ClientPlansRootResponseData;
import com.conformiz.milkconsumerapp.models.response.ViewCustomerComplaintsRootResponseData;
import com.conformiz.milkconsumerapp.utils.Utility;
import com.conformiz.milkconsumerapp.viewholders.PlanListVH;
import com.conformiz.milkconsumerapp.viewholders.ViewClientComplaintsVH;

import java.util.ArrayList;

/**
 * Created by Fahad.Munir on 18-May-17.
 */

public class ViewComplaintsListAdapter extends  RecyclerView.Adapter<ViewClientComplaintsVH> {


    private ArrayList<ViewCustomerComplaintsRootResponseData> mData = new ArrayList<>();
    private OnItemClick mOnItemClick;

    public ViewComplaintsListAdapter(ArrayList<ViewCustomerComplaintsRootResponseData> data, OnItemClick onItemClick) {
        if (data != null && data.size() > 0) {
            mData.clear();
            mData.addAll(data);
        }
        mOnItemClick = onItemClick;
        notifyDataSetChanged();
    }


    public void addDataToPlansList(ArrayList<ViewCustomerComplaintsRootResponseData> data) {
        if (data != null && data.size() > 0) {
            mData.clear();
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

    @Override
    public ViewClientComplaintsVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view_complaints, parent, false);
        return new ViewClientComplaintsVH(view);
    }


    @Override
    public void onBindViewHolder(final ViewClientComplaintsVH holder, final int position) {

        ViewCustomerComplaintsRootResponseData item = mData.get(position);

        holder.getReasonTV().setText(item.getReason());
        String []date = item.getCreated_on().split(" ");
        holder.getDate().setText("Date: " + Utility.getInstance().changeDateFormat(date[0]));
        holder.getStatus().setText("Status: "+item.getStatus_name());

        holder.getRow().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  mOnItemClick.onClick(position, 100);
            }
        });

    }


    @Override
    public int getItemCount() {
        return (mData != null && mData.size() > 0) ? mData.size() : 0;
    }
}
