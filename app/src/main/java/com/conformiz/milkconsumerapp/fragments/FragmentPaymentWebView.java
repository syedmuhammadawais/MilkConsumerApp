package com.conformiz.milkconsumerapp.fragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.models.response.SaveDataArrayResponse;
import com.conformiz.milkconsumerapp.network.INetworkListener;
import com.conformiz.milkconsumerapp.network.NetworkOperations;
import com.conformiz.milkconsumerapp.utils.Constants;
import com.conformiz.milkconsumerapp.utils.SharedPreferenceUtil;
import com.conformiz.milkconsumerapp.utils.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Set;

import im.delight.android.webview.AdvancedWebView;

public class FragmentPaymentWebView extends Fragment implements AdvancedWebView.Listener, INetworkListener<SaveDataArrayResponse> {

    private AdvancedWebView mWebView;
    private ProgressDialog dialog;


    String amountPaid = "";
    private String callUrl = "";


    String TAG = "Web view";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_webview_payments, container,false);
        mWebView = (AdvancedWebView) view.findViewById(R.id.wv_payments);
        mWebView.setListener(getActivity(), this);
        dialog = new ProgressDialog(getActivity());

        Log.i(TAG, "onCreateView: in ");
        if (Utility.getInstance().isOnline(getActivity())) {

            if (getActivity().getIntent() != null) {
                Log.e("Loading URL: ", "" + Constants.INTERPAY_BASE_URL + Constants.ACTION_POST_CONFIRM_PAYMENT + "?Token=" + getActivity().getIntent().getStringExtra("token"));
                amountPaid = getActivity().getIntent().getStringExtra("amount_paid");
                // mWebView.loadUrl("http://tazafarms.conformiz.com/site/confirmPayment?status_code=1&status_message=success&trans_ref_no=123&order_id=a1&signature=xyz");

                try {
                    URLEncoder.encode(getActivity().getIntent().getStringExtra("token"), "UTF-8");
                    Log.i(TAG, "onCreate: Encode URL " + URLEncoder.encode(getActivity().getIntent().getStringExtra("token"), "UTF-8"));

                    mWebView.loadUrl(Constants.INTERPAY_BASE_URL + Constants.ACTION_POST_CONFIRM_PAYMENT + "?Token=" + URLEncoder.encode(getActivity().getIntent().getStringExtra("token"), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getActivity(), "Could'nt Find Payment Process URL", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "Please Turn On Network", Toast.LENGTH_SHORT).show();
        }

        ((ImageView) view.findViewById(R.id.iv_webview_payments_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // onBackPressed();

                getActivity().onBackPressed();

            }
        });


        return view;
    }


//    @Override
//    protected void onCreateView(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_webview_payments);
//
//        View view = i
//        mWebView = (AdvancedWebView) findViewById(R.id.wv_payments);
//        mWebView.setListener(getActivity(), this);
//        dialog = new ProgressDialog(FragmentPaymentWebView.this);
//
//        if (Utility.getInstance().isOnline(FragmentPaymentWebView.this)) {
//
//            if (getIntent() != null) {
//                Log.e("Loading URL: ", "" + Constants.INTERPAY_BASE_URL + Constants.ACTION_POST_CONFIRM_PAYMENT + "?Token=" + getIntent().getStringExtra("token"));
//                amountPaid = getIntent().getStringExtra("amount_paid");
//                //               mWebView.loadUrl("http://tazafarms.conformiz.com/site/confirmPayment?status_code=1&status_message=success&trans_ref_no=123&order_id=a1&signature=xyz");
//
//                try {
//                    URLEncoder.encode(getIntent().getStringExtra("token"), "UTF-8");
//                    Log.i(TAG, "onCreate: Encode URL " + URLEncoder.encode(getIntent().getStringExtra("token"), "UTF-8"));
//
//                    mWebView.loadUrl(Constants.INTERPAY_BASE_URL + Constants.ACTION_POST_CONFIRM_PAYMENT + "?Token=" + URLEncoder.encode(getIntent().getStringExtra("token"), "UTF-8"));
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                Toast.makeText(FragmentPaymentWebView.this, "Could'nt Find Payment Process URL", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            Toast.makeText(FragmentPaymentWebView.this, "Please Turn On Network", Toast.LENGTH_SHORT).show();
//        }
//
//
//        ((ImageView) findViewById(R.id.iv_webview_payments_back)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
//    }


    @SuppressLint("NewApi")
    @Override
    public void onResume() {
        super.onResume();
        mWebView.onResume();
        // ...
    }

    @SuppressLint("NewApi")
    @Override
    public void onPause() {
        mWebView.onPause();
        // ...
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mWebView.onDestroy();
        // ...
        super.onDestroy();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        mWebView.onActivityResult(requestCode, resultCode, intent);
        // ...
    }

//    @Override
//    public void onBackPressed() {
//
//        showMessageDialog("Are you sure to exit the payment process? ");
//
////        if (!mWebView.onBackPressed()) {
////            Log.e(TAG, "onBackPressed: " );
////        return;
////    }
////
////        Log.e(TAG, "onBackPressed: super");
////        // ...
////        super.onBackPressed();
//    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {
        Log.e(TAG, "onPageStarted: Loading URL:  " + url);
        dialog.setMessage("Loading Payment Process ....");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onPageFinished(String url) {
        Log.e(TAG, "onPageFinished: Loading URL:  " + url);

        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

        if (url.contains("confirmPayment") && !callUrl.contains("confirmPayment")) {

            callUrl = "confirmPayment";
            JSONObject request = new JSONObject();
            Uri myUrl = Uri.parse(url);
            Set<String> paramNames = myUrl.getQueryParameterNames();

            for (String key : paramNames) {
                String value = myUrl.getQueryParameter(key);
                Log.i(TAG, "onPageFinished: " + value + " = " + key);

                try {
                    if (key.contains("status_code")) {
                        request.put("status_code", value);
                    } else if (key.contains("trans_ref_no")) {
                        request.put("trans_ref_no", value);
                    } else if (key.contains("order_id")) {
                        request.put("order_id", value);
                    }

                    request.put("amount_paid", amountPaid);
                    request.put("client_id", SharedPreferenceUtil.getInstance(getActivity()).getClientId());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            mWebView.stopLoading();
            Log.i(TAG, "onPageFinished:");
            NetworkOperations.getInstance().postData(getActivity(), Constants.ACTION_POST_PAYMENT_COMPLETE, request, this, SaveDataArrayResponse.class);
        }

    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {
        Log.i(TAG, "onPageError: ");

    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {
        // some file is available for download
        // either handle the download yourself or use the code below

        Log.e(TAG, "onDownloadRequested: ");
        if (AdvancedWebView.handleDownload(getActivity(), url, suggestedFilename)) {
            // download successfully handled
        } else {
            // download couldn't be handled because user has disabled download manager app on the device
            // TODO show some notice to the user
        }
    }

    @Override
    public void onExternalPageRequest(String url) {
        Log.i(TAG, "onExternalPageRequest: ");
    }

    public void showMessageDialog(String msg) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
        builder.setTitle(msg).setCancelable(false);
        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                callUrl = "";
                dialog.dismiss();
                getActivity().onBackPressed();
                getActivity().onBackPressed();
//                ((MainActivity)getApplicationContext()).onBackPressed();
//                finish();
//                MyFragmentManager.getInstance().popFragment();

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        android.app.AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onPreExecute() {
        dialog.setMessage("Processing Payment ....");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onPostExecute(SaveDataArrayResponse result) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        if (result != null) {
            if (result.getSuccess()) {
                Log.i(TAG, "onPostExecute:  payment done ");
                showMessageDialogOnPaymentProcess("Payment Processed Successfully");
            } else {
                Log.i(TAG, "onPostExecute: payment not done");
                //  showMessageDialogOnPaymentProcess("Payment Process Failed");

                // payment  not excepted
            }
        }
    }

    @Override
    public void doInBackground() {

    }


    public void showMessageDialogOnPaymentProcess(String msg) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
        builder.setTitle(msg).setCancelable(false);
        builder.setIcon(R.drawable.ok_dialog_icon_36);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                //onBackPressed();
                dialog.dismiss();
                getActivity().onBackPressed();
                getActivity().onBackPressed();
              //  finish();
            }
        });
//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });

        android.app.AlertDialog alert = builder.create();
        alert.show();
    }

}
