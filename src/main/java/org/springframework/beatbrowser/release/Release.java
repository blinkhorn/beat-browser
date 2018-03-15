package org.springframework.beatbrowser.release;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.beatbrowser.artist.Artist;
import org.springframework.beatbrowser.model.MusicEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.*;

@Entity
@Table(name = "releases")
public class Release extends MusicEntity {


}
