package com.conformiz.milkconsumerapp.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.conformiz.milkconsumerapp.R;

/**
 * Created by Fahad.Munir on 17-Apr-17.
 */

public class PlanListVH extends RecyclerView.ViewHolder{

    TextView productName;
     TextView productDate;
     TextView productOrderType;
   RelativeLayout row;

    public PlanListVH(View itemView) {
        super(itemView);

        productName = (TextView) itemView.findViewById(R.id.tv_product_plan_name);
        productDate = (TextView) itemView.findViewById(R.id.tv_product_start_date);
        productOrderType = (TextView) itemView.findViewById(R.id.tv_product_special_order);
        row         = (RelativeLayout) itemView.findViewById(R.id.rl_product_plan_row);


    }


    public TextView getProductName(){return productName;}

    public TextView getProductDate(){return productDate;}

    public TextView getProductOrderType(){return productOrderType;}

    public RelativeLayout getRow(){return row;}


}
