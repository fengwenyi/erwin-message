package com.fengwenyi.erwinmessage.repository.impl;

import com.fengwenyi.erwinmessage.entity.RolePermissionEntity;
import com.fengwenyi.erwinmessage.dao.IRolePermissionDao;
import com.fengwenyi.erwinmessage.repository.MPRolePermissionRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色-权限表 服务实现类
 * </p>
 *
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-30
 */
@Service
public class RolePermissionRepositoryImpl extends ServiceImpl<IRolePermissionDao, RolePermissionEntity> implements MPRolePermissionRepository {

}
