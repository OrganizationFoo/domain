package org.foo.sandbox.data.model.converter;

import org.foo.sandbox.data.model.dto.PostDto;
import org.foo.sandbox.data.model.entity.PostEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ToPostEntity implements Converter<PostDto, PostEntity> {

    @Override
    public PostEntity convert(PostDto post) {
        PostEntity postEntity = new PostEntity();
        postEntity.setId(post.getId());
        postEntity.setMessage(post.getMessage());
        return postEntity;
    }
}
