package com.conformiz.milkconsumerapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Talha.Hafeez on 10/20/2015.
 */
public class SharedPreferenceUtil implements Constants {

    private SharedPreferences mSharedPreferences;
    private static SharedPreferenceUtil mInstance;

    private SharedPreferenceUtil(Context context) {
        if (mSharedPreferences == null) {
            mSharedPreferences = (context).getSharedPreferences(
                    KEY_PREF_NAME, Context.MODE_PRIVATE);
        }
    }

    public static synchronized SharedPreferenceUtil getInstance(Context c) {
        if (mInstance == null) {
            mInstance = new SharedPreferenceUtil(c);
        }
        return mInstance;
    }

    public void saveClientId(String id){
        mSharedPreferences.edit().putString(CLIENT_ID,id).commit();
    }

    public String getClientId(){
        return mSharedPreferences.getString(CLIENT_ID,"");
    }

    public void saveClientFullName(String id){
        mSharedPreferences.edit().putString("fullname",id).commit();
    }

    public String getClientFullName(){return mSharedPreferences.getString("fullname","");}

    public void saveClientEmail(String id){
        mSharedPreferences.edit().putString("email",id).commit();
    }

    public String getClientEmail(){return mSharedPreferences.getString("email","");}

    public void saveClientContact(String id){
        mSharedPreferences.edit().putString("cell_no_1",id).commit();
    }

    public String getClientContact(){return mSharedPreferences.getString("cell_no_1","");}

    public void saveKeepSignInValue(boolean saveSelect){

        mSharedPreferences.edit().putBoolean(KEEP_ME_SIGN,saveSelect).commit();
    }

    public boolean getKeepSignInValue(){
       return mSharedPreferences.getBoolean(KEEP_ME_SIGN,false);
    }

    public void setCheckBoxValue(boolean status){
        mSharedPreferences.edit().putBoolean(SHOW_CHECK_BOX, status).commit();
    }

    public boolean getCheckBoxValue(){
        return  mSharedPreferences.getBoolean(SHOW_CHECK_BOX,true);
    }


    public void saveUsername(String username){
        mSharedPreferences.edit().putString(SAVE_USERNAME,username).commit();

    }

    public String getUsername(){
       return mSharedPreferences.getString(SAVE_USERNAME,"");
    }

    public void saveUserPassword(String password){
        mSharedPreferences.edit().putString(SAVE_PASSWORD,password).commit();

    }

    public String getUserPassword(){

        return mSharedPreferences.getString(SAVE_PASSWORD,"");
    }

}