package com.conformiz.milkconsumerapp.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.models.SaveDataObjectResponse;
import com.conformiz.milkconsumerapp.network.INetworkListener;
import com.conformiz.milkconsumerapp.network.NetworkOperations;
import com.conformiz.milkconsumerapp.utils.Constants;
import com.conformiz.milkconsumerapp.utils.Utility;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterCodeActivity extends AppCompatActivity implements View.OnClickListener, INetworkListener, View.OnFocusChangeListener {

    private EditText enterCodeET;
    private EditText enterPhoneET;


    private boolean isValidate = false;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_code);

        enterCodeET = (EditText) findViewById(R.id.et_enter_code_register_code);
        enterPhoneET = (EditText) findViewById(R.id.et_phone_register_code);

        enterPhoneET.setOnFocusChangeListener(this);
        findViewById(R.id.btn_submit_code).setOnClickListener(this);

        Utility.getInstance().buttonEffect(((Button)findViewById(R.id.btn_submit_code)), R.color.app_brown_light);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_submit_code:
                if (checkValidation()) {
                    JSONObject request = new JSONObject();
                    try {
                        request.put("cell_no_1", "+92"+enterPhoneET.getText().toString());
                        request.put("code", enterCodeET.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    NetworkOperations.getInstance().postData(RegisterCodeActivity.this,
                            Constants.ACTION_POST_ACTIVATION_WITH_CODE, request, this, SaveDataObjectResponse.class);
                } else {
                    Toast.makeText(RegisterCodeActivity.this, "Please Update fields with errors", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onPreExecute() {
        mProgressDialog = new ProgressDialog(RegisterCodeActivity.this);
        mProgressDialog.setMessage("Verifying Code...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    @Override
    public void onPostExecute(Object result) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }

        if (result != null && result instanceof SaveDataObjectResponse) {
            SaveDataObjectResponse response = (SaveDataObjectResponse) result;
            if (response.getSuccess()) {
                showMessageDialog("" + response.getMessage());
            } else {
                Toast.makeText(RegisterCodeActivity.this, "Phone number not available for registration", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(RegisterCodeActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void doInBackground() {

    }

    public boolean checkValidation() {

        if (TextUtils.isEmpty(enterPhoneET.getText().toString().trim())) {
            enterPhoneET.setError("Please Enter Phone");
            isValidate = false;
        } else if (enterPhoneET.getText().toString().length() < 10) {
            isValidate = false;
            enterPhoneET.setError("Contact Number must have a length of 10");
        } else if (!enterPhoneET.getText().toString().substring(0, 1).equals("3")) {
            isValidate = false;
            enterPhoneET.setError("Mobile Number not valid (Must start with 3xxxxxxxxx)");
        } else if (enterCodeET.getText().toString().length() <= 0) {
            isValidate = false;
            enterCodeET.setError("Please Enter Code");
        }

        return isValidate;
    }


    public void showMessageDialog(String msg) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(RegisterCodeActivity.this);
        builder.setTitle(msg).setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                dialog.cancel();
                finish();
            }
        });

        android.app.AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {

            case R.id.et_phone_register_code:
                if (!hasFocus) {
                    if (TextUtils.isEmpty(enterPhoneET.getText().toString().trim())) {
                        enterPhoneET.setError("Please Enter Phone");
                        isValidate = false;
                    } else if (enterPhoneET.getText().toString().length() < 10) {
                        isValidate = false;
                        enterPhoneET.setError("Contact Number must have a length of 10");
                    } else {
                        String ph = enterPhoneET.getText().toString().substring(0, 1);
                        if (!ph.equals("3")) {
                            isValidate = false;
                            enterPhoneET.setError("Mobile Number not valid (Must start with 03)");
                        } else isValidate = true;
                    }
                }
                break;

        }
    }
}
