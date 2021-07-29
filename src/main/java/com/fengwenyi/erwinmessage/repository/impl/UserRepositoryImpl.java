package com.fengwenyi.erwinmessage.repository.impl;

import com.fengwenyi.erwinmessage.entity.UserEntity;
import com.fengwenyi.erwinmessage.dao.IUserDao;
import com.fengwenyi.erwinmessage.repository.MPUserRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-30
 */
@Service
public class UserRepositoryImpl extends ServiceImpl<IUserDao, UserEntity> implements MPUserRepository {

}
