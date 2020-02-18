package com.przybysz.microblog.repository;

import com.przybysz.microblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {

    User findByUsername(String username);
}
