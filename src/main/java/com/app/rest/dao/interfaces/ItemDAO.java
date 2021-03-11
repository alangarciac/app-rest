package com.app.rest.dao.interfaces;

import com.app.rest.exception.itemExceptions.ItemGeneralException;
import com.app.rest.exception.itemExceptions.ItemNotFoundException;
import com.app.rest.exception.itemExceptions.ItemPersistenceException;
import com.app.rest.model.dto.ItemDTO;

import java.util.List;

public interface ItemDAO {

    ItemDTO findById(Long id) throws ItemNotFoundException, ItemGeneralException, ItemPersistenceException;

    List<ItemDTO> findAll() throws ItemGeneralException, ItemPersistenceException;
    
    ItemDTO save(ItemDTO itemDTO) throws ItemGeneralException, ItemPersistenceException;

    ItemDTO delete(Long id) throws ItemGeneralException, ItemNotFoundException, ItemPersistenceException;

    ItemDTO update(Long id, ItemDTO itemDTO) throws ItemGeneralException, ItemNotFoundException, ItemPersistenceException;

}
