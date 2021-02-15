package com.app.rest.service;

import com.app.rest.model.Pedido;
import org.springframework.retry.backoff.Sleeper;
import org.springframework.retry.support.RetrySimulation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ServicioPedido {

    public List<Pedido> getPedidos() {
        List<Pedido> pedidos= new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            pedidos.add(new Pedido(i, "nombre"+i, false));
        }
        for (int i = 0; i < 5; i++) {
            try {
                TimeUnit.SECONDS.sleep(5);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return pedidos;
    }
}
