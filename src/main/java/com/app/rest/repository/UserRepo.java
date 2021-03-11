package com.app.rest.repository;

import com.app.rest.model.persistence.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserDetail, Long> {
}
