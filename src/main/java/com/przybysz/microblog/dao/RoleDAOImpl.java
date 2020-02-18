package com.przybysz.microblog.dao;

import com.przybysz.microblog.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @Autowired
    private EntityManager entityManager;


    @Override
    @Transactional
    public Collection<Role> findRoleByName(String roleName) {
        Query query = entityManager.createQuery("from Role WHERE name=:roleName");
        query.setParameter("roleName", roleName);
        Collection<Role> roles  =query.getResultList();

        return roles;


    }
}

