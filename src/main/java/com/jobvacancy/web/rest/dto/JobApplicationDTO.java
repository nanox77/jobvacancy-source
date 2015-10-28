package com.jobvacancy.web.rest.dto;

import com.jobvacancy.web.rest.errors.InvalidEmailException;
import com.jobvacancy.web.rest.errors.InvalidSizeException;
import org.springframework.web.multipart.MultipartFile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nicopaez on 10/11/15.
 */
public class JobApplicationDTO {

    private static final String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$";

    private Long offerId;
    private String fullname;
    private String email;
    private MultipartFile file;

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long id) {
        this.offerId = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public void validate() {
        validateEmail();
        validateSizeFile();
    }

    private void validateSizeFile() {
        if (file != null) {
            double sizeInMb = file.getSize() / (1024 * 1024);
            double maxInMb = 1.0d;
            if (sizeInMb > maxInMb) {
                String message = String.format("Size cannot be greater than : %s", maxInMb);
                throw new InvalidSizeException(message);
            }
        }
    }

    private void validateEmail() {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            String message = String.format("Invalid email : %s", email);
            throw new InvalidEmailException(message);
        }
    }

}
