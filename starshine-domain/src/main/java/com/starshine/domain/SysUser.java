package com.starshine.domain;

import lombok.Data;
import java.time.OffsetDateTime;

/**
 * 系统用户
 * @author starshine
 */
@Data
public class SysUser extends EntityBaseTenantOrg {
    /**
     * 用户名
     */
    private String username;

    /**
     * 此用户的规范化用户名。
     */
    private String normalizedUsername;
    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private  String name;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 此用户的规范化电子邮箱。
     */
    private String normalizedEmail;

    /**
     * 电子邮箱已确认
     */
    private boolean emailConfirmed;

    /**
     * 密码哈希
     */
    private String passwordHash;

    /**
     * 每当用户凭证发生改变（密码更改、登录删除）时必须更改的随机值
     */
    private String securityStamp;

    /**
     * 是否外部用户
     */
    private boolean external;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 手机号码已确认
     */
    private boolean phoneNumberConfirmed;

    /**
     * 状态
     * 0=正常,1=停用
     */
    private int status;

    /**
     * 锁定
     */
    private boolean lockoutEnabled;

    /**
     * 锁定到期时间
     */
    private OffsetDateTime lockoutEnd;

    /**
     * 访问失败次数
     */
    private int accessFailedCount;

    /**
     * 下次登录时应更改密码。
     */
    private boolean shouldChangePassword;

    /**
     * 最后一次密码更改时间
     */
    private OffsetDateTime lastPasswordChangeTime;

    /**
     * 最后一次登录ip
     */
    private String lastLoginIp;

    /**
     * 最后一次登录位置
     */
    private String lastLoginLocation;

    /**
     * 最后一次登录时间
     */
    private OffsetDateTime lastLoginTime;

    /**
     * 最后一次登录设备
     */
    private String lastLoginDevice;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户类型
     */
    private String userType;
}
