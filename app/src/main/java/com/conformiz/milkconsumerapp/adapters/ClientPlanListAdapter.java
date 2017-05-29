package com.conformiz.milkconsumerapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.callbacks.OnItemClick;
import com.conformiz.milkconsumerapp.models.response.ClientPlansRootResponseData;
import com.conformiz.milkconsumerapp.utils.Utility;
import com.conformiz.milkconsumerapp.viewholders.PlanListVH;

import java.util.ArrayList;

/**
 * Created by Fahad.Munir on 17-Apr-17.
 */

public class ClientPlanListAdapter extends RecyclerView.Adapter<PlanListVH> {


    private ArrayList<ClientPlansRootResponseData> mData = new ArrayList<>();
    private OnItemClick mOnItemClick;

    public ClientPlanListAdapter(ArrayList<ClientPlansRootResponseData> data, OnItemClick onItemClick) {
        if (data != null && data.size() > 0) {
            mData.clear();
            mData.addAll(data);
        }
        mOnItemClick = onItemClick;
        notifyDataSetChanged();
    }


    public void addDataToPlansList(ArrayList<ClientPlansRootResponseData> data) {
        if (data != null && data.size() > 0) {
            mData.clear();
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

    @Override
    public PlanListVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product_plan, parent, false);
        return new PlanListVH(view);
    }


    @Override
    public void onBindViewHolder(final PlanListVH holder, final int position) {

        ClientPlansRootResponseData item = mData.get(position);


        if (item.getOrder_type().contains("s") && item.getOrder_type().equalsIgnoreCase("spacial") || item.getOrder_type().equalsIgnoreCase("special")) {
//            holder.getProductOrderType().setVisibility(View.VISIBLE);
            holder.getProductName().setText(item.getProduct_name() + " (Special)");
            holder.getProductDate().setText("From:  " + Utility.getInstance().changeDateFormat(item.getStart_date()) +
                    "   To:  " + Utility.getInstance().changeDateFormat(item.getEnd_date())
            );
        } else if (item.getOrder_type().equalsIgnoreCase("regular")) {
            if (!item.getIs_halt().equalsIgnoreCase("0")) {
                holder.getProductName().setText(item.getProduct_name() + " (Stopped)");
            } else {
                holder.getProductName().setText(item.getProduct_name() + " (Regular)");
            }
            holder.getProductDate().setText("Plan Start Date: " + Utility.getInstance().changeDateFormat(item.getStart_date()));
            holder.getProductOrderType().setVisibility(View.INVISIBLE);
        }

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
