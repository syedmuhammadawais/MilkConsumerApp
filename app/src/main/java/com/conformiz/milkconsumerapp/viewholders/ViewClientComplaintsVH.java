package com.conformiz.milkconsumerapp.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.conformiz.milkconsumerapp.R;

/**
 * Created by Fahad.Munir on 18-May-17.
 */

public class ViewClientComplaintsVH extends RecyclerView.ViewHolder {

    private TextView reasonTV;
    private TextView date;
    private TextView status;
    private RelativeLayout row;



    public ViewClientComplaintsVH(View itemView) {
        super(itemView);

        reasonTV = (TextView) itemView.findViewById(R.id.tv_complaint_reason_name);
        date = (TextView) itemView.findViewById(R.id.tv_complaint_view_start_date);
        status = (TextView) itemView.findViewById(R.id.tv_complaint_view_status);
        row = (RelativeLayout) itemView.findViewById(R.id.rl_complaints_plan_row);



    }


    public TextView getReasonTV() {
        return reasonTV;
    }

    public TextView getDate() {
        return date;
    }

    public TextView getStatus() {
        return status;
    }

    public RelativeLayout getRow() {
        return row;
    }
}
