package com.hy.cascade.utils;

import android.text.TextUtils;

/**
 * @author:MtBaby
 * @date:2020/06/18 16:24
 * @desc:
 */
public class StringUtil {
    public static String isEmpty(String label) {
        return TextUtils.isEmpty(label) ? "" : label;
    }
}
