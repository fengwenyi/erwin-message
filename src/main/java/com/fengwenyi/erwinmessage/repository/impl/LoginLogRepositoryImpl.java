package com.fengwenyi.erwinmessage.repository.impl;

import com.fengwenyi.erwinmessage.entity.LoginLogEntity;
import com.fengwenyi.erwinmessage.dao.ILoginLogDao;
import com.fengwenyi.erwinmessage.repository.MPLoginLogRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户登录记录表 服务实现类
 * </p>
 *
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-30
 */
@Service
public class LoginLogRepositoryImpl extends ServiceImpl<ILoginLogDao, LoginLogEntity> implements MPLoginLogRepository {

}
