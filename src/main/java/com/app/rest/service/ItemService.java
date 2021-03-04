package com.app.rest.service;

import com.app.rest.model.dto.ItemDTO;
import com.app.rest.model.persistence.ItemDetail;
import com.app.rest.repository.ItemRepo;
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

    public ItemDTO getItemById(Long id) {
        try {
            return new ItemDTO(itemRepo.findById(id).get());
        } catch (NoSuchElementException e) {
            LOGGER.error("Error trying retrieve item with id {}. Caused by[{}]", id, e.getMessage());
            throw e;
        }
    }

    public List<ItemDTO> getItems()  {
        List<ItemDTO> itemsDTO = new ArrayList<ItemDTO>();
        try {
            List<ItemDetail> list = itemRepo.findAll();
            for (ItemDetail x : list) {
                itemsDTO.add(new ItemDTO(x));
                }
            return  itemsDTO;
        } catch (DataAccessException ie) {
            LOGGER.error("Error trying to retrieve items . Caused by[{}]", ie.getMessage());
            throw ie;
        }
    }

    public ItemDTO saveItem(ItemDTO itemDTO) { //Esta bien que no haya control de duplicados?
        ItemDetail itemDetail = new ItemDetail(itemDTO.getName(), itemDTO.getType(), itemDTO.getDescription(), itemDTO.isDeleted());
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
    public ItemDTO updateItem(Long id, ItemDTO itemDTO) {  //Hago un update o deberia chequear dupes en el new y en todo caso updatear?
        try {
            ItemDetail itemDetail = itemRepo.findById(id).get();
            itemDetail.setName(itemDTO.getName());
            itemDetail.setDescription(itemDTO.getDescription());
            itemDetail.setType(itemDTO.getType());
            itemDetail.setDeleted(itemDTO.isDeleted());
            return new ItemDTO(itemRepo.save(itemDetail));
        } catch (NoSuchElementException e) {
            LOGGER.error("Error trying retrieve item with id {}. Caused by[{}]", id, e.getMessage());
            throw e;
        } catch (DataAccessException e) {
            LOGGER.error("Error trying to update item {}. Caused by[{}]", id, e.getMessage());
            throw e;
        }
    }
}
