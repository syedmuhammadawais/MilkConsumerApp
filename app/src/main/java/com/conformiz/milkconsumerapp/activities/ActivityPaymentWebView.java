package com.conformiz.milkconsumerapp.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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

public class ActivityPaymentWebView extends AppCompatActivity implements AdvancedWebView.Listener, INetworkListener<SaveDataArrayResponse> {

    private AdvancedWebView mWebView;
    private ProgressDialog dialog;


    String amountPaid = "";

    String TAG = "Web view";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_payments);

        mWebView = (AdvancedWebView) findViewById(R.id.wv_payments);
        mWebView.setListener(this, this);
        dialog = new ProgressDialog(ActivityPaymentWebView.this);

        if (Utility.getInstance().isOnline(ActivityPaymentWebView.this)) {

            if (getIntent() != null) {
                Log.e("Loading URL: ", "" + Constants.INTERPAY_BASE_URL + Constants.ACTION_POST_CONFIRM_PAYMENT + "?Token=" + getIntent().getStringExtra("token"));
                amountPaid = getIntent().getStringExtra("amount_paid");
 //               mWebView.loadUrl("http://tazafarms.conformiz.com/site/confirmPayment?status_code=1&status_message=success&trans_ref_no=123&order_id=a1&signature=xyz");


                try {
                    URLEncoder.encode(getIntent().getStringExtra("token"),"UTF-8");
                    Log.i(TAG, "onCreate: Encode URL "+URLEncoder.encode(getIntent().getStringExtra("token"),"UTF-8"));

                mWebView.loadUrl(Constants.INTERPAY_BASE_URL + Constants.ACTION_POST_CONFIRM_PAYMENT + "?Token=" + URLEncoder.encode(getIntent().getStringExtra("token"),"UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(ActivityPaymentWebView.this, "Could'nt Find Payment Process URL", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(ActivityPaymentWebView.this, "Please Turn On Network", Toast.LENGTH_SHORT).show();
        }


        ((ImageView) findViewById(R.id.iv_webview_payments_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        mWebView.onResume();
        // ...
    }


    @Override
    protected void onPause() {
        mWebView.onPause();
        // ...
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        mWebView.onDestroy();
        // ...
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        mWebView.onActivityResult(requestCode, resultCode, intent);
        // ...
    }

    @Override
    public void onBackPressed() {

        showMessageDialog("Are you sure to exit the payment process? ");

//        if (!mWebView.onBackPressed()) {
//            Log.e(TAG, "onBackPressed: " );
//        return;
//    }
//
//        Log.e(TAG, "onBackPressed: super");
//        // ...
//        super.onBackPressed();
    }

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

        if (url.contains("confirmPayment")) {

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
                    request.put("client_id", SharedPreferenceUtil.getInstance(ActivityPaymentWebView.this).getClientId());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            mWebView.stopLoading();
            NetworkOperations.getInstance().postData(ActivityPaymentWebView.this, Constants.ACTION_POST_PAYMENT_COMPLETE, request, this, SaveDataArrayResponse.class);
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
        if (AdvancedWebView.handleDownload(this, url, suggestedFilename)) {
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
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ActivityPaymentWebView.this);
        builder.setTitle(msg).setCancelable(false);
        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                dialog.dismiss();
                finish();
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
                showMessageDialogOnPaymentProcess("Payment Process Failed");

                // payment  not excepted
            }
        }
    }

    @Override
    public void doInBackground() {

    }


    public void showMessageDialogOnPaymentProcess(String msg) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ActivityPaymentWebView.this);
        builder.setTitle(msg).setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                dialog.dismiss();
                finish();
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
