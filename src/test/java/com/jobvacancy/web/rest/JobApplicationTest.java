package com.jobvacancy.web.rest;

import com.jobvacancy.web.rest.dto.JobApplicationDTO;
import com.jobvacancy.web.rest.errors.InvalidEmailException;
import org.junit.Test;

public class JobApplicationTest {

    private static final String APPLICANT_FULLNAME = "THE APPLICANT";
    private static final long OFFER_ID = 1;

    @Test(expected = InvalidEmailException.class)
    public void validateShouldNotAllowEmailWithSpecialCharacters() {
        String APPLICANT_EMAIL = "APPLICAN/T@TEST.COM";
        JobApplicationDTO dto = new JobApplicationDTO();
        dto.setEmail(APPLICANT_EMAIL);
        dto.setFullname(APPLICANT_FULLNAME);
        dto.setOfferId(OFFER_ID);

        dto.validate();
    }

    @Test(expected = InvalidEmailException.class)
    public void validateShouldNotAllowEmailWithoutDomain() {
        String APPLICANT_EMAIL = "APPLICAN";
        JobApplicationDTO dto = new JobApplicationDTO();
        dto.setEmail(APPLICANT_EMAIL);
        dto.setFullname(APPLICANT_FULLNAME);
        dto.setOfferId(OFFER_ID);

        dto.validate();
    }

    @Test(expected = InvalidEmailException.class)
    public void validateShouldNotAllowEmailWithBlankSpaces() {
        String APPLICANT_EMAIL = "   @hotmail.com";
        JobApplicationDTO dto = new JobApplicationDTO();
        dto.setEmail(APPLICANT_EMAIL);
        dto.setFullname(APPLICANT_FULLNAME);
        dto.setOfferId(OFFER_ID);

        dto.validate();
    }

    @Test(expected = InvalidEmailException.class)
    public void validateShouldNotAllowEmailWithoutName() {
        String APPLICANT_EMAIL = "@hotmail.com";
        JobApplicationDTO dto = new JobApplicationDTO();
        dto.setEmail(APPLICANT_EMAIL);
        dto.setFullname(APPLICANT_FULLNAME);
        dto.setOfferId(OFFER_ID);

        dto.validate();
    }

    @Test(expected = InvalidEmailException.class)
    public void validateShouldNotAllowEmailWithoutExtension() {
        String APPLICANT_EMAIL = "@hotmail";
        JobApplicationDTO dto = new JobApplicationDTO();
        dto.setEmail(APPLICANT_EMAIL);
        dto.setFullname(APPLICANT_FULLNAME);
        dto.setOfferId(OFFER_ID);

        dto.validate();
    }
}
