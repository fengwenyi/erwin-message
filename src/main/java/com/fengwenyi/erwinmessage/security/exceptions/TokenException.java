package com.fengwenyi.erwinmessage.security.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019/12/27
 */
public class TokenException extends AuthenticationException {

    public TokenException(String msg) {
        super(msg);
    }
}
