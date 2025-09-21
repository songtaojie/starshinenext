package com.starshine.domain.user;

import com.starshine.common.utils.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * 邮箱
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-27 下午 周三
 */
@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Email {
    private final String address;
    /**
     * -- GETTER --
     * 获取Email是否已确认
     * @return
     */
    private final boolean confirmed;

    /**
     * 创建Email
     * @param address
     * @param confirmed
     * @return
     */
    public static Email of(String address, boolean confirmed) {
        return new Email(validateAndNormalize(address), confirmed);
    }

    private static String validateAndNormalize(String address) {
        if (StringUtils.isEmpty(address)) {
            throw new IllegalArgumentException("Email address cannot be null or empty");
        }
        if (!address.matches("^[\\w.-]+@([\\w-]+\\.)+[\\w-]{2,}$")) {
            throw new IllegalArgumentException("Invalid email format: " + address);
        }
        return address.trim().toLowerCase();
    }

    public Email confirm() {
        if (confirmed) {
            return this;
        }
        return new Email(address, true);
    }

    public Email unconfirm() {
        if (!confirmed) {
            return this;
        }
        return new Email(address, false);
    }

    @Override
    public String toString() {
        return address + (confirmed ? " [verified]" : " [unverified]");
    }
}
