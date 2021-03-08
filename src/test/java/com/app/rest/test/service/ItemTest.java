package com.app.rest.test.service;


import com.app.rest.exception.ItemException;
import com.app.rest.exception.ItemNotFoundException;
import com.app.rest.model.dto.ItemDTO;
import com.app.rest.model.persistence.ItemDetail;
import com.app.rest.service.ItemService;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

@SpringBootTest
public class ItemTest {

    @Autowired
    private ItemService itemService;

    @Test
    public void testItems() throws ItemException {
        System.out.println(itemService.getItems());
    }

    @Test
    public void testItem1() throws ItemNotFoundException, ItemException {
        itemService.getItemById(1L);
        try {
            itemService.getItemById(8L);
        } catch (ItemNotFoundException nf) {
            System.out.println("Id 8 not found, which is expected");
        }
    }

    /*@Rule
    public final ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testItem2() throws ItemNotFoundException {
        itemService.getItemById(1L);
        exceptionRule.expect(ItemNotFoundException.class);   ESTO NO ESTARIA ANDANDO
        itemService.getItemById(8L);
    }
     */

    @Test
    public void testSaveItem() throws Exception{
        ItemDTO itemDTO = new ItemDTO("test", "test","test",false);
        try {
            itemDTO.validate();
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
            System.out.println("Illegal type value, which is expected");
        }
        itemDTO = new ItemDTO("test", "VEGAN","test",false);
        itemDTO.validate();
        System.out.println(itemService.saveItem(itemDTO));

    }
    @Test
    public void testDeleteItem() throws Exception{
        System.out.println(itemService.deleteItem(2L));
    }
    @Test
    public void testUpdateItem() throws Exception{
        System.out.println(itemService.getItemById(1L));
        ItemDTO itemDTO = itemService.getItemById(1L);
        itemDTO.setName("test");
        System.out.println(itemService.updateItem(itemDTO.getId(), itemDTO));
        System.out.println(itemService.getItems());
    }
}