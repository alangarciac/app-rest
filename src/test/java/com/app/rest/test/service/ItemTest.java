package com.app.rest.test.service;


import com.app.rest.exception.itemExceptions.ItemException;
import com.app.rest.exception.itemExceptions.ItemNotFoundException;
import com.app.rest.exception.itemExceptions.ItemValidateException;
import com.app.rest.model.dto.ItemDTO;
import com.app.rest.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        ItemDTO itemDTO = itemService.getItemById(1L);
        System.out.println("Item retrieved "+ itemDTO);
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
    public void testSaveItem() {
        ItemDTO itemDTO;
        try {
            itemDTO = new ItemDTO("test", "test","test");
            itemDTO.validate();
        } catch (ItemValidateException ie) {
            ie.printStackTrace();
            System.out.println("Illegal type value, which is expected");
        }
        try {
            itemDTO = new ItemDTO("test", "OTHER","test");
            itemDTO.validate();
            System.out.println(itemService.saveItem(itemDTO));
            itemDTO = new ItemDTO("test", "OTHER","test");
            itemDTO.validate();
            System.out.println(itemService.saveItem(itemDTO));
        } catch (ItemValidateException ie) {
            ie.printStackTrace(); //Nunca va a saltar aca
        } catch (ItemException ie) {
            System.out.println(ie.getMessage() + " this is expected!");
        }
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
    @Test
    public void testFindByItem() throws Exception{
        System.out.println(itemService.getItemsByType("FOOD"));
    }

}