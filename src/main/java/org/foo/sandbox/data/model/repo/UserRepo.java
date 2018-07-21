package org.foo.sandbox.data.model.repo;

import org.foo.sandbox.data.model.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepo extends PagingAndSortingRepository<UserEntity, Long> {
}
