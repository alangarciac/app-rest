package com.app.rest.model;

public class Order {

    private String name;
    private int id;
    private boolean vegan;


    public Order(int id, String name, boolean vegan) {
        this.name=name;
        this.id=id;
        this.vegan=vegan;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public boolean isVegan() {
        return vegan;
    }
    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", vegan=" + vegan +
                '}';
    }

}
