package com.fengwenyi.erwinmessage.enums;

import lombok.Getter;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-30
 */
@Getter
public enum ReleaseStatusEnum {

    YES(true, "启用")
    , NO(false, "不启用")

    ;

    private final Boolean code;

    private final String desc;

    ReleaseStatusEnum(boolean code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
