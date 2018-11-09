package com.be.www.utilslib.device;

import android.app.Activity;
import android.content.Context;
import android.view.View;

/**
 * Created by frankliu on 2018/11/9.
 */

public interface IScreenUtils {
    float dp2Px(Context context, float dp);

    boolean isHasNavBar(Activity activity);

    boolean isHasNotch(Context context);

    int getScreenWidth(Context context);

    int getScreenHeight(Context context);

    int getNavBarHeight(Context context);

    int getStatusBarHeight(Context context);

    int getSystemBarHeight(Context context);

    int[] getViewLocation(View view);

    void fullScreen(Activity activity);

    void setScreenAlpha(Activity activity, float alpha);

    void hideSoftInputForce(Context context);

    void setSoftInputType(View view);
}
