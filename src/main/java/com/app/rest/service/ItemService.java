package com.app.rest.service;

import com.app.rest.exception.ItemException;
import com.app.rest.exception.ItemNotFoundException;
import com.app.rest.model.dto.ItemDTO;
import com.app.rest.model.persistence.ItemDetail;
import com.app.rest.repository.ItemRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ItemService {
    private static Logger LOGGER = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private ItemRepo itemRepo;

    public ItemDTO getItemById(Long id) throws ItemNotFoundException, ItemException {
        try {
            Optional<ItemDetail> oiDTO = itemRepo.findById(id);

            if (oiDTO.isPresent()) { return new ItemDTO(oiDTO.get()); }
            else {
                String message = MessageFormat.format("Error trying retrieve item with id {0}. Caused by[{1}]", id, "item not found");
                LOGGER.error(message);
                throw new ItemNotFoundException(message);
            }
        } catch (DataAccessException ie) {
            String message = MessageFormat.format("Error trying retrieve item. Caused by[{0}]", ie.getMessage());
            LOGGER.error(message);
            throw new ItemException(message);
        }
    }

    public List<ItemDTO> getItems() throws ItemException {
        List<ItemDTO> itemsDTO = new ArrayList<ItemDTO>();
        try {
            List<ItemDetail> items = itemRepo.findAll();
            for (ItemDetail itemDetail : items) {
                itemsDTO.add(new ItemDTO(itemDetail));
                }
            return  itemsDTO;
        } catch (DataAccessException ie) {
            String message = MessageFormat.format("Error trying retrieve item. Caused by[{0}]", ie.getMessage());
            LOGGER.error(message);
            throw new ItemException(message);
        }
    }

    public ItemDTO saveItem(ItemDTO itemDTO) throws ItemException { //Esta bien que no haya control de duplicados?
        ItemDetail itemDetail = new ItemDetail(itemDTO.getName(), itemDTO.getType(), itemDTO.getDescription(), itemDTO.isDeleted());
        try {
            return new ItemDTO(itemRepo.save(itemDetail));
        } catch (DataAccessException ie) {
            String message = MessageFormat.format("Error trying to save item {0}. Caused by[{1}]", itemDetail, ie.getMessage());
            LOGGER.error(message);
            throw new ItemException(message);
        }
    }

    public ItemDTO deleteItem(Long id) throws ItemException, ItemNotFoundException {
        Optional<ItemDetail> oiDTO = itemRepo.findById(id);
        if (oiDTO.isPresent()) {
            ItemDetail itemDetail = oiDTO.get();
            itemDetail.setDeleted(true);
            try {
                return new ItemDTO(itemRepo.save(itemDetail));
            } catch (DataAccessException ie) {
                String message = MessageFormat.format("Error trying to save item {0}. Caused by[{1}]", itemDetail, ie.getMessage());
                LOGGER.error(message);
                throw new ItemException(message);
            }
        } else {
                String message = MessageFormat.format("Error trying retrieve item with id {0}. Caused by[{1}]", id, "item not found");
                LOGGER.error(message);
                throw new ItemNotFoundException(message);
        }
    }
    public ItemDTO updateItem(Long id, ItemDTO itemDTO) throws ItemException, ItemNotFoundException {  //Hago un update o deberia chequear dupes en el new y en todo caso updatear?
        try {
            ItemDetail itemDetail = itemRepo.findById(id).get();
            itemDetail.setName(itemDTO.getName());
            itemDetail.setDescription(itemDTO.getDescription());
            itemDetail.setType(itemDTO.getType());
            itemDetail.setDeleted(itemDTO.isDeleted());
            return new ItemDTO(itemRepo.save(itemDetail));
        } catch (NoSuchElementException e) {
            String message = MessageFormat.format("Error trying to update item {0}. Caused by[{1}]", id, e.getMessage());
            LOGGER.error(message);
            throw new ItemNotFoundException(message);
        } catch (DataAccessException e) {
            String message = MessageFormat.format("Error trying to update item {0}. Caused by[{1}]", id, e.getMessage());
            LOGGER.error(message);
            throw new ItemException(message);
        }
    }
}
