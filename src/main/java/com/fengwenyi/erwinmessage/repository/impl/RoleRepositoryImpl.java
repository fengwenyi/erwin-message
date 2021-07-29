package com.fengwenyi.erwinmessage.repository.impl;

import com.fengwenyi.erwinmessage.entity.RoleEntity;
import com.fengwenyi.erwinmessage.dao.IRoleDao;
import com.fengwenyi.erwinmessage.repository.MPRoleRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-30
 */
@Service
public class RoleRepositoryImpl extends ServiceImpl<IRoleDao, RoleEntity> implements MPRoleRepository {

}
