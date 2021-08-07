package com.fengwenyi.erwinmessage.util;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-08-08
 */
public class CacheKeyUtils {

    public static String genCaptchaKey(String account) {
        return String.format("captcha-%s", account);
    }

    public static String genMailCodeKey(String account) {
        return String.format("mail-code-%s", account);
    }

}
