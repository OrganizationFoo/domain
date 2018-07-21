package org.foo.sandbox.data.model.service;

import org.foo.sandbox.data.model.converter.FromUserEntity;
import org.foo.sandbox.data.model.converter.ToUserEntity;
import org.foo.sandbox.data.model.dto.UserDto;
import org.foo.sandbox.data.model.entity.UserEntity;
import org.foo.sandbox.data.model.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractGenericService<UserDto, UserEntity, Long> {

    @Autowired
    public UserService(UserRepo repo, ToUserEntity toEntity, FromUserEntity fromEntity) {
        super(repo, toEntity, fromEntity);
    }
}
