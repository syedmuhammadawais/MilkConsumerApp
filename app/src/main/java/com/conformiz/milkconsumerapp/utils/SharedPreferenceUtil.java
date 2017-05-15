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


    public void saveKeepSignInValue(boolean saveSelect){

        mSharedPreferences.edit().putBoolean(KEEP_ME_SIGN,saveSelect).commit();
    }

    public boolean getKeepSignInValue(){
       return mSharedPreferences.getBoolean(KEEP_ME_SIGN,false);
    }
//    public SecurityKeys getSecurityKeys() {
//        SecurityKeys keys = new SecurityKeys();
//        keys.setSecretKey(mSharedPreferences.getString(KEY_SECRET, null));
//        keys.setAccessKey(mSharedPreferences.getString(KEY_ACCESS, null));
//        return keys;
//    }
//
//    public void saveSecurityKeys(SecurityKeys keys) {
//        mSharedPreferences.edit().putString(KEY_SECRET, keys.getSecretKey()).commit();
//        mSharedPreferences.edit().putString(KEY_ACCESS, keys.getAccessKey()).commit();
//    }
//
//    public boolean isLogin() {
//        return getMRN() != null && !getPatientKey().equalsIgnoreCase("-1");
//    }
//
//    public void saveMRN(String pMRN) {
//        mSharedPreferences.edit().putString(KEY_MRN, pMRN).commit();
//    }
//
//    public String getMRN() {
//        return mSharedPreferences.getString(KEY_MRN, null);
//    }
//
//    public void saveCode(String pCode) {
//        mSharedPreferences.edit().putString(KEY_CODE, pCode).commit();
//    }
//
//    public String getCode() {
//        return mSharedPreferences.getString(KEY_CODE, null);
//    }
//
//    public void savePatientKey(String pKey) {
//        mSharedPreferences.edit().putString(KEY_PATIENT, pKey).commit();
//    }
//
//    public String getPatientKey() {
//        return mSharedPreferences.getString(KEY_PATIENT, "-1");
//    }
//
//    public void saveEmergencyContactInfo(String eContact) {
//        mSharedPreferences.edit().putString(EMERGENCY_CONTACT, eContact).commit();
//    }
//
//    public String getEmergenceyContactInfo() {
//        return mSharedPreferences.getString(EMERGENCY_CONTACT, null);
//    }
//
//    public String getPatientUserId() {
//        return mSharedPreferences.getString(PATIENT_USER_ID, null);
//    }
//
//    public void setPatientUserId(String pPatientUserId) {
//        mSharedPreferences.edit().putString(PATIENT_USER_ID, pPatientUserId).commit();
//    }
//
//    public void setGeoFenceStatus(boolean pStatus) {
//        mSharedPreferences.edit().putBoolean(KEY_CODE, pStatus).commit();
//    }
//
//    public void setIsEnrolled(boolean pEnrolled) {
//        mSharedPreferences.edit().putBoolean(KEY_ENROLLED, pEnrolled).commit();
//    }
//
//
//    public boolean getEnrolled() {
//        return mSharedPreferences.getBoolean(KEY_ENROLLED, false);
//    }
//
//    public void setFirstName(String fName) {
//        mSharedPreferences.edit().putString(PATIENT_FNAME, fName).commit();
//    }
//
//    public String getFirstName() {
//        return mSharedPreferences.getString(PATIENT_FNAME, " ");
//    }
//
//    public void setLastName(String lName) {
//        mSharedPreferences.edit().putString(PATIENT_LNAME, lName).commit();
//
//    }
//
//    public String getLastName() {
//        return mSharedPreferences.getString(PATIENT_LNAME, " ");
//
//    }
//
//    //
//    public void setFragmentLounching(boolean pEnrolled) {
//        mSharedPreferences.edit().putBoolean(FRAGMENT_LOUNCHING, pEnrolled).commit();
//    }
//
//    public boolean getFragmentLounching() {
//        return mSharedPreferences.getBoolean(FRAGMENT_LOUNCHING, true);
//    }
//
//    //
//    public String getPatientFName() {
//        return mSharedPreferences.getString(PATIENT_FNAME, null);
//    }
//
//    public String getPatientLName() {
//        return mSharedPreferences.getString(PATIENT_LNAME, null);
//    }
//
//    public String getPatientDOB() {
//        return mSharedPreferences.getString(PATIENT_DOB, null);
//    }
//
//    public String getPatientGender() {
//        return mSharedPreferences.getString(PATIENT_GENDER, null);
//    }
//
//    public String getPatientPhone() {
//        return mSharedPreferences.getString(PATIENT_PHONE, null);
//    }
//
//    public String getPatientEmail() {
//        return mSharedPreferences.getString(PATIENT_EMAIL, null);
//    }
//
//    public void setPatientFName(String pPatientFName) {
//        mSharedPreferences.edit().putString(PATIENT_FNAME, pPatientFName).commit();
//    }
//
//    public void setPatientLName(String patientLName) {
//        mSharedPreferences.edit().putString(PATIENT_LNAME, patientLName).commit();
//    }
//
//    public void setUsername(String username) {
//        mSharedPreferences.edit().putString(PATIENT_UNAME, username).commit();
//    }
//
//
//
//    public void setPassword(String password) {
//        mSharedPreferences.edit().putString(PATIENT_PWD, password).commit();
//    }
//
//    public void setPatientDOB(String patientDOB) {
//        mSharedPreferences.edit().putString(PATIENT_DOB, patientDOB).commit();
//    }
//
//    public void setPatientPhone(String patientPhone) {
//        mSharedPreferences.edit().putString(PATIENT_PHONE, patientPhone).commit();
//    }
//
//    public void setPatientEmail(String patientEmail) {
//        mSharedPreferences.edit().putString(PATIENT_EMAIL, patientEmail).commit();
//    }
//
//    public void setPatientGender(String patientGender) {
//        mSharedPreferences.edit().putString(PATIENT_GENDER, patientGender).commit();
//    }
//
//    public boolean isFencesAdded() {
//        return mSharedPreferences.getBoolean(KEY_FENCES, false);
//    }
//
//    public void clearCache() {
//        mSharedPreferences.edit().remove(KEY_SECRET).commit();
//        mSharedPreferences.edit().remove(KEY_ACCESS).commit();
//        mSharedPreferences.edit().remove(KEY_MRN).commit();
//        mSharedPreferences.edit().remove(KEY_CODE).commit();
//        mSharedPreferences.edit().remove(KEY_PATIENT).commit();
//        mSharedPreferences.edit().remove(KEY_FENCES).commit();
//    }
//
//    public void saveIpAddress(String ip) {
//        mSharedPreferences.edit().putString(KEY_IP, ip).commit();
//    }
//
//    public String getIpAddress() {
//        return mSharedPreferences.getString(KEY_IP, null);
//    }
//
//    public void saveEnterpriseId(String id) {
//        mSharedPreferences.edit().putString(KEY_ENT_ID, id).commit();
//    }
//
//    public void saveTermsAndConditionStatus(Boolean acceptOrNot) {
//        mSharedPreferences.edit().putBoolean(TERMS_ACCEPT_VALUE, acceptOrNot).commit();
//    }
//
//    public boolean getTermsAndConditionStatus() {
//        return mSharedPreferences.getBoolean(TERMS_ACCEPT_VALUE, false);
//    }
//
//    public void saveAutoLogoutTime(long time) {
//        mSharedPreferences.edit().putLong(AUTO_LOGOUT_TIME, time).commit();
//    }
//
//    public void saveSelectedLogoutTimeIndex(int index) {
//        mSharedPreferences.edit().putInt(TIMEOUT_SELECTED_INDEX, index).commit();
//    }
//
//
//    public long  getAutoLogoutTime() {
//        return mSharedPreferences.getLong(AUTO_LOGOUT_TIME, APP_AUTO_LOGOUT_TIME);
//    }
//
//    public int getSelectedLogoutTimeIndex() {
//        return mSharedPreferences.getInt(TIMEOUT_SELECTED_INDEX, 4);
//    }
//
//
//    public String getEnterpriseId() {
//        return mSharedPreferences.getString(KEY_ENT_ID, "-1");
//    }
//
//    public void saveConcersationId(String userKey, String cId) {
//        mSharedPreferences.edit().putString(userKey, cId).commit();
//    }
//
//    public String getConversationId(String key) {
//        return mSharedPreferences.getString(key, -1 + "");
//    }
//
//    public void saveFireBaseToken(String token) {
//        mSharedPreferences.edit().putString(Constants.FIREBASE_TOKEN, token).commit();
//    }
//
//    public String getFireBaseToken() {
//        return mSharedPreferences.getString(Constants.FIREBASE_TOKEN, "Not Found");
//    }
//
//    public void saveShowNotification(String value) {
//        mSharedPreferences.edit().putString(SHOW_NOTIFICATION, value).commit();
//    }
//
//    public String getShowNotification() {
//        return mSharedPreferences.getString(SHOW_NOTIFICATION, "Yes");
//    }
//
//    public void savePatientUsername(String username) {
//        mSharedPreferences.edit().putString(PATIENT_USERNAME, username).commit();
//    }
//
//    public String getPatientUsername(){
//        return mSharedPreferences.getString(PATIENT_USERNAME,"");
//    }
//
//    public void saveEnableBackgroundLogout(Boolean value) {
//        mSharedPreferences.edit().putBoolean(ENABLE_BACKGROUND_LOGOUT, value).commit();
//    }
//
//    public Boolean getEnableBackgroundLogout(){
//        return mSharedPreferences.getBoolean(ENABLE_BACKGROUND_LOGOUT,true);
//    }

}