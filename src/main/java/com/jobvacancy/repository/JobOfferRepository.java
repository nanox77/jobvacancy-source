package com.jobvacancy.repository;

import com.jobvacancy.domain.JobOffer;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the JobOffer entity.
 */
public interface JobOfferRepository extends JpaRepository<JobOffer,Long> {

    @Query("select jobOffer from JobOffer jobOffer where jobOffer.owner.login = ?#{principal.username}")
    List<JobOffer> findByOwnerIsCurrentUser();

}
