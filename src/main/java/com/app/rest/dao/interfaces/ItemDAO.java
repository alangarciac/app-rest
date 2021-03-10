package com.app.rest.dao.interfaces;

import com.app.rest.exception.itemExceptions.ItemException;
import com.app.rest.exception.itemExceptions.ItemNotFoundException;
import com.app.rest.exception.itemExceptions.ItemPersistanceException;
import com.app.rest.model.dto.ItemDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemDAO {

    ItemDTO findById(Long id) throws ItemNotFoundException, ItemException, ItemPersistanceException;

    List<ItemDTO> findAll() throws ItemException, ItemPersistanceException;
    
    ItemDTO save(ItemDTO itemDTO) throws ItemException, ItemPersistanceException;

    ItemDTO delete(Long id) throws ItemException, ItemNotFoundException, ItemPersistanceException;

    ItemDTO update(Long id, ItemDTO itemDTO) throws ItemException, ItemNotFoundException, ItemPersistanceException;

}
