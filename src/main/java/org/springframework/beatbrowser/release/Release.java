package org.springframework.beatbrowser.release;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.beatbrowser.artist.Artist;
import org.springframework.beatbrowser.model.MusicEntity;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Entity
@Table(name = "releases")
public class Release extends MusicEntity {

    @Column(name = "numberTracks")
    @NotEmpty
    private int numberTracks;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "releases")
    private Set<Artist> artists;

    public int getNumberTracks() {
        return this.numberTracks;
    }

    public void setNumberTracks(int numberTracks) {
        this.numberTracks = numberTracks;
    }

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
        artist.setReleases((Set<Release>) this);
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

    @Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId())
                .append("new", this.isNew())
                .append("genre", this.getGenre())
                .append("title", this.getTitle())
                .append("numberTracks", this.numberTracks)
                .append("artists", this.artists).toString();
    }

}
