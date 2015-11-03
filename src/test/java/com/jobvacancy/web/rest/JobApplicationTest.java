package com.jobvacancy.web.rest;

import com.jobvacancy.web.rest.dto.JobApplicationDTO;
import com.jobvacancy.web.rest.errors.InvalidEmailException;
import com.jobvacancy.web.rest.errors.InvalidUrlException;
import org.junit.Test;

public class JobApplicationTest {

    private static final String APPLICANT_FULLNAME = "THE APPLICANT";
    private static final String APPLICANT_VALID_URL = "WWW.DROPBOX.COM/CV2015";
    private static final String APPLICANT_VALID_EMAIL = "APPLICANT@GMAIL.COM";
    private static final long OFFER_ID = 1;

    @Test(expected = InvalidEmailException.class)
    public void validateShouldNotAllowEmailWithSpecialCharacters() {
        String APPLICANT_EMAIL = "APPLICAN/T@TEST.COM";
        JobApplicationDTO dto = new JobApplicationDTO();
        dto.setEmail(APPLICANT_EMAIL);
        dto.setFullname(APPLICANT_FULLNAME);
        dto.setOfferId(OFFER_ID);
        dto.setUrl(APPLICANT_VALID_URL);

        dto.validate();
    }

    @Test(expected = InvalidEmailException.class)
    public void validateShouldNotAllowEmailWithoutDomain() {
        String APPLICANT_EMAIL = "APPLICAN";
        JobApplicationDTO dto = new JobApplicationDTO();
        dto.setEmail(APPLICANT_EMAIL);
        dto.setFullname(APPLICANT_FULLNAME);
        dto.setOfferId(OFFER_ID);
        dto.setUrl(APPLICANT_VALID_URL);

        dto.validate();
    }

    @Test(expected = InvalidEmailException.class)
    public void validateShouldNotAllowEmailWithBlankSpaces() {
        String APPLICANT_EMAIL = "   @hotmail.com";
        JobApplicationDTO dto = new JobApplicationDTO();
        dto.setEmail(APPLICANT_EMAIL);
        dto.setFullname(APPLICANT_FULLNAME);
        dto.setOfferId(OFFER_ID);
        dto.setUrl(APPLICANT_VALID_URL);

        dto.validate();
    }

    @Test(expected = InvalidEmailException.class)
    public void validateShouldNotAllowEmailWithoutName() {
        String APPLICANT_EMAIL = "@hotmail.com";
        JobApplicationDTO dto = new JobApplicationDTO();
        dto.setEmail(APPLICANT_EMAIL);
        dto.setFullname(APPLICANT_FULLNAME);
        dto.setOfferId(OFFER_ID);
        dto.setUrl(APPLICANT_VALID_URL);

        dto.validate();
    }

    @Test(expected = InvalidEmailException.class)
    public void validateShouldNotAllowEmailWithoutExtension() {
        String APPLICANT_EMAIL = "@hotmail";
        JobApplicationDTO dto = new JobApplicationDTO();
        dto.setEmail(APPLICANT_EMAIL);
        dto.setFullname(APPLICANT_FULLNAME);
        dto.setOfferId(OFFER_ID);
        dto.setUrl(APPLICANT_VALID_URL);

        dto.validate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateShouldNotAllowNullEmail() {
        JobApplicationDTO dto = new JobApplicationDTO();
        dto.setEmail(null);
        dto.setFullname(APPLICANT_FULLNAME);
        dto.setOfferId(OFFER_ID);
        dto.setUrl(APPLICANT_VALID_URL);

        dto.validate();
    }

    @Test(expected = InvalidUrlException.class)
    public void validateShouldNotAllowUrlWithInvalidProtocol() {
        String applicantInvalidUrl = "WW2W.GOOGLE.COM/CV/CV_2015";
        JobApplicationDTO dto = new JobApplicationDTO();
        dto.setEmail(APPLICANT_VALID_EMAIL);
        dto.setFullname(APPLICANT_FULLNAME);
        dto.setOfferId(OFFER_ID);
        dto.setUrl(applicantInvalidUrl);

        dto.validate();
    }

    @Test(expected = InvalidUrlException.class)
    public void validateShouldNotAllowUrlWithAddressWithBlankSpaces() {
        String applicantInvalidUrl = "WWW.GOOG   LE.COM/CV/CV_2015";
        JobApplicationDTO dto = new JobApplicationDTO();
        dto.setEmail(APPLICANT_VALID_EMAIL);
        dto.setFullname(APPLICANT_FULLNAME);
        dto.setOfferId(OFFER_ID);
        dto.setUrl(applicantInvalidUrl);

        dto.validate();
    }

    @Test(expected = InvalidUrlException.class)
    public void validateShouldNotAllowUrlWithAddressWithNumberInDomain() {
        String applicantInvalidUrl = "WWW.GOOGLE.C222OM/CV/CV_2015";
        JobApplicationDTO dto = new JobApplicationDTO();
        dto.setEmail(APPLICANT_VALID_EMAIL);
        dto.setFullname(APPLICANT_FULLNAME);
        dto.setOfferId(OFFER_ID);
        dto.setUrl(applicantInvalidUrl);

        dto.validate();
    }

    @Test(expected = InvalidUrlException.class)
    public void validateShouldNotAllowUrlWithAddressWithSpecialCharacters() {
        String applicantInvalidUrl = "WWW.GOOGLE.C222OM!%$%#$%";
        JobApplicationDTO dto = new JobApplicationDTO();
        dto.setEmail(APPLICANT_VALID_EMAIL);
        dto.setFullname(APPLICANT_FULLNAME);
        dto.setOfferId(OFFER_ID);
        dto.setUrl(applicantInvalidUrl);

        dto.validate();
    }

    @Test(expected = InvalidUrlException.class)
    public void validateShouldNotAllowUrlWithBlankAddress() {
        String applicantInvalidUrl = "";
        JobApplicationDTO dto = new JobApplicationDTO();
        dto.setEmail(APPLICANT_VALID_EMAIL);
        dto.setFullname(APPLICANT_FULLNAME);
        dto.setOfferId(OFFER_ID);
        dto.setUrl(applicantInvalidUrl);

        dto.validate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateShouldNotAllowNullUrl() {
        JobApplicationDTO dto = new JobApplicationDTO();
        dto.setEmail(APPLICANT_VALID_EMAIL);
        dto.setFullname(APPLICANT_FULLNAME);
        dto.setOfferId(OFFER_ID);
        dto.setUrl(null);

        dto.validate();
    }
}
