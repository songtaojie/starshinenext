package com.starshine.application.domain.interfaces;

import com.starshine.application.common.data.IHasExtraInfo;
import com.starshine.application.common.entities.IAggregateRoot;
import com.starshine.application.common.entities.IMultiTenant;
import com.starshine.application.domain.model.user.Email;
import com.starshine.application.domain.model.user.PhoneNumber;

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
    Email getEmail();

    /**
     * 获取手机号
     * @return 手机号
     */
    PhoneNumber getPhoneNumber();
}
