package com.conformiz.milkconsumerapp.fragments.paymentfragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.models.response.CustomerBalanceRootResponse;
import com.conformiz.milkconsumerapp.network.INetworkListener;
import com.conformiz.milkconsumerapp.network.NetworkOperations;
import com.conformiz.milkconsumerapp.utils.Constants;
import com.conformiz.milkconsumerapp.utils.SharedPreferenceUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Fahad.Munir on 16-May-17.
 */

public class PaymentFragment extends Fragment implements View.OnClickListener, INetworkListener{

    ProgressDialog dialog;
    Button payNowToProceedBT;
    TextView currentBalanceTV;
    EditText amountET;

    Boolean isValidate = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment,container,false);


        payNowToProceedBT = (Button) view.findViewById(R.id.btn_pay_now);
        currentBalanceTV = (TextView) view.findViewById(R.id.tv_current_balance);

        view.findViewById(R.id.btn_back_payments).setOnClickListener(this);

        dialog = new ProgressDialog(getActivity());

        JSONObject request = new JSONObject();
        try {
            request.put("client_id", SharedPreferenceUtil.getInstance(getActivity()).getClientId()+"");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        NetworkOperations.getInstance().postData(getActivity(), Constants.ACTION_POST_CUSTOMER_ACCOUNT_BALANCE,request,this, CustomerBalanceRootResponse.class);

        return view;
    }

    @Override
    public void onPreExecute() {
        dialog.setMessage("Loading Customer Balance...");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onPostExecute(Object result) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

        if(result!= null && result instanceof CustomerBalanceRootResponse){
            CustomerBalanceRootResponse response = (CustomerBalanceRootResponse) result;

            if(response.getSuccess()){
                currentBalanceTV.setText(response.getData()+" Rupees");
            }
        }

    }

    @Override
    public void doInBackground() {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_back_payments:
                getActivity().onBackPressed();
                break;

            case R.id.btn_pay_now:

                if(payNowToProceedBT.getText().toString().contains("Pay")) {
                    getView().findViewById(R.id.et_enter_amount).setVisibility(View.VISIBLE);
                    amountET = (EditText) getView().findViewById(R.id.et_enter_amount);
                    payNowToProceedBT.setText("Proceed");
                }else {
                    if(checkValidation()){

                    }
                }

        }
    }


    public boolean checkValidation(){
        isValidate = false;
        if(TextUtils.isEmpty(amountET.getText().toString())){
            amountET.setError("Please Enter Amount");
        } else {
            isValidate = true;
        }
//        else if(Integer.parseInt(currentBalanceTV.getText().toString()) < Integer.parseInt(amountET.getText().toString())){
//            amountET.setError("Amount can'nt be greate than current balance");
//        }
        return isValidate;
    }
}
