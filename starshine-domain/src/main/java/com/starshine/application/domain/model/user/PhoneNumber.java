package com.starshine.application.domain.model.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * 手机号
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-27 下午 周三
 */
@Value
@Builder
@AllArgsConstructor
public class PhoneNumber {
    private final String number;
    private final boolean confirmed;

    public static PhoneNumber of(String number) {
        return PhoneNumber.builder()
                .number(number)
                .confirmed(false)
                .build();
    }

    public static PhoneNumber ofAndConfirmed(String number, Boolean confirmed) {
        return PhoneNumber.builder()
                .number(number)
                .confirmed(Boolean.TRUE.equals(confirmed))
                .build();
    }
}