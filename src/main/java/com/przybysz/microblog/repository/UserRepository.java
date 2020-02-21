package com.przybysz.microblog.repository;

import com.przybysz.microblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {

    Optional<User> findByUsername(String username);
}
