package org.foo.sandbox.data.repository;

import org.foo.sandbox.data.model.Comment;
import org.foo.sandbox.data.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Message> findByMessageId(Long messageId, Pageable pageable);

    Page<Message> findByUserId(Long userId, Pageable pageable);

}
