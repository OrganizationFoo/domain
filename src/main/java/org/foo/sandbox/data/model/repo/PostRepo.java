package org.foo.sandbox.data.model.repo;

import org.foo.sandbox.data.model.entity.PostEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepo extends PagingAndSortingRepository<PostEntity, Long> {
}
