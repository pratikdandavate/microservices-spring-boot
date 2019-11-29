package com.aira.matrix.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aira.matrix.exception.ResourceNotFoundException;
import com.aira.matrix.feign.intercomm.OrganClient;
import com.aira.matrix.model.Slide;
import com.aira.matrix.repository.SlideRepository;




@RestController
//@RequestMapping("/api")
public class SlideController {
	
	private static final Logger LOG = LoggerFactory.getLogger(SlideController.class);

    @Autowired
    private SlideRepository slideRepository;

    @Autowired
    private OrganClient organClient;
    
    @GetMapping("/organs/{organId}/slides")
    public List<Slide> getAllslidesByOrganId(@PathVariable (value = "organId") Long organId) {
    	LOG.info("Get all slides for organ "+organId);
    	return slideRepository.findByOrganId(organId);
    }

    @PostMapping("/organs/{organId}/slides")
    public Slide createSlide(@PathVariable (value = "organId") Long organId,
                                 @Valid @RequestBody Slide slide,Principal principal) {
//    	Optional<Organ> organ = organClient.getOrganById(organId);
        return organClient.getOrganById(organId).map(organ -> {
        	LOG.info("Added slide for organ id :"+organ + "with Slide details : "+slide);
            slide.setOrganId(organ.getId());
            return slideRepository.save(slide);
        }).orElseThrow(() -> new ResourceNotFoundException("organId " + organId + " not found"));
    }

    @PutMapping("/organs/{organId}/slides/{slideId}")
    public Slide updateSlide(@PathVariable (value = "organId") Long organId,
                                 @PathVariable (value = "slideId") Long slideId,
                                 @Valid @RequestBody Slide slideRequest) {
//        if(!organRepository.existsById(organId)) {
//            throw new ResourceNotFoundException("organId " + organId + " not found");
//        }
    	organClient.getOrganById(organId)
    			.orElseThrow(() -> new ResourceNotFoundException("organId " + organId + " not found"));
        LOG.info("Updated slide for organ id :"+organId + "with Slide details : "+slideRequest);
        return slideRepository.findById(slideId).map(slide -> {
            slide.setNameOfSlide(slideRequest.getNameOfSlide());
            return slideRepository.save(slide);
        }).orElseThrow(() -> new ResourceNotFoundException("slideId " + slideId + "not found"));
    }

    @DeleteMapping("/organs/{organId}/slides/{slideId}")
    public ResponseEntity<?> deleteSlide(@PathVariable (value = "organId") Long organId,
                              @PathVariable (value = "slideId") Long slideId) {
        LOG.info("Deleted slide for slide id :"+slideId);
    	return slideRepository.findByIdAndOrganId(slideId, organId).map(Slide -> {
            slideRepository.delete(Slide);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Slide not found with id " + slideId + " and organId " + organId));
    }

}