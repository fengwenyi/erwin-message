package com.fengwenyi.erwinmessage.repository.impl;

import com.fengwenyi.erwinmessage.entity.UserRoleEntity;
import com.fengwenyi.erwinmessage.dao.IUserRoleDao;
import com.fengwenyi.erwinmessage.repository.MPUserRoleRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户-角色表 服务实现类
 * </p>
 *
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-30
 */
@Service
public class UserRoleRepositoryImpl extends ServiceImpl<IUserRoleDao, UserRoleEntity> implements MPUserRoleRepository {

}
