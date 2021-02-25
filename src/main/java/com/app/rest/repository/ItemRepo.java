package com.app.rest.repository;

import com.app.rest.model.persistence.ItemDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends JpaRepository<ItemDetail, Long> {

}
