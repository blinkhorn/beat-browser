package org.springframework.beatbrowser.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.util.Set;

@MappedSuperclass
public class MusicEntity extends BaseEntity {

    @Column(name = "genre")
    @NotEmpty
    private String genre;

    @Column(name = "title")
    @NotEmpty
    private String title;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "musicEntity")
//    private Set<Artist> artists;


}
