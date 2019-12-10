package com.aira.matrix.controller;

import java.util.List;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aira.matrix.exception.ResourceNotFoundException;
import com.aira.matrix.model.Organ;
import com.aira.matrix.repository.OrganRepository;

/**
 * Created by Pratik.Dandavate.
 */
@RestController
// @RequestMapping("/api")
public class OrganController {

    private static final Logger LOG = LoggerFactory.getLogger(OrganController.class);

    @Autowired
    OrganRepository organRepository;

    @GetMapping("/organs")
    public List<Organ> getAllOrgans() {
        LOG.info("Get all organs API called. Response :" + organRepository.findAll());
        return organRepository.findAll();
    }

    @PostMapping("/organs")
    @PreAuthorize("hasAuthority('create_profile')")
    public Organ createOrgan(@Valid @RequestBody Organ organ) {
        LOG.info("Create API called with Request :" + organ);
        return organRepository.save(organ);
    }

    @GetMapping("/organs/{id}")
    public Organ getOrganById(@PathVariable(value = "id") Long organId) {
        LOG.info("Get organ by ID for organId " + organId);
        return organRepository.findById(organId)
                .orElseThrow(() -> new ResourceNotFoundException("Organ", "id", organId));
    }

    @PutMapping("/organs/{id}")
    public Organ updateorgan(@PathVariable(value = "id") Long organId, @Valid @RequestBody Organ organDetails) {

        // Organ organ = organRepository.findById(organId)
        // .orElseThrow(() ->
        //// LOG.log(Level.SEVERE,"Organ with id "+organId + "not found");
        // new ResourceNotFoundException("organ", "id", organId)
        // );
        Organ organ = organRepository.findById(organId).orElseThrow(() -> {
            LOG.info("Organ with id " + organId + "not found");
            return new ResourceNotFoundException("organ", "id", organId);
        });

        organ.setName(organDetails.getName());
        Organ updatedorgan = organRepository.save(organ);
        LOG.info("Updated organ details. Response :" + updatedorgan);
        return updatedorgan;
    }

    @DeleteMapping("/organs/{id}")
    public ResponseEntity<?> deleteorgan(@PathVariable(value = "id") Long organId) {
        Organ organ = organRepository.findById(organId)
                .orElseThrow(() -> new ResourceNotFoundException("organ", "id", organId));

        organRepository.delete(organ);
        LOG.info("Deleted organ details. Response :" + organ);
        return ResponseEntity.ok().build();
    }
}
