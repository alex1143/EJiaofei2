package com.dxkj.ejiaofei.ejiaofei.utils;

import android.content.Context;
import android.view.WindowManager;

/**
 * 应用工具类
 * Created by dxkj on 2015/9/29.
 */
public class AppUtils {
    /**
     * 获取屏幕分辨率
     * @param context
     * @return
     */
    public static int[] getScreenDispaly(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = windowManager.getDefaultDisplay().getWidth();// 手机屏幕的宽度
        int height = windowManager.getDefaultDisplay().getHeight();// 手机屏幕的高度
        int result[] = { width, height };
        return result;
    }
}
