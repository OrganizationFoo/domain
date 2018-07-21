package org.foo.sandbox.data.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.foo.sandbox.data.model.User;

@Entity
@Table(name = "users")
public class UserEntity extends User {

    private static final long serialVersionUID = -9085927873183589714L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public Long getId() {
        return super.getId();
    }

    @Column(unique = true, nullable = false, updatable = false)
    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Column(nullable = false)
    @Override
    public String getName() {
        return super.getName();
    }

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Override
    public Boolean getVerified() {
        Boolean verified = super.getVerified();
        if (verified == null) {
            verified = Boolean.FALSE;
        }
        return verified;
    }

    @Override
    public void setEmail(String email) {
        if (email != null && email.trim().length() == 0) {
            return;
        }
        super.setEmail(email);
    }

    @Override
    public void setName(String name) {
        if (name != null && name.trim().length() == 0) {
            return;
        }
        super.setName(name);
    }

    @Override
    public void setVerified(Boolean verified) {
        if (verified == null) {
            return;
        }
        super.setVerified(verified);
    }
}
