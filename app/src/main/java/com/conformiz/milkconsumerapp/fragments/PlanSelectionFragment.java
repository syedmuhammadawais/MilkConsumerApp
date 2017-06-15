package com.conformiz.milkconsumerapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.mainfragmentmanager.MyFragmentManager;
import com.conformiz.milkconsumerapp.models.response.AllProductsRootResponseData;
import com.conformiz.milkconsumerapp.utils.Utility;

/**
 * Created by Fahad.Munir on 25-May-17.
 */

public class PlanSelectionFragment extends Fragment implements View.OnClickListener {

    Button createUpdateBT, createSpecialOrderBT, pauseDeliveriesBT;

    AllProductsRootResponseData mSelectedProductData = new AllProductsRootResponseData();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plan_selection, container, false);

        createUpdateBT = (Button) view.findViewById(R.id.btn_update_weekly_plan);
        createSpecialOrderBT = (Button) view.findViewById(R.id.btn_create_one_time_order);
        pauseDeliveriesBT = (Button) view.findViewById(R.id.btn_pause_deliveries);

        ((TextView)view.findViewById(R.id.tv_weekly_product_name)).setText(mSelectedProductData.getProduct_name());
        view.findViewById(R.id.btn_back_plan_selection).setOnClickListener(this);

        createUpdateBT.setOnClickListener(this);
        createSpecialOrderBT.setOnClickListener(this);
        pauseDeliveriesBT.setOnClickListener(this);

        // To show press Effect
        Utility.getInstance().buttonEffect(createUpdateBT, R.color.app_brown_light);
        Utility.getInstance().buttonEffect(createSpecialOrderBT, R.color.app_brown_light);
        Utility.getInstance().buttonEffect(pauseDeliveriesBT, R.color.app_brown_light);

        return view;
    }

    @Override
    public void onClick(View v) {

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        switch (v.getId()) {

            case R.id.btn_update_weekly_plan:
                ProductScheduleFragment productScheduleFragment = new ProductScheduleFragment();
                productScheduleFragment.setSelectedProductData(mSelectedProductData);
                productScheduleFragment.setIsUpdate(1); // 1 for update 0 for new

                MyFragmentManager.getInstance().addFragment(productScheduleFragment);
                transaction.replace(R.id.fragment_container, productScheduleFragment);
                transaction.commit();
                break;

            case R.id.btn_create_one_time_order:
                SpecialOrderFragment specialOrderFragment = new SpecialOrderFragment();
                specialOrderFragment.setSelectedProductData(mSelectedProductData);
                MyFragmentManager.getInstance().addFragment(specialOrderFragment);
                transaction.replace(R.id.fragment_container, specialOrderFragment).commit();
                break;

            case R.id.btn_pause_deliveries:
                PauseDeliveriesFragment pauseDeliveriesFragment = new PauseDeliveriesFragment();
                pauseDeliveriesFragment.setSelectedProductData(mSelectedProductData);
                MyFragmentManager.getInstance().addFragment(pauseDeliveriesFragment);
                transaction.replace(R.id.fragment_container,pauseDeliveriesFragment).commit();
                break;

            case R.id.btn_back_plan_selection:
                getActivity().onBackPressed();
                break;
        }
    }

    public void setSelectedProductData(AllProductsRootResponseData mSelectedProductData) {
        this.mSelectedProductData = mSelectedProductData;
    }
}
