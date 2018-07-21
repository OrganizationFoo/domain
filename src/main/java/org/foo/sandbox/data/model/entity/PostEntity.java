package org.foo.sandbox.data.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.foo.sandbox.data.model.Post;

@Entity
@Table(name = "posts")
public class PostEntity extends Post {

    private static final long serialVersionUID = 2569129457815326766L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public Long getId() {
        return super.getId();
    }

    @Column(nullable = false)
    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public void setMessage(String message) {
        if (message == null || message.trim().length() == 0) {
            return;
        }
        super.setMessage(message);
    }
}
