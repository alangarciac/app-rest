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

    /*public OrderDetail(Long id, String code, Date date, List<Item> items, User user) {
        this.id = id;
        this.code = code;
        this.date = date;
        this.items = items;
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }*/

    public void setCode(String code) {
        this.code = code;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setUser(User user) {
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

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", date=" + date +
                ", items=" + items.toString() +
                ", user=" + user +
                '}';
    }
}
