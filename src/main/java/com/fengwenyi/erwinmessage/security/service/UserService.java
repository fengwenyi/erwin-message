package com.fengwenyi.erwinmessage.security.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fengwenyi.erwinmessage.entity.RoleEntity;
import com.fengwenyi.erwinmessage.entity.UserEntity;
import com.fengwenyi.erwinmessage.entity.UserRoleEntity;
import com.fengwenyi.erwinmessage.enums.ReleaseStatusEnum;
import com.fengwenyi.erwinmessage.repository.*;
import com.fengwenyi.erwinmessage.security.util.MyUserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019/12/25
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private MPUserRepository mpUserRepository;

    @Autowired
    private MPRoleRepository mpRoleRepository;

    @Autowired
    private MPPermissionRepository mpPermissionRepository;

    @Autowired
    private MPUserRoleRepository mpUserRoleRepository;

    @Autowired
    private MPRolePermissionRepository mpRolePermissionRepository;

    public com.fengwenyi.erwinmessage.security.entity.UserEntity findByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return null;
        }

        List<UserEntity> userList = mpUserRepository.list(
                new LambdaQueryWrapper<UserEntity>()
                        .eq(UserEntity::getErwinId, username)
                        .eq(UserEntity::getReleaseStatus, ReleaseStatusEnum.YES.getCode()));

        if (userList == null || userList.size() == 0) {
            return null;
        }

        if (userList.size() > 1) {
            log.error("账户重复，账号：{}", username);
            return null;
        }

        UserEntity userModel = userList.get(0);

        boolean accountNonLocked = !userModel.getLockStatus();

        com.fengwenyi.erwinmessage.security.entity.UserEntity userEntity =
                new com.fengwenyi.erwinmessage.security.entity.UserEntity()
                .setUsername(username)
                .setUid(userModel.getId() + "")
                .setPassword(userModel.getPassword())
                .setAccountNonLocked(accountNonLocked);

        List<UserRoleEntity> userRoleModelList = mpUserRoleRepository.list(
                new LambdaQueryWrapper<UserRoleEntity>()
                        .eq(UserRoleEntity::getUserId, userModel.getId())
                        .eq(UserRoleEntity::getReleaseStatus, ReleaseStatusEnum.YES.getCode()));

        // 权限
        /*
       List<String> permissions = new ArrayList<>();
        for (UserRoleModel userRoleModel : userRoleModelList) {
            RoleModel roleModel = mpRoleService.getById(userRoleModel.getRoleId());
            if (roleModel.getDeleteStatus().equals(DeleteStatusEnum.NORMAL.getCode())
                    && roleModel.getReleaseStatus().equals(ReleaseStatusEnum.RELEASE.getCode())) {
                List<RolePermissionModel> rolePermissionModelList = mpRolePermissionService.list(
                        new LambdaQueryWrapper<RolePermissionModel>()
                                .eq(RolePermissionModel::getRoleId, roleModel.getId())
                                .eq(RolePermissionModel::getDeleteStatus, DeleteStatusEnum.NORMAL.getCode())
                                .eq(RolePermissionModel::getReleaseStatus, ReleaseStatusEnum.RELEASE.getCode()));
                for (RolePermissionModel rolePermissionModel : rolePermissionModelList) {
                    PermissionModel permissionModel = mpPermissionService.getById(rolePermissionModel.getPermissionId());
                    if (permissionModel.getDeleteStatus().equals(DeleteStatusEnum.NORMAL.getCode())
                            && permissionModel.getReleaseStatus().equals(ReleaseStatusEnum.RELEASE.getCode())) {
                        permissions.add(permissionModel.getPermission());
                    }
                }
            }
        }

        userEntity.setPermissions(permissions);*/

        // 角色
        List<String> roles = new ArrayList<>();
        for (UserRoleEntity userRoleModel : userRoleModelList) {
            RoleEntity roleModel = mpRoleRepository.getById(userRoleModel.getRoleId());
            if (roleModel.getReleaseStatus()) {
                roles.add(roleModel.getRole());
            }
        }
        userEntity.setRoles(roles);
        return userEntity;
    }

    // 当前登录用户
    public UserEntity getCurrentLoginUser() {
//        AuthenticationUser loginUser = MyUserUtils.getLoginUser();


        // 这里假设用用户名查询
        // 真实环境会用uid查询
        UserDetails userDetails = MyUserUtils.getLoginUser();
        assert userDetails != null;
        String username = userDetails.getUsername();
        List<UserEntity> userModelList = mpUserRepository.list(
                new LambdaQueryWrapper<UserEntity>()
                        .eq(UserEntity::getErwinId, username)
                        .eq(UserEntity::getReleaseStatus, ReleaseStatusEnum.YES.getCode())
        );
        if (userModelList.size() == 1) return userModelList.get(0);
        return null;
    }

}
