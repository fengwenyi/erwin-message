package com.fengwenyi.erwinmessage.security.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019/12/25
 */
@Data
@Accessors(chain = true)
public class UserEntity {

    private String uid;

    private String username;

    private String password;

    private String nickname;

    /** 角色 */
    private List<String> roles;

    /** 账户没有被锁定 */
    private boolean accountNonLocked;

}
