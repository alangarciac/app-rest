package com.app.rest.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "ORDERDETAIL")
public class OrderDetail {
    @Id
    @GeneratedValue
    private Long id;
    private String code;
    private Date date;

    @OneToMany
    private List<Item> items;
    @OneToOne
    private User user;

    public OrderDetail() {
    }

    public OrderDetail(Long id, String code, Date date, List<Item> items, User user) {
        this.id = id;
        this.code = code;
        this.date = date;
        this.items = items;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public Date getDate() {
        return date;
    }

    public List<Item> getItems() {
        return items;
    }

    public User getUser() {
        return user;
    }
}
