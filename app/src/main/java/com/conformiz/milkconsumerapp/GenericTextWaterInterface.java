package com.conformiz.milkconsumerapp;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by Fahad.Munir on 01-Jun-17.
 */

public class GenericTextWaterInterface  implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
