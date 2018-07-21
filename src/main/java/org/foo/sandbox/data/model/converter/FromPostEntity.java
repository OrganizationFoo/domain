package org.foo.sandbox.data.model.converter;

import org.foo.sandbox.data.model.dto.PostDto;
import org.foo.sandbox.data.model.entity.PostEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FromPostEntity implements Converter<PostEntity, PostDto> {

    @Override
    public PostDto convert(PostEntity postEntity) {
        PostDto post = new PostDto();
        post.setId(postEntity.getId());
        post.setMessage(postEntity.getMessage());
        return post;
    }
}
