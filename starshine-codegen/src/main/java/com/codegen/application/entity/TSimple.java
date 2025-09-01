package com.codegen.application.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author baomidou
 * @since 2025-08-28
 */
@Getter
@Setter
@ToString
@TableName("t_simple")
public class TSimple implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 名字
     */
    private String name;

    private Short age;

    private Integer deleteFlag;

    private Integer deleted;

    private Integer version;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
