package com.jobvacancy.web.rest.dto;

import com.jobvacancy.web.rest.errors.InvalidEmailException;
import com.jobvacancy.web.rest.errors.InvalidUrlException;

import java.util.regex.Pattern;

public class JobApplicationDTO {

    private static final String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$";
    private static final String URL_PATTERN = "^(ftp|http|https)://?(([\\w!~*'().&=+$%-]+: )?[\\w!~*'().&=+$%-]+@)?(([0-9]{1,3}\\.){3}[0-9]{1,3}|([\\w!~*'()-]+\\.)*([\\w^-][\\w-]{0,61})?[\\w]\\.[a-z]{2,6})(:[0-9]{1,4})?((/*)|(/+[\\w!~*'().;?:@&=+$,%#-]+)+/*)$";

    private Long offerId;
    private String fullname;
    private String email;
    private String url;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void validate() {
        validateEmail();
        validateUrl();
    }

    private void validateUrl() {
        if (url == null) {
            throw new IllegalArgumentException("Url is required");
        }
        if (!isMatchWithRegexAndField(URL_PATTERN, url.toLowerCase())) {
            String message = String.format("Invalid url : %s", url);
            throw new InvalidUrlException(message);
        }
    }

    private void validateEmail() {
        if (email == null) {
            throw new IllegalArgumentException("E-mail is required");
        }
        if (!isMatchWithRegexAndField(EMAIL_PATTERN, email.toLowerCase())) {
            String message = String.format("Invalid email : %s", email);
            throw new InvalidEmailException(message);
        }
    }

    private boolean isMatchWithRegexAndField(String regex, String field) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(field).matches();
    }

}
