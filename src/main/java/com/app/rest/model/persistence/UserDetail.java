package com.app.rest.model.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERDETAIL")
public class UserDetail {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private boolean deleted;

    public UserDetail() {
    }

    public UserDetail(String name, String email, boolean deleted) {
        this.name = name;
        this.email = email;
        this.deleted = deleted;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isDeleted() {
        return deleted;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", deleted='" + deleted + '\'' +
                '}';
    }
}
