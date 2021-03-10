package com.app.rest.service;

import com.app.rest.dao.impl.ItemDAOHibernateImpl;
import com.app.rest.exception.itemExceptions.ItemException;
import com.app.rest.exception.itemExceptions.ItemNotFoundException;
import com.app.rest.exception.itemExceptions.ItemPersistanceException;
import com.app.rest.model.dto.ItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ItemService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private ItemDAOHibernateImpl itemDAO;


    public ItemDTO getItemById(Long id) throws ItemNotFoundException, ItemException {
        try {
            return itemDAO.findById(id);
        } catch (ItemException | ItemNotFoundException ie) {
            LOGGER.debug(ie.getMessage());
            throw ie;
        } catch (ItemPersistanceException ip){
            LOGGER.error(ip.getMessage());
            throw new ItemException(ip.getMessage());
        }
    }

    public List<ItemDTO> getItems() throws ItemException {
        try {
            return itemDAO.findAll();
        } catch (ItemException ie){
            LOGGER.debug(ie.getMessage());
            throw ie;
        } catch (ItemPersistanceException ip){
            LOGGER.error(ip.getMessage());
            throw new ItemException(ip.getMessage());
        }
    }

    public ItemDTO saveItem(ItemDTO itemDTO) throws ItemException {
        try {
            if (Objects.isNull(itemDAO.findOneByNameAndDesc(itemDTO))) { //check dupes
                return itemDAO.save(itemDTO);
            } else throw new ItemException("Item already exists!");
        } catch (ItemException ie){
            LOGGER.debug(ie.getMessage());
            throw ie;
        } catch (ItemPersistanceException ip){
            LOGGER.error(ip.getMessage());
            throw new ItemException(ip.getMessage());
        }
    }

    public ItemDTO deleteItem(Long id) throws ItemException, ItemNotFoundException {
        try {
            return itemDAO.delete(id);
        } catch (ItemException | ItemNotFoundException ie) {
            LOGGER.debug(ie.getMessage());
            throw ie;
        } catch (ItemPersistanceException ip){
            LOGGER.error(ip.getMessage());
            throw new ItemException(ip.getMessage());
        }
    }

    public ItemDTO updateItem(Long id, ItemDTO itemDTO) throws ItemException, ItemNotFoundException {
        try {
            return itemDAO.update(id, itemDTO);
        } catch (ItemException | ItemNotFoundException ie) {
            LOGGER.debug(ie.getMessage());
            throw ie;
        } catch (ItemPersistanceException ip){
            LOGGER.error(ip.getMessage());
            throw new ItemException(ip.getMessage());
        }
    }

    public List<ItemDTO> getItemsByType(String type) throws ItemException {
        try {
            return itemDAO.findAllByTypeSorted(type);
        } catch (ItemException ie) {
            LOGGER.debug(ie.getMessage());
            throw ie;
        } catch (ItemPersistanceException ip){
            LOGGER.error(ip.getMessage());
            throw new ItemException(ip.getMessage());
        }
    }
}
