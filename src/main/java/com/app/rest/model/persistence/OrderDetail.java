package com.app.rest.model.persistence;

import javax.persistence.*;

import java.util.Date;
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
    @JoinTable(name = "ORDERDETAIL_ITEMDETAIL")
    private List<ItemDetail> itemDetails;
    @OneToOne
    private UserDetail userDetail;

    public OrderDetail() {
    }

    public OrderDetail(String code, Date date, String status, List<ItemDetail> itemDetails, UserDetail userDetail) {
        this.code = code;
        this.date = date;
        this.status = status;
        this.itemDetails = itemDetails;
        this.userDetail = userDetail;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setItems(List<ItemDetail> itemDetails) {
        this.itemDetails = itemDetails;
    }

    public void setUser(UserDetail userDetail) {
        this.userDetail = userDetail;
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

    public List<ItemDetail> getItems() {
        return itemDetails;
    }

    public UserDetail getUser() {
        return userDetail;
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
                ", items=" + itemDetails +
                ", user=" + userDetail +
                '}';
    }
}
