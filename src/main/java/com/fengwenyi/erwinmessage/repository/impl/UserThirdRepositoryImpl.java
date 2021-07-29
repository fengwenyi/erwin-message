package com.fengwenyi.erwinmessage.repository.impl;

import com.fengwenyi.erwinmessage.entity.UserThirdEntity;
import com.fengwenyi.erwinmessage.dao.IUserThirdDao;
import com.fengwenyi.erwinmessage.repository.MPUserThirdRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 第三方用户表 服务实现类
 * </p>
 *
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-30
 */
@Service
public class UserThirdRepositoryImpl extends ServiceImpl<IUserThirdDao, UserThirdEntity> implements MPUserThirdRepository {

}
