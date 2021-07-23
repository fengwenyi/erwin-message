package com.fengwenyi.erwinmessage.security.index;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019/12/25
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/list")
    public String list() {
        return "user>list";
    }

}
