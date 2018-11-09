package com.be.www.utilslib.device;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;

import java.lang.reflect.Method;

/**
 * Created by frankliu on 2018/11/9.
 */

class DeviceUtils {
    protected static DisplayMetrics getMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }

    /**
     * 判断是否存在navigationbar
     */
    protected static boolean checkNavigationBar(Context context) {
        Context   mContext         = context.getApplicationContext();
        boolean   hasNavigationBar = false;
        Resources resources        = mContext.getResources();
        int       id               = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = resources.getBoolean(id);
        }
        try {
            Class  systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m                     = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride        = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {
        }
        return hasNavigationBar;
    }

    protected static boolean isHasNavigationBar(Activity activity) {
        int   dpi   = getDpi(activity);
        Point point = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(point);
        int dh = point.y;
        return dpi != dh;
    }

    /**
     * 获取带带导航栏的屏幕高度
     *
     * @param activity
     * @return
     */
    private static int getDpi(Activity activity) {
        int            dpi     = 0;
        Display        display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics dm      = new DisplayMetrics();
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            dpi = dm.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dpi;
    }

    /**
     * 获取导航栏高度px
     *
     * @param context
     * @return
     */
    protected static int getNavigationBarHeight(Context context) {
        Context   mContext         = context.getApplicationContext();
        Resources res              = mContext.getResources();
        int       navigationHeight = res.getIdentifier("navigation_bar_height", "dimen", "android");
        return res.getDimensionPixelSize(navigationHeight);
    }

    /**
     * 获取状态栏高度px
     *
     * @param context
     * @return
     */
    protected static int getStatusBarHeight(Context context) {
        Context   mContext     = context.getApplicationContext();
        Resources res          = mContext.getResources();
        int       statusHeight = res.getIdentifier("status_bar_height", "dimen", "android");
        return res.getDimensionPixelSize(statusHeight);
    }

    /**
     * 获取状态栏高度px
     *
     * @param context
     * @return
     */
    protected static int getSystemBarHeight(Context context) {
        Context   mContext     = context.getApplicationContext();
        Resources res          = mContext.getResources();
        int       systemHeight = res.getIdentifier("system_bar_height", "dimen", "android");
        return res.getDimensionPixelSize(systemHeight);
    }

    protected static boolean isHasNotch(Context context) {
        return Device.Hawei.isHasNotch(context) || Device.Oppo.isHasNotch(context) || Device.Vivo.isHasNotchIn(context);
    }
}
