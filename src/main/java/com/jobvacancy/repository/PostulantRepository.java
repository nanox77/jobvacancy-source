package com.jobvacancy.repository;

import com.jobvacancy.domain.JobOffer;
import com.jobvacancy.domain.Postulant;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the postulant entity.
 */
public interface PostulantRepository extends JpaRepository<Postulant,Long> {

	@Query("select postulant from Postulant postulant where postulant.id = ?#{jobOffer.postulants.id}")
    List<Postulant> findByJobOfferId();
	
}

