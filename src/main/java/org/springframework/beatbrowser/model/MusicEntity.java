package org.springframework.beatbrowser.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class MusicEntity extends BaseEntity {

    @Column(name = "genre")
    @NotEmpty
    private String genre;

    @Column(name = "title")
    @NotEmpty
    private String title;

    
}
