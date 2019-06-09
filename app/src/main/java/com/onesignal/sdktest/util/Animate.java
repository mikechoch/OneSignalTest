package com.onesignal.sdktest.util;

import android.view.View;

public class Animate {

    public Animate() {
    }

    public void toggleAnimationView(boolean showAnimation, View view, View anim) {
        int viewVis = showAnimation ? View.INVISIBLE : View.VISIBLE;
        int animVis = showAnimation ? View.VISIBLE : View.INVISIBLE;

        view.setVisibility(viewVis);
        anim.setVisibility(animVis);
    }

}
