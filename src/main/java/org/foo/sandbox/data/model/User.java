package org.foo.sandbox.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

@Entity
@Table(name = "users")
public class User extends BaseModel {

    private static final long serialVersionUID = 887545012118815411L;

    private Long id;
    private String email;
    private String name;
    private Boolean verified;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Column(unique = true, nullable = false, updatable = false)
    public String getEmail() {
        return email;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    @Column(columnDefinition = "boolean default false", nullable = false)
    public Boolean getVerified() {
        return BooleanUtils.isTrue(verified);
    }

    public void setEmail(String email) {
        if (StringUtils.isNotBlank(email)) {
            this.email = email;
        }
    }

    public void setName(String name) {
        if (StringUtils.isNotBlank(name)) {
            this.name = name;
        }
    }

    public void setVerified(Boolean verified) {
        if (verified != null) {
            this.verified = verified;
        }
    }

    public void setId(Long id) {
        this.id = id;
    }
}
