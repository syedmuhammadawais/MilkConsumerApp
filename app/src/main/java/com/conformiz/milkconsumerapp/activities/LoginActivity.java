package com.conformiz.milkconsumerapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.databinding.ActivityLoginBinding;
import com.conformiz.milkconsumerapp.models.response.LoginRootResponse;
import com.conformiz.milkconsumerapp.network.INetworkListener;
import com.conformiz.milkconsumerapp.network.NetworkOperations;
import com.conformiz.milkconsumerapp.utils.Constants;
import com.conformiz.milkconsumerapp.utils.SharedPreferenceUtil;
import com.conformiz.milkconsumerapp.utils.Utility;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import it.beppi.tristatetogglebutton_library.TriStateToggleButton;

public class LoginActivity extends AppCompatActivity implements
        View.OnClickListener,
        TriStateToggleButton.OnToggleChanged,
        View.OnFocusChangeListener, INetworkListener {

    private String TAG = "LoginActivity";
    boolean isValidate = false;

    ActivityLoginBinding bindView;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindView = DataBindingUtil.setContentView(this, R.layout.activity_login);

        bindView.btnLogin.setOnClickListener(this);
        Utility.buttonEffect(bindView.btnLogin);
        Utility.buttonEffect(bindView.btnSignUp);

        bindView.btnSignUp.setOnClickListener(this);
        bindView.tvForgetPassword.setOnClickListener(this);
        bindView.tvRememberMe.setOnClickListener(this);
        bindView.tbRememberMe.setOnToggleChanged(this);
        bindView.etEnterUsername.setOnFocusChangeListener(this);
        bindView.etEnterPassword.setOnFocusChangeListener(this);

        mProgressDialog = new ProgressDialog(LoginActivity.this);

        if (SharedPreferenceUtil.getInstance(LoginActivity.this).getKeepSignInValue()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }


    }

    @Override
    public void onClick(View v) {

        Intent intent;
        switch (v.getId()) {

            case R.id.btn_login:
                if (isValidated()) {
                    JSONObject loginObj = new JSONObject();
                    JsonObject g = new JsonObject();

                    try {
                        loginObj.put("userName", bindView.etEnterUsername.getText().toString());
                        loginObj.put("password", bindView.etEnterPassword.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    NetworkOperations.getInstance().postData(LoginActivity.this, Constants.ACTION_POST_LOGIN, loginObj, this, LoginRootResponse.class);

                } else {
                    Toast.makeText(LoginActivity.this, "Please Update Fields With Error Message", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_sign_up:
                intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                break;

            case R.id.tv_forget_password:
                Intent recoverPasswordIntent = new Intent(LoginActivity.this,RecoverPasswordActivity.class);
                startActivity(recoverPasswordIntent);
                break;

            case R.id.tv_remember_me:
                if (bindView.tbRememberMe.getToggleStatus().toString().equalsIgnoreCase("off")) {
                    bindView.tbRememberMe.setToggleStatus(2, true);
                    onToggle(bindView.tbRememberMe.getToggleStatus(), true, 2);
                } else if (bindView.tbRememberMe.getToggleStatus().toString().equalsIgnoreCase("on")) {
                    bindView.tbRememberMe.setToggleStatus(0, true);
                    onToggle(bindView.tbRememberMe.getToggleStatus(), false, 0);
                }
                break;
        }

    }

    @Override
    public void onPreExecute() {

        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Authenticating User...");
        mProgressDialog.show();

    }

    @Override
    public void onPostExecute(Object result) {

        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }

        if (result != null && result instanceof LoginRootResponse) {

            LoginRootResponse response = (LoginRootResponse) result;

            if (response.getSuccess()) {
                SharedPreferenceUtil.getInstance(LoginActivity.this).saveClientId(response.getData().getClient_id());
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(LoginActivity.this, response.getMessage() + "", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(LoginActivity.this, "Problem Found Could'nt login", Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public void doInBackground() {

    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {

            case R.id.et_enter_username:
                if (TextUtils.isEmpty(bindView.etEnterUsername.getText().toString().trim()) && !hasFocus) {
                    bindView.etEnterUsername.setError("Username is Required");
                    isValidate = false;
                } else {
                    // Do not do network call for user exist or not
                    // set isValidate in onPostExecute for true or false
                }
                break;

            case R.id.et_enter_password:
                if (TextUtils.isEmpty(bindView.etEnterPassword.getText().toString().trim()) && !hasFocus) {
                    bindView.etEnterPassword.setError("Password is Required");
                    isValidate = false;
                } else {
                    isValidate = true;
                }
                break;

        }
    }

    @Override
    public void onToggle(TriStateToggleButton.ToggleStatus toggleStatus, boolean b, int i) {

        switch (i) {
            case 0:
                SharedPreferenceUtil.getInstance(LoginActivity.this).saveKeepSignInValue(false);
                break;

            case 2:
                SharedPreferenceUtil.getInstance(LoginActivity.this).saveKeepSignInValue(true);
                break;
        }
    }

    public boolean isValidated() {
        if (TextUtils.isEmpty(bindView.etEnterUsername.getText().toString().trim())) {
            bindView.etEnterUsername.setError("Username is Required");
            isValidate = false;
        }
        if (TextUtils.isEmpty(bindView.etEnterPassword.getText().toString().trim())) {
            bindView.etEnterPassword.setError("Password is Required");
            isValidate = false;
        } else {
            isValidate = true;
        }
        return isValidate;
    }
}
