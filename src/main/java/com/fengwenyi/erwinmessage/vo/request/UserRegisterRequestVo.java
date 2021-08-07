package com.fengwenyi.erwinmessage.vo.request;

import lombok.Data;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-08-08
 */
@Data
public class UserRegisterRequestVo {

    private String mailCode;

    private String account;

    private String password;
}
