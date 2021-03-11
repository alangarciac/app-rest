package com.app.rest.service;

import com.app.rest.dao.impl.ItemDAOHibernateImpl;
import com.app.rest.exception.itemExceptions.ItemGeneralException;
import com.app.rest.exception.itemExceptions.ItemNotFoundException;
import com.app.rest.exception.itemExceptions.ItemPersistenceException;
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


    public ItemDTO getItemById(Long id) throws ItemNotFoundException, ItemGeneralException {
        try {
            return itemDAO.findById(id);
        } catch (ItemGeneralException | ItemNotFoundException ie) {
            LOGGER.debug(ie.getMessage());
            throw ie;
        } catch (ItemPersistenceException ip){
            LOGGER.error(ip.getMessage());
            throw new ItemGeneralException(ip.getMessage());
        }
    }

    public List<ItemDTO> getItems() throws ItemGeneralException {
        try {
            return itemDAO.findAll();
        } catch (ItemGeneralException ie){
            LOGGER.debug(ie.getMessage());
            throw ie;
        } catch (ItemPersistenceException ip){
            LOGGER.error(ip.getMessage());
            throw new ItemGeneralException(ip.getMessage());
        }
    }

    public ItemDTO saveItem(ItemDTO itemDTO) throws ItemGeneralException {
        try {
            if (Objects.isNull(itemDAO.findOneByNameAndDesc(itemDTO))) { //check dupes
                return itemDAO.save(itemDTO);
            } else throw new ItemGeneralException("Item already exists!");
        } catch (ItemGeneralException ie){
            LOGGER.debug(ie.getMessage());
            throw ie;
        } catch (ItemPersistenceException ip){
            LOGGER.error(ip.getMessage());
            throw new ItemGeneralException(ip.getMessage());
        }
    }

    public ItemDTO deleteItem(Long id) throws ItemGeneralException, ItemNotFoundException {
        try {
            return itemDAO.delete(id);
        } catch (ItemGeneralException | ItemNotFoundException ie) {
            LOGGER.debug(ie.getMessage());
            throw ie;
        } catch (ItemPersistenceException ip){
            LOGGER.error(ip.getMessage());
            throw new ItemGeneralException(ip.getMessage());
        }
    }

    public ItemDTO updateItem(Long id, ItemDTO itemDTO) throws ItemGeneralException, ItemNotFoundException {
        try {
            return itemDAO.update(id, itemDTO);
        } catch (ItemGeneralException | ItemNotFoundException ie) {
            LOGGER.debug(ie.getMessage());
            throw ie;
        } catch (ItemPersistenceException ip){
            LOGGER.error(ip.getMessage());
            throw new ItemGeneralException(ip.getMessage());
        }
    }

    public List<ItemDTO> getItemsByType(String type) throws ItemGeneralException {
        try {
            return itemDAO.findAllByTypeSorted(type);
        } catch (ItemGeneralException ie) {
            LOGGER.debug(ie.getMessage());
            throw ie;
        } catch (ItemPersistenceException ip){
            LOGGER.error(ip.getMessage());
            throw new ItemGeneralException(ip.getMessage());
        }
    }
}
