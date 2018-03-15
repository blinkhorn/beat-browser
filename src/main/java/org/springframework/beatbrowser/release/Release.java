package org.springframework.beatbrowser.release;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.beatbrowser.artist.Artist;
import org.springframework.beatbrowser.model.MusicEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.*;

@Entity
@Table(name = "releases")
public class Release extends MusicEntity {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "release")
    private Set<Artist> artists;

    protected Set<Artist> getArtistsInternal() {
        if (this.artists == null) {
            this.artists = new HashSet<Artist>();
        }
        return this.artists;
    }

    protected void setArtistsInternal(Set<Artist> artists) { this.artists = artists; }

    public List<Artist> getArtists() {
        List<Artist> sortedArtists = new ArrayList<Artist>(getArtistsInternal());
        PropertyComparator.sort(sortedArtists,
                new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedArtists);
    }

    public void addArtist(Artist artist) {
        if (artist.isNew()) {
            getArtistsInternal().add(artist);
        }
//        artist.setRelease(this);
    }
}
