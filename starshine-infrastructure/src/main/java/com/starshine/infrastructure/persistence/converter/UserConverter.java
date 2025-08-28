package com.starshine.infrastructure.persistence.converter;

import com.starshine.domain.model.user.PhoneNumber;
import com.starshine.domain.model.user.SysUser;
import com.starshine.domain.model.user.Email;
import com.starshine.infrastructure.persistence.po.SysUserPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConverter {
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    @Mappings({
        @Mapping(target = "email", qualifiedByName = "toEmail"),
        @Mapping(target = "phoneNumber", qualifiedByName = "toPhoneNumber")
    })
    SysUser toDomain(SysUserPO po);

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
            @Mapping(source = "phoneNumber.confirmed", target = "phoneConfirmed"),
    })
    SysUserPO toPO(SysUser domain);
}
