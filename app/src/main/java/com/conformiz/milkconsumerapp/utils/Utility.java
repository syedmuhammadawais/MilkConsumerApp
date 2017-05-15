package com.conformiz.milkconsumerapp.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.activities.MainActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {

    private static Utility mInstance;

    private Utility() {
    }

    public static Utility getInstance() {
        if (mInstance == null)
            mInstance = new Utility();
        return mInstance;
    }

    public boolean isOnline(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        return (netInfo != null && netInfo.isConnected());
    }


    public boolean isContainOneNumberAndString(String s) {
        String number = ".*[0-9].*";
        String character = ".*[A-Za-z].*";
        return s.matches(number) && s.matches(character);
    }

    public boolean isStringContainsSpecialCharacter(String s) {
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(s);
        // boolean b = m.matches();
        Log.i("s in ", " v = " + s);
        boolean b = m.find();
        boolean result = false;
        if (b) {
            System.out.println("There is a special character in my string ");
            result = true;

        } else {
            System.out.println("There is no special char.");
            result = false;

        }

        return result;
    }

    public boolean isAllowedSpecialCharactersInString(String s) {

        boolean result = false;
        String se = "";
        Log.e("There Pass: ", " " + s);
        Pattern regex = Pattern.compile("[#@!_~.-]");
        //  Pattern regex = Pattern.compile("[#@!~-_.]");

        //matcher to find if there is any special character in string
        Matcher matcher = regex.matcher(s);


        if (matcher.find()) {
            Log.i(" con", "1");

            se = matcher.replaceAll("A");

            if (isStringContainsSpecialCharacter(se)) {
                Log.i(" con", "1 of 1");

                System.out.println("There  char." + se);
                Log.i("s", " found");
                result = false;
            } else {
                Log.i(" con", "1 of 2");

                result = true;
            }
            // errors.rejectValue("searchFor", "wrong_pattern.SearchQuery.searchForSpecialCharacters","Special characters are not allowed!");
        } else if (isStringContainsSpecialCharacter(s)) {
            Log.i(" con", "2");

            System.out.println("There  char else.");
            result = false;
        } else {
            Log.i(" con", "3");

            result = true;
        }

        return result;
    }


    public void onSuccessSubmitDialog(String msg, final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false)
                .setTitle("" + msg)
                .setIcon(R.drawable.ok_dialog_icon_36)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                        dialog.cancel();
                        dialog.dismiss();
                        ((MainActivity) context).onBackPressed();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    public  boolean isValidEmail(CharSequence target) {
        Log.i("Test email value", "isValidEmail: "+ android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches());
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();

    }



    // UI Button Pressed State UIs
    public static void buttonEffect(View button){
        button.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
    }

}
