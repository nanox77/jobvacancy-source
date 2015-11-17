package com.jobvacancy.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.jobvacancy.domain.JobOffer;
import com.jobvacancy.domain.Postulant;
import com.jobvacancy.domain.User;
import com.jobvacancy.repository.JobOfferRepository;
import com.jobvacancy.repository.UserRepository;
import com.jobvacancy.security.SecurityUtils;
import com.jobvacancy.service.MailService;
import com.jobvacancy.web.rest.dto.JobApplicationDTO;
import com.jobvacancy.web.rest.errors.InvalidApplyJobOfferException;
import com.jobvacancy.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class JobApplicationResource {

    private final Logger log = LoggerFactory.getLogger(JobOfferResource.class);

    @Inject
    private JobOfferRepository jobOfferRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private MailService mailService;

    /**
     * POST  /Application -> Create a new jobOffer.
     */
    @RequestMapping(value = "/Application",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<JobOffer> createJobApplication(@Valid @RequestBody JobApplicationDTO jobApplication)
        throws URISyntaxException {
        log.debug("REST request to save JobApplication : {}", jobApplication);
        jobApplication.validate();
        JobOffer jobOffer = jobOfferRepository.findOne(jobApplication.getOfferId());
        validateOffer(jobOffer.getOwner().getId());
        Postulant postulant = jobApplication.getPostulant();
        jobOffer.addPostulant(postulant);
        jobOfferRepository.save(jobOffer);
        this.mailService.sendApplication(jobApplication.getEmail(), jobApplication.getUrl(), jobOffer);
        sendMailForMaxCapacity(jobOffer);
        return ResponseEntity.accepted()
            .headers(HeaderUtil.createAlert("Application created and sent offer's owner", "")).body(null);
    }

    private void sendMailForMaxCapacity(JobOffer jobOffer) {
        Integer capacity = jobOffer.getCapacity();
        int postulantSize = jobOffer.getPostulants().size();
        if (capacity != null && capacity.equals(postulantSize)) {
            this.mailService.sendEmailForMaxCapacity(jobOffer);
        }
    }

    private void validateOffer(Long ownerId) {
        String currentLogin = SecurityUtils.getCurrentLogin();
        Optional<User> currentUser = userRepository.findOneByLogin(currentLogin);
        Long userId = currentUser.get().getId();
        if (userId.equals(ownerId)) {
            throw new InvalidApplyJobOfferException("Cannot apply to offer published by yourself");
        }
    }
}
