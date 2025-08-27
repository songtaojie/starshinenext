package com.starshine.infrastructure.persistence.converter;

import com.starshine.domain.model.user.PhoneNumber;
import com.starshine.domain.model.user.SysUser;
import com.starshine.domain.model.user.Email;
import com.starshine.infrastructure.persistence.po.SysUserPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConverter {
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);
    @Mapping(target = "email", qualifiedByName = "toEmail")
    SysUser toDomain(SysUserPO po);

    // 👇 提取为独立方法，用 @Named 标记
    @Named("toEmail")
    default Email toEmail(SysUserPO po) {
        return Email.of(po.getEmail(), po.isEmailConfirmed());
    }

    @Named("toPhoneNumber")
    default PhoneNumber toPhoneNumber(SysUserPO po) {
        return PhoneNumber.ofAndConfirmed(po.getPhoneNumber(), po.isPhoneNumberConfirmed());
    }

    @Named("toPassword")
    default Password toPassword(SysUserPO po) {
        return Password.restore(po.getPasswordHash());
    }

}
