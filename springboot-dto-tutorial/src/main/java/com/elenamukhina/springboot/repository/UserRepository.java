package com.elenamukhina.springboot.repository;

import com.elenamukhina.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
