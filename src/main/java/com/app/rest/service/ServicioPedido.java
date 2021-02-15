package com.app.rest.service;

import com.app.rest.model.Pedido;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioPedido {

    public List<Pedido> getPedidos() {
        List<Pedido> pedidos= new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            pedidos.add(new Pedido(i, "nombre"+i, false));
        }
        return pedidos;
    }
}
