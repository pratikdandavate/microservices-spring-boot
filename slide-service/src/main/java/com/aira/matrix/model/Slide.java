package com.aira.matrix.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "slides")
public class Slide {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nameOfSlide;

	private Long organId;

    @CreationTimestamp
    private LocalDateTime createDateTime;
 
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nameOfSlide
	 */
	public String getNameOfSlide() {
		return nameOfSlide;
	}

	/**
	 * @param nameOfSlide the nameOfSlide to set
	 */
	public void setNameOfSlide(String nameOfSlide) {
		this.nameOfSlide = nameOfSlide;
	}
	
	public Long getOrganId() {
		return organId;
	}

	public void setOrganId(Long organId) {
		this.organId = organId;
	}

	public LocalDateTime getCreatedAt() {
        return createDateTime;
    }

    public LocalDateTime getUpdatedAt() {
        return updateDateTime;
    }

}
