package com.starshine.interfaces;

import com.starshine.common.data.IHasExtraInfo;
import com.starshine.common.entities.IAggregateRoot;
import com.starshine.common.entities.IMultiTenant;

/**
 * 用户接口
 */
public interface IUser extends IAggregateRoot, IMultiTenant, IHasExtraInfo {
    /**
     * 获取用户名
     * @return 用户名
     */
    String getUsername();

    /**
     * 获取用户名
     * @return 用户名
     */
    String getName();

    /**
     * 获取邮箱
     * @return 邮箱
     */
    String getEmail();

    /**
     * 获取手机号
     * @return 手机号
     */
    String getPhoneNumber();
}
