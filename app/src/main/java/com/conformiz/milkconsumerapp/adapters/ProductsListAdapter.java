package com.conformiz.milkconsumerapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.callbacks.OnItemClick;
import com.conformiz.milkconsumerapp.models.response.AllProductsRootResponseData;
import com.conformiz.milkconsumerapp.utils.Constants;
import com.conformiz.milkconsumerapp.viewholders.ProductsViewHolder;

import java.util.ArrayList;

/**
 * Created by Fahad.Munir on 13-Apr-17.
 */

public class ProductsListAdapter extends RecyclerView.Adapter<ProductsViewHolder> implements Constants{

    private ArrayList<AllProductsRootResponseData> mProductsDataList = new ArrayList<>();
    private OnItemClick mOnItemClick;


    public ProductsListAdapter(ArrayList<AllProductsRootResponseData> productsDataList, OnItemClick onItemClick) {

        if (productsDataList != null && productsDataList.size() > 0) {
            mProductsDataList.clear();
            mProductsDataList.addAll(productsDataList);
        }
        mOnItemClick = onItemClick;
        this.notifyDataSetChanged();
    }


    public void addProductsToList(ArrayList<AllProductsRootResponseData> productsDataList) {

        if (productsDataList != null && productsDataList.size() > 0) {
            mProductsDataList.clear();
            mProductsDataList.addAll(productsDataList);
        }
        this.notifyDataSetChanged();
    }

    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product_view, parent, false);

        return new ProductsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ProductsViewHolder holder, final int position) {

        final AllProductsRootResponseData data = mProductsDataList.get(position);
        holder.getProductName().setText(data.getProduct_name());
        holder.getRowLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClick.onClick(position, ON_CLICK_PRODUCT);
            }
        });

        holder.getProductInfo().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClick.onClick(position, ON_CLICK_PRODUCT_INFO);
            }
        });

    }


    @Override
    public int getItemCount() {
        return mProductsDataList != null ? mProductsDataList.size() : 0;
    }
}
