package com.conformiz.milkconsumerapp.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.models.response.SaveDataArrayResponse;
import com.conformiz.milkconsumerapp.models.response.SaveDataResponse;
import com.conformiz.milkconsumerapp.network.INetworkListener;
import com.conformiz.milkconsumerapp.network.NetworkOperations;
import com.conformiz.milkconsumerapp.utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import it.beppi.tristatetogglebutton_library.TriStateToggleButton;

public class RecoverPasswordActivity extends AppCompatActivity implements
        View.OnClickListener,
        TriStateToggleButton.OnToggleChanged,
        View.OnFocusChangeListener, INetworkListener {

    private boolean isValidate = false;
    private ProgressDialog mProgressDialog;

    private EditText emailOrPhoneET;

   // private TextView emailOrPhoneTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_password);

        emailOrPhoneET = (EditText) findViewById(R.id.et_email_forget_pass);
      //  emailOrPhoneTV = (TextView) findViewById(R.id.tv_email_phone);
        emailOrPhoneET.setOnFocusChangeListener(this);
        findViewById(R.id.btn_recover_password).setOnClickListener(this);


       // ((TriStateToggleButton) findViewById(R.id.tb_email_or_password)).setOnToggleChanged(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_recover_password:

                if(checkValidation()){

                    JSONObject request = new JSONObject();
                    try {
                        request.put("cell_no_1",emailOrPhoneET.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    NetworkOperations.getInstance().postData(RecoverPasswordActivity.this, Constants.ACTION_GET_RECOVER_PASSWORD,request,this, SaveDataResponse.class);
                }
                break;
        }

    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.et_email_forget_pass:
                isValidate = !hasFocus && !TextUtils.isEmpty(emailOrPhoneET.getText().toString());
                break;
        }
    }

    @Override
    public void onPreExecute() {
        mProgressDialog = new ProgressDialog(RecoverPasswordActivity.this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Recovering Password...");
        mProgressDialog.show();
    }

    @Override
    public void onPostExecute(Object result) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();

            if(result !=null  && result instanceof SaveDataResponse){
                SaveDataResponse response = (SaveDataResponse)result;
                if(response.getSuccess()){
                    showMessageDialog("Your Password is: "+response.getData());
                } else{
                    Toast.makeText(RecoverPasswordActivity.this,""+response.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
            if(result !=null  && result instanceof SaveDataArrayResponse){
               showMessageDialog("This number is not registered");
            }


            Log.i("change", "onPostExecute: ");
        }

    }

    @Override
    public void doInBackground() {

    }

    @Override
    public void onToggle(TriStateToggleButton.ToggleStatus toggleStatus, boolean b, int i) {
        switch (i) {
            case 2:
                emailOrPhoneET.setText("");
                emailOrPhoneET.setHint("Phone: 03xx1234567");
              //  emailOrPhoneTV.setText("Phone");
                break;

            case 0:
                emailOrPhoneET.setText("");
                emailOrPhoneET.setHint("Enter Email Address");
             //   emailOrPhoneTV.setText("Email Address");

                break;

        }
    }

    public boolean checkValidation(){
        if(TextUtils.isEmpty(emailOrPhoneET.getText().toString())){
            isValidate = false;
            emailOrPhoneET.setError("Please Enter Your Phone");
        } else {
            isValidate = true;
        }

        return isValidate;
    }

    public void showMessageDialog(String msg) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(RecoverPasswordActivity.this);
        builder.setTitle(msg).setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                dialog.cancel();
            }
        });

        android.app.AlertDialog alert = builder.create();
        alert.show();
    }

}
