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
 * 第三方用户表
 * </p>
 *
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user_third")
public class UserThirdEntity extends Model<UserThirdEntity> {

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
     * User ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * Third ID
     */
    @TableField("third_id")
    private String thirdId;

    /**
     * 第三方。1：QQ；2：微信；3：Github；4：Gitee；5：支付宝；6：Google；7：
     */
    @TableField("third_type")
    private Integer thirdType;

    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 性别；0：未知，默认；1：男；2：女
     */
    @TableField("sex")
    private Boolean sex;

    /**
     * 城市
     */
    @TableField("city")
    private String city;

    /**
     * 头像
     */
    @TableField("head_url")
    private String headUrl;

    /**
     * 锁定状态，0：正常；1：锁定
     */
    @TableField("lock_status")
    private Boolean lockStatus;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
