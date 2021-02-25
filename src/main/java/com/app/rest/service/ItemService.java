package com.app.rest.service;

import com.app.rest.model.persistence.ItemDetail;
import com.app.rest.repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepo itemRepo;

    public List<ItemDetail> getItems() throws InterruptedException {
        return itemRepo.findAll();
    }
    public Optional<ItemDetail> getItem(Long id) throws InterruptedException {
        return itemRepo.findById(id);
    }
    public Long saveItem(ItemDetail itemDetail) throws InterruptedException {
        return itemRepo.save(itemDetail).getId();
    }
    public ItemDetail deleteItem(ItemDetail itemDetail) {
        itemDetail.setDeleted(true);
        return itemRepo.save(itemDetail);
    }
}
