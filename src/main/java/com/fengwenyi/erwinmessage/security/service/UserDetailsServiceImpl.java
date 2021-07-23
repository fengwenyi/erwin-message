package com.fengwenyi.erwinmessage.security.service;

import com.fengwenyi.mount.security.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019/12/25
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity userEntity = userService.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException(String.format("%s.这个用户不存在", username));
        }
        List<SimpleGrantedAuthority> authorities = userEntity.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//        return new User(userEntity.getUsername(), userEntity.getPassword(), authorities);
        return new User(username, userEntity.getPassword(), true, true, true, userEntity.isAccountNonLocked(), authorities);
    }
}
