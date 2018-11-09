package com.be.www.utilslib.device;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * Created by frankliu on 2018/11/9.
 * 不同设备类型进行不同处理
 */

class Device {
    public static class Hawei {
        /**
         * 是否有刘海屏
         *
         * @param context
         * @return
         */
        protected static boolean isHasNotch(Context context) {
            boolean ret = false;
            try {
                ClassLoader cl              = context.getClassLoader();
                Class       HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
                Method      get             = HwNotchSizeUtil.getMethod("hasNotchInScreen");
                ret = (boolean) get.invoke(HwNotchSizeUtil);
            } catch (ClassNotFoundException e) {
                Log.e("test", "hasNotchInScreen ClassNotFoundException");
            } catch (NoSuchMethodException e) {
                Log.e("test", "hasNotchInScreen NoSuchMethodException");
            } catch (Exception e) {
                Log.e("test", "hasNotchInScreen Exception");
            } finally {
                return ret;
            }
        }

        /**
         * 刘海屏尺寸
         *
         * @param context
         * @return
         */
        protected static int[] getNotchSize(Context context) {
            int[] ret = new int[]{0, 0};
            try {
                ClassLoader cl              = context.getClassLoader();
                Class       HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
                Method      get             = HwNotchSizeUtil.getMethod("getNotchSize");
                ret = (int[]) get.invoke(HwNotchSizeUtil);
            } catch (ClassNotFoundException e) {
                Log.e("test", "getNotchSize ClassNotFoundException");
            } catch (NoSuchMethodException e) {
                Log.e("test", "getNotchSize NoSuchMethodException");
            } catch (Exception e) {
                Log.e("test", "getNotchSize Exception");
            } finally {
                return ret;
            }
        }
    }

    public static class Oppo {
        /**
         * 是否有刘海屏
         *
         * @param context
         * @return
         */
        protected static boolean isHasNotch(Context context) {
            return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
        }
    }

    public static class Vivo {
        public static final int NOTCH_IN_SCREEN_VOIO   = 0x00000020;//是否有凹槽
        public static final int ROUNDED_IN_SCREEN_VOIO = 0x00000008;//是否有圆角

        protected static boolean isHasNotchIn(Context context) {
            boolean ret = false;
            try {
                ClassLoader cl        = context.getClassLoader();
                Class       FtFeature = cl.loadClass("com.util.FtFeature");
                Method      get       = FtFeature.getMethod("isFeatureSupport", int.class);
                ret = (boolean) get.invoke(FtFeature, NOTCH_IN_SCREEN_VOIO);

            } catch (ClassNotFoundException e) {
                Log.e("test", "hasNotchInScreen ClassNotFoundException");
            } catch (NoSuchMethodException e) {
                Log.e("test", "hasNotchInScreen NoSuchMethodException");
            } catch (Exception e) {
                Log.e("test", "hasNotchInScreen Exception");
            } finally {
                return ret;
            }
        }
    }

    public static class Xiaomi {

    }
}
