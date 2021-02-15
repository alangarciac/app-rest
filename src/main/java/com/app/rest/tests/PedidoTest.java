package com.app.rest.tests;

import com.app.rest.AppRest;
import com.app.rest.service.ServicioPedido;
import org.springframework.boot.SpringApplication;

public class PedidoTest {
    public static void main(String[] args) {
        ServicioPedido spedido = new ServicioPedido();
        System.out.println(spedido.getPedidos());
    }

}
