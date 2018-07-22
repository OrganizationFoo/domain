package org.foo.sandbox.data.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.foo.sandbox.data.exception.DataInconsistencyException;
import org.foo.sandbox.data.exception.ResourceNotFoundException;
import org.foo.sandbox.data.model.Message;
import org.foo.sandbox.data.model.User;
import org.foo.sandbox.data.repository.MessageRepository;
import org.foo.sandbox.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/{userId}/messages")
    public Page<Message> getAllMessagesByUserId(@PathVariable(value = "userId") Long userId, Pageable pageable) {
        return messageRepository.findByUserId(userId, pageable);
    }

    @PostMapping("/users/{userId}/messages")
    public Message createMessage(@PathVariable(value = "userId") Long userId, @Valid @RequestBody Message input) {

        return userRepository.findById(userId).map(user -> {
            Message message = new Message();
            message.setMessage(input.getMessage());
            message.setUser(user);
            return messageRepository.save(message);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }

    @PutMapping("/users/{userId}/messages/{messageId}")
    public Message updateMessage(@PathVariable(value = "userId") Long userId, @PathVariable(value = "messageId") Long messageId, @Valid @RequestBody Message input) {

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
            throw new DataInconsistencyException("UserId " + userId + " cannot update MessageId " + messageId + " by " + message.getUser().getId());
        }

        message.setMessage(input.getMessage());
        return messageRepository.save(message);
    }

    @DeleteMapping("/users/{userId}/messages/{messageId}")
    public ResponseEntity<?> deleteMessage(@PathVariable(value = "userId") Long userId, @PathVariable(value = "messageId") Long messageId) {

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
            throw new DataInconsistencyException("UserId " + userId + " cannot delete MessageId " + messageId + " by " + message.getUser().getId());
        }

        messageRepository.delete(message);
        return ResponseEntity.ok().build();
    }
}
