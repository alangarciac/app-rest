package com.app.rest.model;

import javax.persistence.*;

@Entity
public class Item {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String type;
    private String description;
    private boolean deleted;

    public Item() {
    }
    public Item(String name, String type, String description, boolean deleted) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.deleted = deleted;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
