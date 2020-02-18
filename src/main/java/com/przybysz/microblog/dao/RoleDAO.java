package com.przybysz.microblog.dao;

import com.przybysz.microblog.entity.Role;

import java.util.Collection;

public interface RoleDAO {

    public Collection<Role> findRoleByName(String roleName);
}
