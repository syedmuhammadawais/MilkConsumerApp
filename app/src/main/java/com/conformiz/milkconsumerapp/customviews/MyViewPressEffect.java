package com.conformiz.milkconsumerapp.customviews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Fahad.Munir on 23-May-17.
 */

public class MyViewPressEffect extends ViewGroup {


    public MyViewPressEffect(Context context) {
        super(context);
    }

    public MyViewPressEffect(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewPressEffect(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            return super.dispatchTouchEvent(event);
        }
        catch (Exception ignored){
            return true;
        }
    }





}
