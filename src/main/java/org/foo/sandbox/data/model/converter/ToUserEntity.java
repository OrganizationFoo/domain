package org.foo.sandbox.data.model.converter;

import org.foo.sandbox.data.model.dto.UserDto;
import org.foo.sandbox.data.model.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ToUserEntity implements Converter<UserDto, UserEntity> {

    @Override
    public UserEntity convert(UserDto user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setEmail(user.getEmail());
        userEntity.setName(user.getName());
        userEntity.setVerified(user.getVerified());
        return userEntity;
    }
}
