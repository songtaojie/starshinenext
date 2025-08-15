package com.starshine.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 删除基础类
 * @author songtaojie
 */
@Data
public class EntityBaseDel extends EntityBase {

    /**
     * 是否删除
     */
    private boolean isDelete;

    /**
     * 删除时间
     */
    private LocalDateTime DeleteTime;

    /**
     * 删除用户
     */
    private Long deleteUserId;

    /**
     * 删除用户名称
     */
    private String deleteUserName;
}
