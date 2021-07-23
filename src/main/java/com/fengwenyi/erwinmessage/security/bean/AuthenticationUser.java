package com.fengwenyi.erwinmessage.security.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019/12/27
 */
@Setter
@Getter
public class AuthenticationUser extends UsernamePasswordAuthenticationToken {

    private String uid;


    public AuthenticationUser(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public AuthenticationUser(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
