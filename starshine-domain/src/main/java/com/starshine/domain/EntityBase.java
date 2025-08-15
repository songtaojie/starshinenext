package com.starshine.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 实体基类
 * @author songtaojie
 */
@Data
public class  EntityBase extends EntityBaseId{

    /**
     * 创建时间
     */
    private LocalDateTime CreateTime;

    /**
     * 更新时间
     */
    private LocalDateTime UpdateTime;

    /**
     * 创建用户
     */
    private Long createUserId;

    /**
     * 更新用户
     */
    private Long updateUserId;

    /**
     * 创建用户名称
     */
    private String createUserName;

    /**
     * 更新用户名称
     */
    private String updateUserName;
}

