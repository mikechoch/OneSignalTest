package com.onesignal.sdktest.util;

import android.support.design.widget.TextInputLayout;
import android.util.Patterns;

import com.onesignal.sdktest.constant.Text;

public class ProfileUtil {

    public ProfileUtil() {

    }

    public boolean isEmailValid(TextInputLayout emailTextInputLayout) {
        emailTextInputLayout.setErrorEnabled(false);
        if (emailTextInputLayout.getEditText() != null) {
            String email = emailTextInputLayout.getEditText().getText().toString().trim();
            if (email.isEmpty()) {
                emailTextInputLayout.setError(Text.EMAIL_IS_REQUIRED);
                return false;
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailTextInputLayout.setError(Text.INVALID_EMAIL);
                return false;
            }
        } else {
            emailTextInputLayout.setError(Text.ERROR);
            return false;
        }
        return true;
    }

}
