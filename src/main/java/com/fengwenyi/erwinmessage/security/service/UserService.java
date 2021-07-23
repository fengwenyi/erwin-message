package com.fengwenyi.erwinmessage.security.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fengwenyi.mount.db.model.UserModel;
import com.fengwenyi.mount.db.service.MPUserService;
import com.fengwenyi.mount.enums.DeleteStatusEnum;
import com.fengwenyi.mount.enums.ReleaseStatusEnum;
import com.fengwenyi.mount.security.bean.AuthenticationUser;
import com.fengwenyi.mount.security.entity.UserEntity;
import com.fengwenyi.mount.security.util.MyUserUtils;
import com.fengwenyi.mount.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019/12/25
 */
@Service
public class UserService {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private MPUserService mpUserService;

    public UserEntity findByUsername(String username) {
        return iUserService.findByUsername(username);
    }

    // 当前登录用户
    public UserModel getCurrentLoginUser() {
//        AuthenticationUser loginUser = MyUserUtils.getLoginUser();


        // 这里假设用用户名查询
        // 真实环境会用uid查询
        UserDetails userDetails = MyUserUtils.getLoginUser();
        assert userDetails != null;
        String username = userDetails.getUsername();
        List<UserModel> userModelList = mpUserService.list(
                new LambdaQueryWrapper<UserModel>()
                        .eq(UserModel::getErwinId, username)
                        .eq(UserModel::getDeleteStatus, DeleteStatusEnum.NORMAL.getCode())
                        .eq(UserModel::getReleaseStatus, ReleaseStatusEnum.RELEASE.getCode())
        );
        if (userModelList.size() == 1) return userModelList.get(0);
        return null;
    }

}
