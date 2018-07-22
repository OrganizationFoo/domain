package org.foo.sandbox.data.controller;

import java.util.Optional;

import org.foo.sandbox.data.exception.DataInconsistencyException;
import org.foo.sandbox.data.exception.ResourceNotFoundException;
import org.foo.sandbox.data.model.Message;
import org.foo.sandbox.data.model.User;
import org.foo.sandbox.data.repository.CommentRepository;
import org.foo.sandbox.data.repository.MessageRepository;
import org.foo.sandbox.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/{userId}/messages/{messageId}/comments")
    public Page<Message> getComments(@PathVariable(value = "userId") Long userId, @PathVariable(value = "messageId") Long messageId, Pageable pageable) {

        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new ResourceNotFoundException("UserId " + userId + " not found");
        }

        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if (!optionalMessage.isPresent()) {
            throw new ResourceNotFoundException("MessageId " + messageId + " not found");
        }

        User user = optionalUser.get();
        Message message = optionalMessage.get();

        if (!message.getUser().getId().equals(user.getId())) {
            throw new DataInconsistencyException("Expecting UserId " + message.getUser().getId() + " (author) of MessageId " + messageId + ", instead found " + userId);
        }

        return commentRepository.findByMessageId(messageId, pageable);
    }
}
