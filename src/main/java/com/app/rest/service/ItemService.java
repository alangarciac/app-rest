package com.app.rest.service;

import com.app.rest.model.Item;
import com.app.rest.repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepo itemRepo;

    public List<Item> getItems() throws InterruptedException {
        return itemRepo.findAll();
    }
    public Optional<Item> getItem(Long id) throws InterruptedException {
        return itemRepo.findById(id);
    }
    public Long saveItem(Item item) throws InterruptedException {
        return itemRepo.save(item).getId();
    }
    public Item deleteItem(Item item) {
        item.setDeleted(true);
        return itemRepo.save(item);
    }
}
