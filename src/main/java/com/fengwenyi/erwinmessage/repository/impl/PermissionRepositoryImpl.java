package com.fengwenyi.erwinmessage.repository.impl;

import com.fengwenyi.erwinmessage.entity.PermissionEntity;
import com.fengwenyi.erwinmessage.dao.IPermissionDao;
import com.fengwenyi.erwinmessage.repository.MPPermissionRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-30
 */
@Service
public class PermissionRepositoryImpl extends ServiceImpl<IPermissionDao, PermissionEntity> implements MPPermissionRepository {

}
