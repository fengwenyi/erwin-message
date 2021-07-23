package com.fengwenyi.erwinmessage.security.util;

import java.time.LocalDateTime;

/**
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019/12/27
 */
public class DateTimeUtils {

    /**
     * 比较两个时间大小，简言之，before < after 是否成立
     * <p>
     *     假定有两个时间，before和after，如果before小于after，返回 {@code true }，
     *     反之，返回 {@code false}
     * </p>
     * @param before 小的是
     * @param after 大的时间
     * @return before < after 是否成立
     */
    public static Boolean isBefore(LocalDateTime before, LocalDateTime after) {
        return before.isBefore(after);
    }

}
