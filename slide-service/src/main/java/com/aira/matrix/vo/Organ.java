package com.aira.matrix.vo;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


/**
 * Created by Pratik.Dandavate.
 */

public class Organ {

	private Long id;

    private String name;

    private LocalDateTime createDateTime;
 
    private LocalDateTime updateDateTime;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createDateTime;
    }

    public LocalDateTime getUpdatedAt() {
        return updateDateTime;
    }
}
