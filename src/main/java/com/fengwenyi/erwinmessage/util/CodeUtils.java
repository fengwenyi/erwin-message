package com.fengwenyi.erwinmessage.util;

import com.fengwenyi.javalib.generate.MathUtils;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-08-08
 */
public class CodeUtils {

    public static String gen(int len) {
        String str = "0123456789qazwsxedcvfrtgbnhyujmkiolp";
        double randomNum = MathUtils.randomNum(len - 1, str.length() - len);
        int start = Double.valueOf(randomNum).intValue();
        return str.substring(start, start + len);
    }

}
