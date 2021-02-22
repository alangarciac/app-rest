package com.app.rest.services;

import com.app.rest.model.Item;
import com.app.rest.repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepo itemRepo;

    public List<Item> getItems() throws InterruptedException {
        return itemRepo.findAll();
    }
    public void save(Item item){
        itemRepo.save(item);
    }
}
