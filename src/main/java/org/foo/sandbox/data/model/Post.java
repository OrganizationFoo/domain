package org.foo.sandbox.data.model;

public abstract class Post extends BaseModel {

    private static final long serialVersionUID = 5568996268525591981L;

    private Long id;
    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
