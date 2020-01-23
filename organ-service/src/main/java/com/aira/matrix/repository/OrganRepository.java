package com.aira.matrix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aira.matrix.model.Organ;

/**
 * Created by Pratik.Dandavate
 */

@Repository
public interface OrganRepository extends JpaRepository<Organ, Long> {

}
