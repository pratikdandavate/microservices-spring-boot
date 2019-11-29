package com.aira.services.authorization.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aira.services.authorization.model.Permission;

@Repository
public interface IPermissionRepository extends JpaRepository<Permission, Integer> {

    Permission findByName(String name);

}
