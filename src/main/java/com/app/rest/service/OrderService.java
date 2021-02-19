package com.app.rest.service;

import com.app.rest.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class OrderService {

    public List<Order> getOrders() throws InterruptedException {
        //Remove this example code an implement when the DB is ready
        List<Order> orders= new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            orders.add(new Order(i, "name"+i, false));
        }
            try {
                TimeUnit.SECONDS.sleep(5);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
                throw e;
            }

        
        return orders;
    }

    public Object getOrder(int id) {
        //Remove this example code an implement when the DB is ready
        return new Order(7777, "ProtoGetOrder", false);
    }

    public Object deleteOrder(int id) {
        //Remove this example code an implement when the DB is ready
        return new Order(8888, "ProtoDeleteOrder", false);
    }

    public Object newOrder(OrderService newServicio) {
        //Remove this example code an implement when the DB is ready
        return new Order(9999, "ProtoNewOrder", false);
    }
}
