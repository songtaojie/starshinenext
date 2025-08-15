package com.starshine.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 框架实体基类Id
 * @author songtaojie
 */
@Data
public abstract class EntityBaseId implements Serializable {
    private long Id;
}



