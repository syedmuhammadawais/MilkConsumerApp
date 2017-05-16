package com.conformiz.milkconsumerapp.fragments.signupfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.conformiz.milkconsumerapp.R;

/**
 * Created by Fahad.Munir on 12-Apr-17.
 */

public class ScreenThreeFragment extends Fragment {

    public boolean isValidate = false;
    private EditText verificationCode;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_screen_three,container,false);
        verificationCode = (EditText)  view.findViewById(R.id.et_enter_code_sign_up);
        return view;
    }

    public String getAuthCode(){
        return verificationCode.getText().toString();
    }

    public boolean isValidationTrue(){
        if(TextUtils.isEmpty(verificationCode.getText().toString())){
            verificationCode.setError("Please Enter Verification Code");
            Toast.makeText(getActivity(),"Please Enter Verification Code",Toast.LENGTH_SHORT).show();
        return false;
        }
        return true;
    }


}
