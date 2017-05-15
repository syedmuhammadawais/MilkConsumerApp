package com.conformiz.milkconsumerapp.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.conformiz.milkconsumerapp.R;

/**
 * Created by Fahad.Munir on 10-May-17.
 */

public class DeliveriesRecordVH extends RecyclerView.ViewHolder {

    private TextView productName;
    private TextView productDate;
    private TextView productQuantity;
    private TextView productPrice;

    private RelativeLayout row;

    public DeliveriesRecordVH(View itemView) {
        super(itemView);

        productName = (TextView) itemView.findViewById(R.id.tv_product_plan_name_deliveries_record);
        productDate = (TextView) itemView.findViewById(R.id.tv_product_start_date_deliveries_record);
        productQuantity = (TextView) itemView.findViewById(R.id.tv_product_quantity_deliveries_record);
        productPrice = (TextView) itemView.findViewById(R.id.tv_product_price_deliveries_record);
        row = (RelativeLayout) itemView.findViewById(R.id.rl_product_plan_row_deliveries_record);

    }


    public TextView getProductName() {
        return productName;
    }

    public TextView getProductDate() {
        return productDate;
    }

    public RelativeLayout getRow() {
        return row;
    }

    public TextView getProductQuantity() {
        return productQuantity;
    }

    public TextView getProductPrice() {
        return productPrice;
    }
}
