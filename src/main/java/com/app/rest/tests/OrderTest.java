package com.app.rest.tests;

import com.app.rest.service.OrderService;

public class OrderTest {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        try {
            System.out.println(orderService.getOrders());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
