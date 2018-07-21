package org.foo.sandbox.data.model.service;

import org.foo.sandbox.data.model.converter.FromPostEntity;
import org.foo.sandbox.data.model.converter.ToPostEntity;
import org.foo.sandbox.data.model.dto.PostDto;
import org.foo.sandbox.data.model.entity.PostEntity;
import org.foo.sandbox.data.model.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService extends AbstractGenericService<PostDto, PostEntity, Long> {

    @Autowired
    public PostService(PostRepo repo, ToPostEntity toEntity, FromPostEntity fromEntity) {
        super(repo, toEntity, fromEntity);
    }
}
