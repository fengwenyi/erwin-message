package com.fengwenyi.erwinmessage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户登录记录表
 * </p>
 *
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_login_log")
public class LoginLogEntity extends Model<LoginLogEntity> {

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
     * Erwin ID
     */
    @TableField("erwin_id")
    private String erwinId;

    /**
     * 登录IP
     */
    @TableField("ip")
    private String ip;

    /**
     * 登录位置（如果与注册城市不一致，则需要验证）
     */
    @TableField("position")
    private String position;

    /**
     * 使用的系统
     */
    @TableField("os")
    private String os;

    /**
     * 使用的浏览器
     */
    @TableField("browser")
    private String browser;

    /**
     * 登录结果
     */
    @TableField("login_result")
    private String loginResult;

    /**
     * 错误信息
     */
    @TableField("error_message")
    private String errorMessage;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
