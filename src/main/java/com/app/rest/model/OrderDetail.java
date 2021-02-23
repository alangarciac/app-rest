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
    private String status;

    @OneToMany
    private List<Item> items;
    @OneToOne
    private User user;

    public OrderDetail() {
    }

    public OrderDetail(String code, Date date, String status, List<Item> items, User user) {
        this.code = code;
        this.date = date;
        this.status = status;
        this.items = items;
        this.user = user;
    }

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

    public void setStatus(String status) {
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", items=" + items +
                ", user=" + user +
                '}';
    }
}
