package com.conformiz.milkconsumerapp.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.databinding.ActivitySignupBinding;
import com.conformiz.milkconsumerapp.fragments.signupfragments.ScreenTwoFragment;
import com.conformiz.milkconsumerapp.fragments.signupfragments.ScreenThreeFragment;
import com.conformiz.milkconsumerapp.fragments.signupfragments.ScreenOneFragment;
import com.conformiz.milkconsumerapp.models.Request.SignUpUserRootRequest;
import com.conformiz.milkconsumerapp.models.response.SaveDataResponse;
import com.conformiz.milkconsumerapp.models.response.UserCreationResponse;
import com.conformiz.milkconsumerapp.network.INetworkListener;
import com.conformiz.milkconsumerapp.network.NetworkOperations;
import com.conformiz.milkconsumerapp.utils.Constants;
import com.conformiz.milkconsumerapp.utils.SharedPreferenceUtil;
import com.conformiz.milkconsumerapp.utils.Utility;

import org.json.JSONException;
import org.json.JSONObject;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener, INetworkListener {

    ActivitySignupBinding binding;
    private ProgressDialog mProgressDialog;

    ScreenTwoFragment screenTwoFragment;
    ScreenOneFragment screenOneFragment;
    ScreenThreeFragment screenThreeFragment;

    public SignUpUserRootRequest signUpRequest = new SignUpUserRootRequest();

    public boolean isValidate = false;

    private int fragmentNo = 1; // default show fragment Step 1


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        binding = DataBindingUtil.setContentView(SignupActivity.this, R.layout.activity_signup);
        binding.btnNext.setOnClickListener(this);
        binding.btnBack.setOnClickListener(this);

        Utility.buttonEffect(binding.btnNext);
        Utility.buttonEffect(binding.btnBack);

        screenOneFragment = new ScreenOneFragment();
        screenTwoFragment = new ScreenTwoFragment();
        screenThreeFragment = new ScreenThreeFragment();

        updateFragment(fragmentNo);

    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.btn_next:
                boolean isFragmentValidated = false;
                if (fragmentNo == 1 && screenOneFragment.isValidate && screenOneFragment.checkValidation()) {

                    // Validate on Next button press so, user will go to next fragment if current fragment validation is successful for all fields
                    screenOneFragment.addDataToRequestObject(signUpRequest);
                    isFragmentValidated = true;

                    fragmentNo++;
                    updateFragment(fragmentNo);

                } else if (fragmentNo == 2 && screenTwoFragment.isValidate && screenTwoFragment.checkValidation()) {
                    // Validate on Next button press so, user will go to next fragment if current fragment validation is successful for all fields
                    isFragmentValidated = true;
                    screenTwoFragment.addDataToRequestObject(signUpRequest);
                    NetworkOperations.getInstance().postData(SignupActivity.this, Constants.ACTION_POST_CUSTOMER_SIGN_UP, signUpRequest, this, UserCreationResponse.class);


                } else if (fragmentNo == 3 && screenThreeFragment.isValidationTrue()) {

                    JSONObject requestAuthenticationCode = new JSONObject();
                    try {
                        requestAuthenticationCode.put("client_id", SharedPreferenceUtil.getInstance(SignupActivity.this).getClientId());
                        requestAuthenticationCode.put("code", screenThreeFragment.getAuthCode());
                        NetworkOperations.getInstance().postData(SignupActivity.this,
                                Constants.ACTION_POST_CUSTOMER_AUTHENTICATION,
                                requestAuthenticationCode,
                                this,
                                SaveDataResponse.class);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }  else {
                    isFragmentValidated = false;
                    Toast.makeText(SignupActivity.this, "Please Update Empty Fields or Fields With Errors", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_back:
                if (fragmentNo > 1) {
                    fragmentNo--;
                    updateFragment(fragmentNo);
                } else {
                    finish();
                }
                break;
        }

    }


    public void updateFragment(int fragmentNumber) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (fragmentNumber) {

            // case is fragment number
            case 1:
                transaction.replace(R.id.sign_up_container, screenOneFragment);
                transaction.commit();
                updateStepUi(1);
                break;

            case 2:
                transaction.replace(R.id.sign_up_container, screenTwoFragment);
                transaction.commit();
                updateStepUi(2);
                break;

            case 3:
                transaction.replace(R.id.sign_up_container, screenThreeFragment);
                transaction.commit();
                updateStepUi(3);
                break;


        }
    }

    public void updateStepUi(int fragmentNumber) {

        binding.ivStep1.setImageResource(R.drawable.step1_blue);
        binding.ivStep2.setImageResource(R.drawable.step2_blue);
        binding.ivStep3.setImageResource(R.drawable.step3_blue);

        switch (fragmentNumber) {
            case 1:
                binding.ivStep1.setImageResource(R.drawable.step1_white);
                break;

            case 2:
                binding.ivStep2.setImageResource(R.drawable.step2_white);
                break;

            case 3:
                binding.ivStep3.setImageResource(R.drawable.step3_white);
                binding.btnBack.setVisibility(View.GONE);
                binding.btnNext.setText("Enter");
                break;


        }
    }

    @Override
    public void onBackPressed() {
        if(fragmentNo == 3){
            showMessageDialog("Are you sure to close the SignUp Process?");
        } else
        onClick(findViewById(R.id.btn_back));
    }

    @Override
    public void onPreExecute() {
        mProgressDialog = new ProgressDialog(SignupActivity.this);
        mProgressDialog.setMessage("Creating User....");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    @Override
    public void onPostExecute(Object result) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }

        if (result instanceof UserCreationResponse && ((UserCreationResponse) result).getSuccess()) {
            Toast.makeText(SignupActivity.this, "Verification Code Sent Successfully", Toast.LENGTH_SHORT).show();
            SharedPreferenceUtil.getInstance(SignupActivity.this).saveClientId(((UserCreationResponse) result).getData().getClient_id());
            fragmentNo++;
            updateFragment(fragmentNo);
        }else {
            Toast.makeText(SignupActivity.this, "Server Error Found" , Toast.LENGTH_SHORT).show();

        }

        if (result instanceof SaveDataResponse) {
            SaveDataResponse response = (SaveDataResponse) result;
            if (response.getSuccess()) {
                Toast.makeText(SignupActivity.this, "" + response.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(SignupActivity.this, "" + response.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void doInBackground() {

    }


    public void showMessageDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
        builder.setTitle("" + msg)
                .setIcon(R.drawable.product_info)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton("Exit Sign Up", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        finish();


                    }
                });

        builder.show();
    }
}
