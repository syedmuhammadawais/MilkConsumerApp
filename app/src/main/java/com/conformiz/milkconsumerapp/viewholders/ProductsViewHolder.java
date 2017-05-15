package com.conformiz.milkconsumerapp.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.conformiz.milkconsumerapp.R;

/**
 * Created by Fahad.Munir on 13-Apr-17.
 */

public class ProductsViewHolder extends RecyclerView.ViewHolder {

    private TextView productName;
    private ImageView productInfo;
    private RelativeLayout rowLayout;


    public ProductsViewHolder(View itemView) {
        super(itemView);
        productName = (TextView) itemView.findViewById(R.id.tv_product_name);
        productInfo =(ImageView) itemView.findViewById(R.id.imageView_product_info);
        rowLayout = (RelativeLayout) itemView.findViewById(R.id.rl_product_row_item);

    }

    public TextView getProductName(){return productName;}

    public RelativeLayout getRowLayout(){return rowLayout;}

    public ImageView getProductInfo() {return productInfo;}
}
