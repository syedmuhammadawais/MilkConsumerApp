package com.conformiz.milkconsumerapp.fragments.paymentfragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.activities.PaymentWebViewActivity;
import com.conformiz.milkconsumerapp.callbacks.CallBackToCloseWindow;
import com.conformiz.milkconsumerapp.models.response.CustomerBalanceRootResponse;
import com.conformiz.milkconsumerapp.models.response.PaymentProcessResponse;
import com.conformiz.milkconsumerapp.network.INetworkListener;
import com.conformiz.milkconsumerapp.network.NetworkOperations;
import com.conformiz.milkconsumerapp.utils.Constants;
import com.conformiz.milkconsumerapp.utils.SharedPreferenceUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by Fahad.Munir on 16-May-17.
 */

public class PaymentFragment extends Fragment implements View.OnClickListener, INetworkListener, CallBackToCloseWindow {

    ProgressDialog dialog;
    Button payNowToProceedBT;
    TextView currentBalanceTV;
    EditText amountET;

    String dialogMsg = "";
    Boolean isValidate = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment, container, false);


        payNowToProceedBT = (Button) view.findViewById(R.id.btn_pay_now);
        currentBalanceTV = (TextView) view.findViewById(R.id.tv_current_balance);

        view.findViewById(R.id.btn_back_payments).setOnClickListener(this);
        view.findViewById(R.id.btn_pay_now).setOnClickListener(this);

        dialog = new ProgressDialog(getActivity());

        JSONObject request = new JSONObject();
        try {
            request.put("client_id", SharedPreferenceUtil.getInstance(getActivity()).getClientId() + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        dialogMsg = "Loading Customer Balance...";
        NetworkOperations.getInstance().postData(getActivity(), Constants.ACTION_POST_CUSTOMER_ACCOUNT_BALANCE, request, this, CustomerBalanceRootResponse.class);

        return view;
    }

    @Override
    public void onPreExecute() {
        dialog.setMessage(dialogMsg);
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onPostExecute(Object result) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

        if (result != null && result instanceof CustomerBalanceRootResponse) {
            CustomerBalanceRootResponse response = (CustomerBalanceRootResponse) result;

            if (response.getSuccess()) {
                currentBalanceTV.setText(response.getData() + " Rupees");
            } else {
                Toast.makeText(getActivity(), "Current Balance Not Found", Toast.LENGTH_SHORT).show();
            }
        }


        // Edit this program
        if (result != null && result instanceof PaymentProcessResponse) {
            PaymentProcessResponse response = (PaymentProcessResponse) result;
            if (response.getStatus_code().equalsIgnoreCase("1")) {

//                FragmentPaymentWebView fragmentPaymentWebView = new FragmentPaymentWebView();
//                getActivity().getIntent().putExtra("token", response.getToken());
//                getActivity().getIntent().putExtra("amount_paid", amountET.getText().toString());
//                MyFragmentManager.getInstance().addFragment(fragmentPaymentWebView);
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.fragment_container, fragmentPaymentWebView).commit();

                Intent intent = new Intent(getActivity(),PaymentWebViewActivity.class);
                intent.putExtra("token",response.getToken());
                intent.putExtra("amount_paid",amountET.getText().toString());
                startActivityForResult(intent,1);

            } else {
                Toast.makeText(getActivity(), "" + response.getStatus_message(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void doInBackground() {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_back_payments:
                getActivity().onBackPressed();
                break;

            case R.id.btn_pay_now:

                if (payNowToProceedBT.getText().toString().contains("Pay")) {
                    getView().findViewById(R.id.et_enter_amount).setVisibility(View.VISIBLE);
                    amountET = (EditText) getView().findViewById(R.id.et_enter_amount);
                    payNowToProceedBT.setText("Proceed");
                } else {
                    if (checkValidation()) {

                        Log.i("on ", "onClick: Payment Fragment btn pay now");
                        JSONObject request = new JSONObject();
                        try {
                            SharedPreferenceUtil sharedPreferenceUtil = SharedPreferenceUtil.getInstance(getActivity());
                            request.put("app_id", "7453014062");
                            request.put("app_key", "31397653");
                            request.put("name", sharedPreferenceUtil.getClientFullName());
                            request.put("mobile", sharedPreferenceUtil.getClientContact());
                            request.put("email", sharedPreferenceUtil.getClientEmail());
                            request.put("Order_id", new Date().getTime() + "");
                            request.put("currency", "PKR");
                            request.put("Amount", amountET.getText().toString());
                            request.put("order_desc", "1 Kg Milk");

                            dialogMsg = "Processing Payment...";
                            NetworkOperations.getInstance().postData(getActivity(), Constants.INTERPAY_BASE_URL + Constants.ACTION_POST_PROCESS_PAYMENT, request, this, PaymentProcessResponse.class);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getActivity().onBackPressed();
    }

    public boolean checkValidation() {
        isValidate = false;
        if (TextUtils.isEmpty(amountET.getText().toString())) {
            amountET.setError("Please Enter Amount");
        } else {
            isValidate = true;
        }
//        else if(Integer.parseInt(currentBalanceTV.getText().toString()) < Integer.parseInt(amountET.getText().toString())){
//            amountET.setError("Amount can'nt be greate than current balance");
//        }
        return isValidate;
    }

    @Override
    public void closeWindow() {

    }
}
