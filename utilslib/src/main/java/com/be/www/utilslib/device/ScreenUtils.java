package com.be.www.utilslib.device;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by frankliu on 2018/11/9.
 */

public class ScreenUtils {

    public static float dp2Px(Context context, float dp) {
        return dp * DeviceUtils.getMetrics(context).density;
    }

    public static boolean isHasNavBar(Activity activity) {
        return DeviceUtils.checkNavigationBar(activity) && DeviceUtils.isHasNavigationBar(activity);
    }

    public static boolean isHasNotch(Context context) {
        return DeviceUtils.isHasNotch(context);
    }

    public static int getScreenWidth(Context context) {
        return DeviceUtils.getMetrics(context).widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return DeviceUtils.getMetrics(context).heightPixels;
    }

    public static int getNavBarHeight(Context context) {
        return DeviceUtils.getNavigationBarHeight(context);
    }

    public static int getStatusBarHeight(Context context) {
        return DeviceUtils.getStatusBarHeight(context);
    }

    public static int getSystemBarHeight(Context context) {
        return DeviceUtils.getSystemBarHeight(context);
    }

    public static void setStatusBarColor(Activity activity, @ColorRes int colorId) {
        StatusBarUtils.setStatusBarColor(activity, colorId);
    }

    public static void statusBarLight(Activity activity) {
        StatusBarUtils.StatusBarLightMode(activity);
    }

    public static void statusBarDark(Activity activity) {
    }

    public static void hideSoftInputForce(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void hideSoftInputType(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
    }


    public static int[] getViewLocation(View view) {
        //获取需要在其上方显示的控件的位置信息
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        return location;
    }

    public static void setScreenAlpha(Activity activity, float alpha) {
        WindowManager.LayoutParams params = activity.getWindow().getAttributes();
        params.alpha = alpha;
        activity.getWindow().setAttributes(params);
    }

    public static void fullScreen(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window    = activity.getWindow();
            View   decorView = window.getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            //                window.setNavigationBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window                     window                    = activity.getWindow();
            WindowManager.LayoutParams attributes                = window.getAttributes();
            int                        flagTranslucentStatus     = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            int                        flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
            attributes.flags |= flagTranslucentStatus;
            //                attributes.flags |= flagTranslucentNavigation;
            window.setAttributes(attributes);
        }
    }
}
