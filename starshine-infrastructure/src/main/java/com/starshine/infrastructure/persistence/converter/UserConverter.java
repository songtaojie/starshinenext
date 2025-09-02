package com.starshine.infrastructure.persistence.converter;

import com.starshine.domain.user.PhoneNumber;
import com.starshine.domain.user.User;
import com.starshine.domain.user.Email;
import com.starshine.infrastructure.persistence.po.SysUserPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

/**
 * 用户转换器
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-30 下午 周六
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

    @Mappings({
        @Mapping(target = "email",source = ".", qualifiedByName = "toEmail"),
        @Mapping(target = "phoneNumber",source = ".", qualifiedByName = "toPhoneNumber")
    })
    User toDomain(SysUserPO po);

    /**
     * 转换邮箱
     * @param po
     * @return
     */
    @Named("toEmail")
    default Email toEmail(SysUserPO po) {
        return Email.of(po.getEmail(), po.isEmailConfirmed());
    }

    /**
     * 转换手机号
     * @param po
     * @return
     */
    @Named("toPhoneNumber")
    default PhoneNumber toPhoneNumber(SysUserPO po) {
        return PhoneNumber.ofAndConfirmed(po.getPhoneNumber(), po.isPhoneNumberConfirmed());
    }

    @Mappings({
            @Mapping(source = "email.address", target = "email"),
            @Mapping(source = "email.confirmed", target = "emailConfirmed"),
            @Mapping(source = "phoneNumber.number", target = "phoneNumber"),
            @Mapping(source = "phoneNumber.confirmed", target = "phoneNumberConfirmed"),
    })
    SysUserPO toPO(User domain);
}
