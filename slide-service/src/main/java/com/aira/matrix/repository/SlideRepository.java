package com.aira.matrix.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aira.matrix.model.Slide;

public interface SlideRepository extends JpaRepository<Slide, Long> {
	List<Slide> findByOrganId(Long organId);

	Optional<Slide> findByIdAndOrganId(Long id, Long organId);
}
