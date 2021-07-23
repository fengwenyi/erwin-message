package com.fengwenyi.erwinmessage.security.access;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fengwenyi.mount.db.model.PermissionModel;
import com.fengwenyi.mount.db.model.RoleModel;
import com.fengwenyi.mount.db.model.RolePermissionModel;
import com.fengwenyi.mount.db.service.MPPermissionService;
import com.fengwenyi.mount.db.service.MPRolePermissionService;
import com.fengwenyi.mount.db.service.MPRoleService;
import com.fengwenyi.mount.enums.DeleteStatusEnum;
import com.fengwenyi.mount.enums.ReleaseStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author Erwin Feng
 * @since 2020/3/27 23:33
 */
@Slf4j
@Component
public class MyPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private MPRolePermissionService mpRolePermissionService;

    @Autowired
    private MPPermissionService mpPermissionService;

    @Autowired
    private MPRoleService mpRoleService;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        log.info("url: {}, permission: {}", targetDomainObject, permission);
        // 获得loadUserByUsername()方法的结果
        User user = (User)authentication.getPrincipal();
        // 获得loadUserByUsername()中注入的角色
        Collection<GrantedAuthority> authorities = user.getAuthorities();

        // 遍历用户所有角色
        for(GrantedAuthority authority : authorities) {
            String roleName = authority.getAuthority();
            List<RoleModel> roleModelList = mpRoleService.list(
                    new LambdaQueryWrapper<RoleModel>()
                            .eq(RoleModel::getRole, roleName)
                            .eq(RoleModel::getDeleteStatus, DeleteStatusEnum.NORMAL.getCode())
                            .eq(RoleModel::getReleaseStatus, ReleaseStatusEnum.RELEASE.getCode()));
            if (roleModelList.size() == 1) {
                RoleModel roleModel = roleModelList.get(0);
                if (roleModel != null && roleModel.getId() != null) {
                    List<RolePermissionModel> rolePermissionModelList = mpRolePermissionService.list(
                            new LambdaQueryWrapper<RolePermissionModel>()
                                    .eq(RolePermissionModel::getRoleId, roleModel.getId())
                                    .eq(RolePermissionModel::getDeleteStatus, DeleteStatusEnum.NORMAL.getCode())
                                    .eq(RolePermissionModel::getReleaseStatus, ReleaseStatusEnum.RELEASE.getCode()));
                    for (RolePermissionModel rolePermissionModel : rolePermissionModelList) {
                        PermissionModel permissionModel = mpPermissionService.getById(rolePermissionModel.getPermissionId());
                        if (permissionModel.getDeleteStatus().equals(DeleteStatusEnum.NORMAL.getCode())
                                && permissionModel.getReleaseStatus().equals(ReleaseStatusEnum.RELEASE.getCode())) {
                            // 如果访问的Url和权限用户符合的话，返回true
                            if(targetDomainObject.equals("")
                                    && permission.equals("")) {
                                return true;
                            }
                        }
                    }
                }
            }

        }

        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
