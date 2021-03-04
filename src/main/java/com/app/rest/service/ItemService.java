package com.app.rest.service;

import com.app.rest.model.dto.ItemDTO;
import com.app.rest.model.persistence.ItemDetail;
import com.app.rest.repository.ItemRepo;
import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ItemService {
    private static Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private ItemRepo itemRepo;

    //as guidance
    /*public List<ItemDetail> getItems() throws InterruptedException {
        return itemRepo.findAll();
    }
    public ItemDetail getItem(Long id) throws InterruptedException {
        return itemRepo.findById(id).get;
    }
    public Long saveItem(ItemDetail itemDetail) throws InterruptedException {
        return itemRepo.save(itemDetail).getId();
    }
    public ItemDetail deleteItem(ItemDetail itemDetail) {
        itemDetail.setDeleted(true);
        return itemRepo.save(itemDetail);
    }*/
    public ItemDTO getItemById(Long id) {
        try {
            return new ItemDTO(itemRepo.findById(id).get());
        } catch (NoSuchElementException e) {
            LOGGER.error("Error trying retrieve order with id {}. Caused by[{}]", id, e.getMessage());
            throw e;
        }
    }

    public List<ItemDTO> getItems()  {
        List<ItemDTO> itemsDTO = new ArrayList<ItemDTO>();
        List<ItemDetail> list = itemRepo.findAll();
        for (ItemDetail x : list) {
            itemsDTO.add(new ItemDTO(x));
        }
        return  itemsDTO;
    }

    public ItemDTO saveItem(ItemDTO itemDTO) {
        ItemDetail itemDetail = new ItemDetail(itemDTO);
        try {
            return new ItemDTO(itemRepo.save(itemDetail));
        } catch (DataAccessException ie) {
            LOGGER.error("Error trying to save item {}. Caused by[{}]", itemDetail, ie.getMessage());
            throw ie;
        }
    }

    public ItemDTO deleteItem(Long id) {
        try {
            ItemDetail itemDetail = itemRepo.findById(id).get();
            itemDetail.setDeleted(true);
            return new ItemDTO(itemRepo.save(itemDetail));
        } catch (NoSuchElementException e) {
            LOGGER.error("Error trying retrieve order with id {}. Caused by[{}]", id, e.getMessage());
            throw e;
        }
    }
}
