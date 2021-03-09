package com.app.rest.service;

import com.app.rest.dao.ItemDAO;
import com.app.rest.exception.ItemException;
import com.app.rest.exception.ItemNotFoundException;
import com.app.rest.exception.ItemTypeException;
import com.app.rest.model.dto.ItemDTO;
import com.app.rest.model.persistence.ItemDetail;
import com.app.rest.repository.ItemRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.*;

@Service
public class ItemService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private ItemDAO itemDAO;


    public ItemDTO getItemById(Long id) throws ItemNotFoundException, ItemException {
        return itemDAO.findById(id);
    }

    public List<ItemDTO> getItems() throws ItemException {
        return itemDAO.findAll();
    }

    public ItemDTO saveItem(ItemDTO itemDTO) throws ItemException {
        if (Objects.isNull(itemDAO.findOneByNameAndDesc(itemDTO))) { //check dupes
            return itemDAO.save(itemDTO);
        } else throw new ItemException("Item already exists!");
    }

    public ItemDTO deleteItem(Long id) throws ItemException, ItemNotFoundException {
            return itemDAO.delete(id);
    }

    public ItemDTO updateItem(Long id, ItemDTO itemDTO) throws ItemException, ItemNotFoundException {
        return itemDAO.update(id, itemDTO);
    }
}
