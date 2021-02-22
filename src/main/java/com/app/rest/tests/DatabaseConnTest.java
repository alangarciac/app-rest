package com.app.rest.tests;

import com.app.rest.model.Item;
import com.app.rest.services.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class DatabaseConnTest {

    @Autowired
    ItemService itemServ;

    @Test
    public void UnitTest() throws InterruptedException, InternalError {
        long l1 = itemServ.getItems().size();
        itemServ.save(new Item("una prueba", "una prueba", "una prueba"));
        long l2 = itemServ.getItems().size();
        if (l1 == l2) {
            throw new InternalError("No agrego nada che!");
        }
    }
}
