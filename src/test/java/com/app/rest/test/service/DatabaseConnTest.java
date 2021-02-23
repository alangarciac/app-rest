package com.app.rest.test.service;

import com.app.rest.model.Item;
import com.app.rest.service.ItemService;
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
        Item item = new Item("una prueba", "una prueba", "una prueba");
        itemServ.saveItem(item);
        long l2 = itemServ.getItems().size();
        if (l1 == l2) {
            throw new InternalError("No agrego nada che!");
        }
        itemServ.deleteItem(item);
        if (! item.isDeleted()) {
            throw new InternalError("no lo borro");
        }
    }
}
