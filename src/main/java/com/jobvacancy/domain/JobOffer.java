package com.jobvacancy.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * A JobOffer.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "JOB_OFFER")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class JobOffer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "location")
    private String location;

    @Column(name = "description")
    private String description;
    
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Postulant> postulants = new HashSet<Postulant>();   
    
    @ManyToOne
    private User owner;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public  Collection<Postulant> getPostulant() {
        return postulants;
    }

    public void addPostulant(Postulant postulant) {
        this.postulants.add(postulant);
    }

    
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User user) {
        this.owner = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JobOffer jobOffer = (JobOffer) o;

        if ( ! Objects.equals(id, jobOffer.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "JobOffer{" +
                "id=" + id +
                ", title='" + title + "'" +
                ", location='" + location + "'" +
                ", description='" + description + "'" +
                '}';
    }
	
}
