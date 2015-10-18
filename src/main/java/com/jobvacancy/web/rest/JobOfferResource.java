package com.jobvacancy.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.jobvacancy.domain.JobOffer;
import com.jobvacancy.domain.User;
import com.jobvacancy.repository.JobOfferRepository;
import com.jobvacancy.repository.UserRepository;
import com.jobvacancy.security.SecurityUtils;
import com.jobvacancy.web.rest.util.HeaderUtil;
import com.jobvacancy.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing JobOffer.
 */
@RestController
@RequestMapping("/api")
public class JobOfferResource {

    private final Logger log = LoggerFactory.getLogger(JobOfferResource.class);

    @Inject
    private JobOfferRepository jobOfferRepository;

    @Inject
    private UserRepository userRepository;

    /**
     * POST  /jobOffers -> Create a new jobOffer.
     */
    @RequestMapping(value = "/jobOffers",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<JobOffer> createJobOffer(@Valid @RequestBody JobOffer jobOffer) throws URISyntaxException {
        log.debug("REST request to save JobOffer : {}", jobOffer);
        if (jobOffer.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new jobOffer cannot already have an ID").body(null);
        }
        String currentLogin = SecurityUtils.getCurrentLogin();
        Optional<User> currentUser = userRepository.findOneByLogin(currentLogin);
        jobOffer.setOwner(currentUser.get());
        JobOffer result = jobOfferRepository.save(jobOffer);
        return ResponseEntity.created(new URI("/api/jobOffers/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("jobOffer", result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /jobOffers -> Updates an existing jobOffer.
     */
    @RequestMapping(value = "/jobOffers",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<JobOffer> updateJobOffer(@Valid @RequestBody JobOffer jobOffer) throws URISyntaxException {
        log.debug("REST request to update JobOffer : {}", jobOffer);
        if (jobOffer.getId() == null) {
            return createJobOffer(jobOffer);
        }
        JobOffer result = jobOfferRepository.save(jobOffer);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("jobOffer", jobOffer.getId().toString()))
                .body(result);
    }

    /**
     * GET  /jobOffers -> get all the jobOffers.
     */
/*
    @RequestMapping(value = "/jobOffers",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<JobOffer>> getAllJobOffers(Pageable pageable)
        throws URISyntaxException {
        Page<JobOffer> page = jobOfferRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/jobOffers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
*/

    @RequestMapping(value = "/jobOffers",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<JobOffer>> getAllJobOffers(Pageable pageable)
        throws URISyntaxException {
        List<JobOffer> list = jobOfferRepository.findByOwnerIsCurrentUser();
        Page<JobOffer> page = new Page<JobOffer>() {
            @Override
            public int getTotalPages() {
                return 1;
            }

            @Override
            public long getTotalElements() {
                return list.size();
            }

            @Override
            public int getNumber() {
                return 0;
            }

            @Override
            public int getSize() {
                return list.size();
            }

            @Override
            public int getNumberOfElements() {
                return list.size();
            }

            @Override
            public List<JobOffer> getContent() {
                return list;
            }

            @Override
            public boolean hasContent() {
                return true;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public boolean isFirst() {
                return true;
            }

            @Override
            public boolean isLast() {
                return true;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }

            @Override
            public Iterator<JobOffer> iterator() {
                return list.iterator();
            }
        };
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/jobOffers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /jobOffers/:id -> get the "id" jobOffer.
     */
    @RequestMapping(value = "/jobOffers/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<JobOffer> getJobOffer(@PathVariable Long id) {
        log.debug("REST request to get JobOffer : {}", id);
        return Optional.ofNullable(jobOfferRepository.findOne(id))
            .map(jobOffer -> new ResponseEntity<>(
                jobOffer,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /jobOffers/:id -> delete the "id" jobOffer.
     */
    @RequestMapping(value = "/jobOffers/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteJobOffer(@PathVariable Long id) {
        log.debug("REST request to delete JobOffer : {}", id);
        jobOfferRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("jobOffer", id.toString())).build();
    }


    /**
     * GET  /jobOffers -> get all the jobOffers.
     */
    @RequestMapping(value = "/offers",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<JobOffer>> getAllOffers(Pageable pageable)
            throws URISyntaxException {
        Page<JobOffer> page = jobOfferRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/offers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
