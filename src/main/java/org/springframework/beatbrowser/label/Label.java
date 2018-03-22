package org.springframework.beatbrowser.label;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.beatbrowser.artist.Artist;
import org.springframework.beatbrowser.release.Release;
import org.springframework.beatbrowser.model.NamedEntity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "labels")
public class Label extends NamedEntity {

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "labels")
    private Set<Artist> artists;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "label")
    private Set<Release> releases;

    protected Set<Artist> getArtistsInternal() {
        if (this.artists == null) {
            this.artists = new HashSet<Artist>();
        }
        return this.artists;
    }

    protected void setArtistsInternal(Set<Artist> artists) {
        this.artists = artists;
    }

    public void setArtists(Set<Artist> artists) { this.artists = artists; }

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
        artist.setLabels((Set<Label>) this);
    }

    /**
     * Return the Artist with the given name, or null if none found for this Release.
     *
     * @param name to test
     * @return true if artist name is already in use
     */
    public Artist getArtist(String name) {
        return getArtist(name, false);
    }

    /**
     * Return the Artist with the given name, or null if none found for this Release.
     *
     * @param name to test
     * @return true if artist name is already in use
     */
    public Artist getArtist(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (Artist artist : getArtistsInternal()) {
            if (!ignoreNew || !artist.isNew()) {
                String compName = artist.getName();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {
                    return artist;
                }
            }
        }
        return null;
    }


}
