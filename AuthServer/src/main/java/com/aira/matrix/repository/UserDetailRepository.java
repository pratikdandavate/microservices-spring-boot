package com.aira.matrix.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aira.matrix.model.User;

public interface UserDetailRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String name);
}
