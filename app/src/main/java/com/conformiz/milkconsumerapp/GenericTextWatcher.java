package com.conformiz.milkconsumerapp;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

/**
 * Created by Fahad.Munir on 01-Jun-17.
 */

public class GenericTextWatcher implements TextWatcher {

    private View view;

    private GenericTextWatcher(View view) {
        this.view = view;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    public void afterTextChanged(Editable editable) {

    }
}
