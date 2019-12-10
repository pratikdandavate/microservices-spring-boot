/**
 * 
 */
package com.airamatrix.airadhi.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airamatrix.airadhi.demo.model.Study;
import com.airamatrix.airadhi.demo.payload.AiradhiRequest;
import com.airamatrix.airadhi.demo.payload.AiradhiResponse;
import com.airamatrix.airadhi.demo.repository.DemoRepository;

import reactor.core.publisher.Mono;

/**
 * @author Jaikishan Gurav
 *
 */
@Service
public class DemoService {

    @Autowired
    private DemoRepository repository;

    public Mono<AiradhiResponse> add(AiradhiRequest request) {
//	System.out.println(TenantHolder.getTenant().getAlias());
	Study study = mapToStudy(request);
	return Mono.just(repository.save(study)).map(result -> mapToResponse(study));
    }

    private Study mapToStudy(AiradhiRequest request) {
	Study study = new Study();
	study.setEplAccessionNo(request.getEplAccessionNo());
	study.setEplProjectNo(request.getEplProjectNo());
	study.setSacrifice(request.getSacrifice());
	study.setStain(request.getStain());
	study.setTitle(request.getTitle());

	return study;
    }

    private AiradhiResponse mapToResponse(Study study) {
	AiradhiResponse response = new AiradhiResponse();
	response.setId(study.getStudyPk());
	response.setEplAccessionNo(study.getEplAccessionNo());
	response.setEplProjectNo(study.getEplProjectNo());
	response.setTitle(study.getTitle());
	response.setSacrifice(study.getSacrifice());
	response.setStain(study.getStain());

	return response;
    }

}
