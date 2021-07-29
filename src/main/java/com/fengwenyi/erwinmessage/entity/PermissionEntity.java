package com.fengwenyi.erwinmessage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_permission")
public class PermissionEntity extends Model<PermissionEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 创建者ID
     */
    @TableField("create_user_id")
    private Long createUserId;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 修改者ID
     */
    @TableField("update_user_id")
    private Long updateUserId;

    /**
     * 乐观锁
     */
    @TableField("version")
    @Version
    private Integer version;

    /**
     * 启用状态，0：不启用；1：启用
     */
    @TableField("release_status")
    private Boolean releaseStatus;

    /**
     * 删除状态，0：正常；1：删除
     */
    @TableField("delete_status")
    @TableLogic
    private Boolean deleteStatus;

    /**
     * 权限
     */
    @TableField("permission")
    private String permission;

    /**
     * URL
     */
    @TableField("url")
    private String url;

    /**
     * 说明
     */
    @TableField("remarks")
    private String remarks;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
