package org.foo.sandbox.data.model.converter;

import org.foo.sandbox.data.model.dto.UserDto;
import org.foo.sandbox.data.model.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FromUserEntity implements Converter<UserEntity, UserDto> {

    @Override
    public UserDto convert(UserEntity userEntity) {
        UserDto user = new UserDto();
        user.setId(userEntity.getId());
        user.setEmail(userEntity.getEmail());
        user.setName(userEntity.getName());
        user.setVerified(userEntity.getVerified());
        return user;
    }
}
