package com.conformiz.milkconsumerapp.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
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

import it.beppi.tristatetogglebutton_library.TriStateToggleButton;

public class RecoverPasswordActivity extends AppCompatActivity implements
        View.OnClickListener,
        TriStateToggleButton.OnToggleChanged,
        View.OnFocusChangeListener, INetworkListener {

    private boolean isValidate = false;
    private ProgressDialog mProgressDialog;

    Button recoverPasswordBT;

    private EditText emailOrPhoneET;

   // private TextView emailOrPhoneTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_password);

        recoverPasswordBT = (Button) findViewById(R.id.btn_recover_password);
        emailOrPhoneET = (EditText) findViewById(R.id.et_email_forget_pass);
      //  emailOrPhoneTV = (TextView) findViewById(R.id.tv_email_phone);
        emailOrPhoneET.setOnFocusChangeListener(this);
        recoverPasswordBT.setOnClickListener(this);

        Utility.getInstance().buttonEffect(recoverPasswordBT,R.color.app_brown_light);
        findViewById(R.id.btn_back_recover_password).setOnClickListener(this);


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

                    NetworkOperations.getInstance().postData(RecoverPasswordActivity.this, Constants.ACTION_GET_RECOVER_PASSWORD,request,this, SaveDataObjectResponse.class);
                }
                break;

            case R.id.btn_back_recover_password:
                onBackPressed();
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

            if(result !=null  && result instanceof SaveDataObjectResponse){
                SaveDataObjectResponse response = (SaveDataObjectResponse)result;
                if(response.getSuccess()){
                    showMessageDialog(""+response.getMessage());
                } else{
                    Toast.makeText(RecoverPasswordActivity.this,""+response.getMessage(),Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(RecoverPasswordActivity.this,"Server Error",Toast.LENGTH_SHORT).show();
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
        } else if(emailOrPhoneET.getText().toString().length()>0){


            String str = emailOrPhoneET.getText().toString().substring(0,3);

            Log.i("tag", "checkValidation: "+str);
            if(str.equalsIgnoreCase("+92")){
                isValidate = true;
            }else {
                emailOrPhoneET.setError("Please write phone number in the format of +923xxxxxxxxx");
                isValidate = false;

            }

        }

        else {
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
                RecoverPasswordActivity.this.onBackPressed();
            }
        });

        android.app.AlertDialog alert = builder.create();
        alert.show();
    }

}
