package com.app.rest.dao.interfaces;

import com.app.rest.exception.ItemException;
import com.app.rest.exception.ItemNotFoundException;
import com.app.rest.exception.ItemTypeException;
import com.app.rest.format.DateFormat;
import com.app.rest.model.dto.ItemDTO;
import com.app.rest.model.persistence.ItemDetail;
import com.app.rest.repository.ItemRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public interface ItemDAO {

    ItemDTO findById(Long id) throws ItemNotFoundException, ItemException;

    List<ItemDTO> findAll() throws ItemException;

    ItemDTO findOneByNameAndDesc(ItemDTO itemDTO) throws ItemException;

    ItemDTO save(ItemDTO itemDTO) throws ItemException;

    ItemDTO delete(Long id) throws ItemException, ItemNotFoundException;

    ItemDTO update(Long id, ItemDTO itemDTO) throws ItemException, ItemNotFoundException;

}
