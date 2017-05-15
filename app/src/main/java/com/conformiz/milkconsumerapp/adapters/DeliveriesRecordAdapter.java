package com.conformiz.milkconsumerapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.callbacks.OnItemClick;
import com.conformiz.milkconsumerapp.models.response.DeliveriesRecordRootResponse;
import com.conformiz.milkconsumerapp.models.response.DeliveriesRecordRootResponseData;
import com.conformiz.milkconsumerapp.viewholders.DeliveriesRecordVH;

import java.util.ArrayList;

/**
 * Created by Fahad.Munir on 10-May-17.
 */

public class DeliveriesRecordAdapter extends RecyclerView.Adapter<DeliveriesRecordVH> {

    ArrayList<DeliveriesRecordRootResponseData> mData = new ArrayList<>();
    private OnItemClick mOnItemClick;

    public DeliveriesRecordAdapter(ArrayList<DeliveriesRecordRootResponseData> data, OnItemClick onItemClick) {
        if (data != null && data.size() > 0) {
            mData.clear();
            mData.addAll(data);
        }
        mOnItemClick = onItemClick;
        notifyDataSetChanged();
    }

    public void addDataToPlansList(ArrayList<DeliveriesRecordRootResponseData> data){
        if (data != null && data.size() > 0) {
            mData.clear();
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }


    @Override
    public DeliveriesRecordVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_deliveries_record, parent, false);
        return new DeliveriesRecordVH(view);
    }

    @Override
    public void onBindViewHolder(DeliveriesRecordVH holder, final int position) {
        DeliveriesRecordRootResponseData item = mData.get(position);

        holder.getProductName().setText(item.getProduct_name());
        holder.getProductDate().setText("Plan Date:  "+item.getDate());
        holder.getProductQuantity().setText("Quantity:  "+item.getQuantity()+" "+item.getUnit());
        holder.getProductPrice().setText("Price:  "+item.getAmount());

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
