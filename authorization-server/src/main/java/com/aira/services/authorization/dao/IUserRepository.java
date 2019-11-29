/**
 * 
 */
package com.aira.services.authorization.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aira.services.authorization.model.User;

/**
 * @author Jaikishan Gurav
 *
 */
public interface IUserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

}
