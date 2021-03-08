package com.app.rest.model.persistence;


import javax.persistence.*;

@Entity
@Table(name = "ITEMDETAIL")
public class ItemDetail {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String type;
    private String description;
    private boolean deleted;
    private Long last_modified;

    public ItemDetail() {
    }

    public ItemDetail(String name, String type, String description, boolean deleted, Long last_modified) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.deleted = deleted;
        this.last_modified = last_modified;
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

    public Long getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(Long last_modified) {
        this.last_modified = last_modified;
    }

    @Override
    public String toString() {
        return "ItemDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", deleted=" + deleted +
                ", last_modified=" + last_modified +
                '}';
    }
}
