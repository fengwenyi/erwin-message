package com.fengwenyi.erwinmessage.index.controller;

import com.fengwenyi.api.result.ResultTemplate;
import com.fengwenyi.erwinmessage.entity.UserEntity;
import com.fengwenyi.erwinmessage.repository.MPUserRepository;
import com.fengwenyi.erwinmessage.vo.request.UserRegisterRequestVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-31
 */
@Tag(name = "认证 API")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private MPUserRepository mpUserRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setMpUserRepository(MPUserRepository mpUserRepository) {
        this.mpUserRepository = mpUserRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    // 注册
    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public ResultTemplate<?> register(@Validated @RequestBody UserRegisterRequestVo requestVo) {
        UserEntity userEntity = new UserEntity()
                .setErwinId(requestVo.getAccount())
                .setPassword(passwordEncoder.encode(requestVo.getPassword()))
                ;
        mpUserRepository.save(userEntity);
        return ResultTemplate.fail();
    }

}
