package com.conformiz.milkconsumerapp.fragments.signupfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.models.Request.SignUpUserRootRequest;
import com.conformiz.milkconsumerapp.models.response.SaveDataArrayResponse;
import com.conformiz.milkconsumerapp.network.INetworkListener;
import com.conformiz.milkconsumerapp.network.NetworkOperations;
import com.conformiz.milkconsumerapp.utils.Constants;
import com.conformiz.milkconsumerapp.utils.Utility;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Fahad.Munir on 12-Apr-17.
 */

public class ScreenTwoFragment extends Fragment implements View.OnClickListener, View.OnFocusChangeListener, INetworkListener {

    public boolean isValidate = false;

    EditText emailET;
    EditText phoneET;
    EditText usernameET;
    EditText passwordET;

    public ScreenTwoFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_screen_two, container, false);

        emailET = (EditText) view.findViewById(R.id.et_email_sign_up);
        phoneET = (EditText) view.findViewById(R.id.et_phone_sign_up);
        usernameET = (EditText) view.findViewById(R.id.et_username_sign_up);
        passwordET = (EditText) view.findViewById(R.id.et_password_sign_up);

        emailET.setOnClickListener(this);
        phoneET.setOnClickListener(this);
        usernameET.setOnClickListener(this);
        passwordET.setOnClickListener(this);

        emailET.setOnFocusChangeListener(this);
        phoneET.setOnFocusChangeListener(this);
        usernameET.setOnFocusChangeListener(this);
        passwordET.setOnFocusChangeListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.et_email_sign_up:
                break;

            case R.id.et_phone_sign_up:
                break;

            case R.id.et_username_sign_up:
                break;

            case R.id.et_password_sign_up:
                break;

        }

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        switch (v.getId()) {

            case R.id.et_email_sign_up:
                if (!hasFocus) {
                    if (TextUtils.isEmpty(emailET.getText().toString().trim())) {
                        emailET.setError("Please Enter Email");
                        isValidate = false;
                    } else if (!(Utility.getInstance().isValidEmail(emailET.getText().toString()))) {
                        isValidate = false;
                        emailET.setError("Please Enter a valid email address format");
                    } else {
                        isValidate = true;
                    }
                }
                break;

            case R.id.et_phone_sign_up:
                if (!hasFocus) {
                    if (TextUtils.isEmpty(phoneET.getText().toString().trim())) {
                        phoneET.setError("Please Enter Phone");
                        isValidate = false;
                    } else if (phoneET.getText().toString().length() < 11) {
                        isValidate = false;
                        phoneET.setError("Contact Number must have a length of 11");
                    } else {
                        String ph = phoneET.getText().toString().substring(0, 2);
                        if (!ph.equals("03")) {
                            isValidate = false;
                            phoneET.setError("Mobile Number not valid (Must start with 03)");
                        } else isValidate = true;
                    }


                }
                break;

            case R.id.et_username_sign_up:
                if (!hasFocus) {
                    if (TextUtils.isEmpty(usernameET.getText().toString().trim())) {
                        usernameET.setError("Please Enter Username");
                        isValidate = false;
                    } else {

                        JSONObject isUsernameAvailable = new JSONObject();
                        try {
                            isUsernameAvailable.put("userName", usernameET.getText().toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        NetworkOperations.getInstance().postData(getActivity(), Constants.ACTION_POST_CHECK_USER_AVAILABLE, isUsernameAvailable, this, SaveDataArrayResponse.class);
                    }
                }
                break;

            case R.id.et_password_sign_up:
                if (!hasFocus) {
                    if (TextUtils.isEmpty(passwordET.getText().toString().trim())) {
                        passwordET.setError("Please Enter Password");

                        isValidate = false;
                    } else {
                        isValidate = true;
                    }
                }
                break;

        }
    }

    @Override
    public void onPreExecute() {

    }

    @Override
    public void onPostExecute(Object result) {

        if (result != null) {
            if (result instanceof SaveDataArrayResponse) {
                if (((SaveDataArrayResponse) result).getSuccess()) {
                    Log.i("else save Data response", "onPostExecute: not instacn TRUE");

                    isValidate = true;
                    Toast.makeText(getActivity(), "Username Available", Toast.LENGTH_SHORT).show();
                    // usernameET.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.primary_light));
                } else {

                    isValidate = false;
                    usernameET.setError("Username Already Exist");
                    //  usernameET.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.light_red));
                    Toast.makeText(getActivity(), "Username already exist please try another Username", Toast.LENGTH_SHORT).show();
                }
            } else {
                Log.i("else save Data response", "onPostExecute: not instacn");
            }
        }else {Log.i("NULL", "onPostExecute: not instacn");}
    }

    @Override
    public void doInBackground() {

    }

    public void addDataToRequestObject(SignUpUserRootRequest request) {

//        ((SignupActivity)getActivity()).signUpRequest.setEmail(emailET.getText().toString());
//        ((SignupActivity)getActivity()).signUpRequest.setResidence_phone_no(phoneET.getText().toString());
//        ((SignupActivity)getActivity()).signUpRequest.setUserName(usernameET.getText().toString());
//        ((SignupActivity)getActivity()).signUpRequest.setPassword(passwordET.getText().toString());
//

        request.setEmail(emailET.getText().toString());
        request.setCell_no_1( phoneET.getText().toString().substring(1, phoneET.getText().toString().length()));
        request.setUserName(usernameET.getText().toString());
        request.setPassword(passwordET.getText().toString());

    }


    public boolean checkValidation() {

        if (TextUtils.isEmpty(emailET.getText().toString())) {
            isValidate = false;
        } else if (TextUtils.isEmpty(phoneET.getText().toString())) {
            isValidate = false;
        } else if (TextUtils.isEmpty(usernameET.getText().toString())) {
            isValidate = false;
        } else if (TextUtils.isEmpty(passwordET.getText().toString())) {
            isValidate = false;
        } else isValidate = true;

        return isValidate;
    }

}
