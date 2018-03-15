package org.springframework.beatbrowser.artist;

import org.springframework.beatbrowser.model.NamedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "artists")
public class Artist extends NamedEntity {
    @Column(name = "dob") //date of birth
    @NotEmpty
    private String dob;

    @Column(name = "nationality")
    @NotEmpty
    private String nationality;



}
