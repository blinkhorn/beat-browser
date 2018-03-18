package org.springframework.beatbrowser.label;

import org.springframework.beatbrowser.artist.Artist;
import org.springframework.beatbrowser.release.Release;
import org.springframework.beatbrowser.model.NamedEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "labels")
public class Label extends NamedEntity {

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "labels")
    private Set<Artist> artists;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "label")
    private Set<Release> releases;




}
