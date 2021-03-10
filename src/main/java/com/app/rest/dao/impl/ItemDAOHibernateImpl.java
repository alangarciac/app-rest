package com.app.rest.dao.impl;

import com.app.rest.dao.interfaces.ItemDAO;
import com.app.rest.exception.ItemException;
import com.app.rest.exception.ItemNotFoundException;
import com.app.rest.exception.ItemTypeException;
import com.app.rest.format.DateFormat;
import com.app.rest.model.dto.ItemDTO;
import com.app.rest.model.dto.ItemType;
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
public class ItemDAOHibernateImpl implements ItemDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemDAOHibernateImpl.class);

    @Autowired
    private ItemRepo itemRepo;

    private static Specification<ItemDetail> hasName(String name) {
        return (ItemDetail, cq, cb) -> cb.equal(ItemDetail.get("name"), name);
    }
    private static Specification<ItemDetail> descriptionContains(String description) {
        return (ItemDetail, cq, cb) -> cb.like(ItemDetail.get("description"), "%" + description + "%");
    }
    private static Specification<ItemDetail> hasType(String type) {
        return (ItemDetail, cq, cb) -> cb.equal(ItemDetail.get("type"), type);
    }

    public ItemDTO findById(Long id) throws ItemNotFoundException, ItemException {
        try {
            Optional<ItemDetail> oiDTO = itemRepo.findById(id);
            if (oiDTO.isPresent()) {
                return new ItemDTO(oiDTO.get());
            } else {
                String message = MessageFormat.format("Error trying retrieve item with id {0}. Caused by[{1}]", id, "item not found");
                LOGGER.error(message);
                throw new ItemNotFoundException(message);
            }
        } catch (ItemTypeException ie) {
            LOGGER.error(ie.getMessage());
            throw new ItemException(ie.getMessage());
        } catch (DataAccessException ie) {
            String message = MessageFormat.format("Error trying retrieve item. Caused by[{0}]", ie.getMessage());
            LOGGER.error(message);
            throw new ItemException(message);
        }
    }

    public List<ItemDTO> findAll() throws ItemException {
        List<ItemDTO> itemsDTO = new ArrayList<>();
        try {
            List<ItemDetail> items = itemRepo.findAll();
            for (ItemDetail itemDetail : items) {
                itemsDTO.add(new ItemDTO(itemDetail));
                }
            return  itemsDTO;
        } catch (ItemTypeException ie) {
            LOGGER.error(ie.getMessage());
            throw new ItemException(ie.getMessage());
        } catch (DataAccessException ie) {
            String message = MessageFormat.format("Error trying retrieve item. Caused by[{0}]", ie.getMessage());
            LOGGER.error(message);
            throw new ItemException(message);
        }
    }
    public ItemDTO findOneByNameAndDesc(ItemDTO itemDTO) throws ItemException {
        ItemDetail itemDetail = new ItemDetail(itemDTO.getName(), itemDTO.getType(), itemDTO.getDescription(), itemDTO.isDeleted(), Calendar.getInstance().getTimeInMillis());
        try {
            Optional<ItemDetail> oiDTO = itemRepo.findOne(hasName(itemDTO.getName()).and(descriptionContains(itemDTO.getDescription())));
            if (oiDTO.isPresent()) {
                return new ItemDTO(oiDTO.get());
            } else { return null; }
        } catch (ItemTypeException ie) {
            LOGGER.error(ie.getMessage());
            throw new ItemException(ie.getMessage());
        } catch (DataAccessException ie) {
            String message = MessageFormat.format("Error trying to save item {0}. Caused by[{1}]", itemDetail, ie.getMessage());
            LOGGER.error(message);
            throw new ItemException(message);
        }
    }
    public ItemDTO save(ItemDTO itemDTO) throws ItemException {
        ItemDetail itemDetail = new ItemDetail(itemDTO.getName(), itemDTO.getType(), itemDTO.getDescription(), itemDTO.isDeleted(), Calendar.getInstance().getTimeInMillis());
        try {
            return new ItemDTO(itemRepo.save(itemDetail));
        } catch (ItemTypeException ie) {
            LOGGER.error(ie.getMessage());
            throw new ItemException(ie.getMessage());
        } catch (DataAccessException ie) {
            String message = MessageFormat.format("Error trying to save item {0}. Caused by[{1}]", itemDetail, ie.getMessage());
            LOGGER.error(message);
            throw new ItemException(message);
        }
    }

    public ItemDTO delete(Long id) throws ItemException, ItemNotFoundException {
        try {
            Optional<ItemDetail> oiDet = itemRepo.findById(id);
            if (oiDet.isPresent()) {
                oiDet.get().setDeleted(true);
                oiDet.get().setLast_modified(DateFormat.toEpoch(LocalDateTime.now()));
                return new ItemDTO(itemRepo.save(oiDet.get()));
            } else {
                String message = MessageFormat.format("Error trying retrieve item with id {0}. Caused by[{1}]", id, "item not found");
                LOGGER.error(message);
                throw new ItemNotFoundException(message);
            }
        } catch (ItemTypeException ie) {
            LOGGER.error(ie.getMessage());
            throw new ItemException(ie.getMessage());
        } catch (DataAccessException ie) {
            String message = MessageFormat.format("Error trying to save item  with id {0}. Caused by[{1}]", id, ie.getMessage());
            LOGGER.error(message);
            throw new ItemException(message);
        }
    }
    public ItemDTO update(Long id, ItemDTO itemDTO) throws ItemException, ItemNotFoundException {
        try {
            Optional<ItemDetail> oiDTO = itemRepo.findById(id);
            if (oiDTO.isPresent()) {
                ItemDetail itemDetail = oiDTO.get();
                itemDetail.setName(itemDTO.getName());
                itemDetail.setDescription(itemDTO.getDescription());
                itemDetail.setType(itemDTO.getType());
                itemDetail.setLast_modified(DateFormat.toEpoch(LocalDateTime.now()));
                return new ItemDTO(itemRepo.save(itemDetail));
            } else {
                String message = MessageFormat.format("Error trying to update item {0}. Caused by[{1}]", id, "item not found");
                LOGGER.error(message);
                throw new ItemNotFoundException(message);
            }
        } catch (ItemTypeException ie) {
            LOGGER.error(ie.getMessage());
            throw new ItemException(ie.getMessage());
        } catch (DataAccessException e) {
            String message = MessageFormat.format("Error trying to update item {0}. Caused by[{1}]", id, e.getMessage());
            LOGGER.error(message);
            throw new ItemException(message);
        }
    }

    public List<ItemDTO> findAllByType(String type) throws ItemException {
        try {
            List<ItemDetail> oiDet = itemRepo.findAll(hasType(type));
            if (oiDet.size() > 0) {
                List<ItemDTO> itemsDTO = new ArrayList<>();
                for (ItemDetail itemDetail : oiDet) {
                    itemsDTO.add(new ItemDTO(itemDetail));
                }
                return  itemsDTO;
            } else { return null; }
        } catch (ItemTypeException ie) {
            LOGGER.error(ie.getMessage());
            throw new ItemException(ie.getMessage());
        } catch (DataAccessException ie) {
            String message = MessageFormat.format("Error trying to search items by type{0}. Caused by[{1}]", type, ie.getMessage());
            LOGGER.error(message);
            throw new ItemException(message);
        }
    }
}
