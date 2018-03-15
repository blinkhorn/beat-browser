package org.springframework.beatbrowser.artist;

import org.springframework.beatbrowser.model.NamedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "artists")
public class Artist extends NamedEntity {
    @Column
}
