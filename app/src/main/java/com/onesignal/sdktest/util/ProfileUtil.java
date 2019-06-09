package com.onesignal.sdktest.util;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.Patterns;

public class ProfileUtil {

    public ProfileUtil() {

    }

    public boolean isEmailValid(TextInputLayout emailTextInputLayout) {
        emailTextInputLayout.setErrorEnabled(false);
        if (emailTextInputLayout.getEditText() != null) {
            String email = emailTextInputLayout.getEditText().getText().toString().trim();
            if (email.isEmpty()) {
                emailTextInputLayout.setError("Email is required");
                return false;
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailTextInputLayout.setError("Invalid email");
                return false;
            }
        } else {
            emailTextInputLayout.setError("Error");
            return false;
        }
        return true;
    }

}
