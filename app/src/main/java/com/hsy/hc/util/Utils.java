package com.hsy.hc.util;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者:liupeng
 * 16/9/22 15:30
 * 注释: 公共方法
 */
public class Utils {

    /**
     * 验证手机号
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-3,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 手机号码加短线
     *
     * @param phone_num
     * @return
     */
    public static String Phone(String phone_num) {
        String phones = "";
        if (TextUtils.isEmpty(phone_num)) {
            return "-";
        }

        if (phone_num.contains("-")) {
            return phone_num;
        }

        if (phone_num.length() >= 11) {
            phones = phone_num.substring(0, 3) + "-" + phone_num.substring(3, 7) + "-" + phone_num.substring(7, phone_num.length());
        } else if (phone_num.length() == 10) {
            phones = phone_num.substring(0, 3) + "-" + phone_num.substring(3, 6) + "-" + phone_num.substring(6, phone_num.length());
        } else if (phone_num.length() >= 7) {
            phones = phone_num.substring(0, 3) + "-" + phone_num.substring(3, phone_num.length());
        } else if (phone_num.length() >= 3) {
            phones = phone_num;
        }
        return phones;
    }
}
