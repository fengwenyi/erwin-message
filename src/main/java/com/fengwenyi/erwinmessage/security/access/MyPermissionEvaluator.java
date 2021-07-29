package com.fengwenyi.erwinmessage.security.access;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fengwenyi.erwinmessage.entity.PermissionEntity;
import com.fengwenyi.erwinmessage.entity.RoleEntity;
import com.fengwenyi.erwinmessage.entity.RolePermissionEntity;
import com.fengwenyi.erwinmessage.enums.DeleteStatusEnum;
import com.fengwenyi.erwinmessage.enums.ReleaseStatusEnum;
import com.fengwenyi.erwinmessage.repository.MPPermissionRepository;
import com.fengwenyi.erwinmessage.repository.MPRolePermissionRepository;
import com.fengwenyi.erwinmessage.repository.MPRoleRepository;
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
    private MPRolePermissionRepository mpRolePermissionRepository;

    @Autowired
    private MPPermissionRepository mpPermissionRepository;

    @Autowired
    private MPRoleRepository mpRoleRepository;

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
            List<RoleEntity> roleModelList = mpRoleRepository.list(
                    new LambdaQueryWrapper<RoleEntity>()
                            .eq(RoleEntity::getRole, roleName)
                            .eq(RoleEntity::getReleaseStatus, ReleaseStatusEnum.YES.getCode()));
            if (roleModelList.size() == 1) {
                RoleEntity roleModel = roleModelList.get(0);
                if (roleModel != null && roleModel.getId() != null) {
                    List<RolePermissionEntity> rolePermissionModelList = mpRolePermissionRepository.list(
                            new LambdaQueryWrapper<RolePermissionEntity>()
                                    .eq(RolePermissionEntity::getRoleId, roleModel.getId())
                                    .eq(RolePermissionEntity::getReleaseStatus, ReleaseStatusEnum.YES.getCode()));
                    for (RolePermissionEntity rolePermissionModel : rolePermissionModelList) {
                        PermissionEntity permissionModel = mpPermissionRepository.getById(rolePermissionModel.getPermissionId());
                        if (permissionModel.getReleaseStatus()) {
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
