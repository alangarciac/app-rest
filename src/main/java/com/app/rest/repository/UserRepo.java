package com.app.rest.repository;

import com.app.rest.model.persistence.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserDetail, Long> {
}
