package com.app.rest.dao;

import com.app.rest.exception.OrderNotFoundException;
import com.app.rest.model.dto.OrderDTO;
import com.app.rest.model.persistence.OrderDetail;
import com.app.rest.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class OrderDAO {

    @Autowired
    private OrderRepo orderRepo;

    public OrderDTO retrieveOrderById(Long id) throws OrderNotFoundException, IllegalStateException {

        // Acá llama a ordenRepo por el approach que elegimos. En este punto se interactúa con la DB.
        // por ejemplo si no tuvieramos Hibernate, las queries se harían aca y se mapearian al objeto persistente (OrderDetail)
        OrderDetail orderDetail = Optional.ofNullable(orderRepo.findById(id)).get()
                .orElseThrow(() -> new OrderNotFoundException(String.format("Order with id[%s] not found.", id)));

        // retorna un DTO (objeto manipulable en la app)
        return new OrderDTO(orderDetail);
    }
}
