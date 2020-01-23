/**
 * 
 */
package com.airamatrix.airadhi.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airamatrix.airadhi.demo.model.Study;

/**
 * @author Jaikishan Gurav
 *
 */
@Repository
public interface DemoRepository extends JpaRepository<Study, Integer> {

}
