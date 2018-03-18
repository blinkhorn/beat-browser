package org.springframework.beatbrowser.release;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.beatbrowser.artist.Artist;
import org.springframework.beatbrowser.label.Label;
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "releases")
    private Set<Track> tracks;

    @ManyToOne(cascade = CascadeType.ALL)
    private Label label;

    public int getNumberTracks() {
        return this.numberTracks;
    }

    public void setNumberTracks(int numberTracks) {
        this.numberTracks = numberTracks;
    }

    protected Set<Track> getTracksInternal() {
        if (this.tracks == null) {
            this.tracks = new HashSet<Track>();
        }
        return this.tracks;
    }

    protected void setTracksInternal(Set<Track> tracks) {
        this.tracks = tracks;
    }

    public void setTracks(Set<Track> tracks) { this.tracks = tracks; }

    public List<Track> getTracks() {
        List<Track> sortedTracks = new ArrayList<Track>(getTracksInternal());
        PropertyComparator.sort(sortedTracks,
                new MutableSortDefinition("title", true, true));
        return Collections.unmodifiableList(sortedTracks);
    }

    public void addTrack(Track track) {
        if (track.isNew()) {
            getTracksInternal().add(track);
        }
        track.setRelease(this);
    }

    /**
     * Return the Track with the given title, or null if none found for this Release.
     *
     * @param title to test
     * @return true if track title is already in use
     */
    public Track getTrack(String title) {
        return getTrack(title, false);
    }

    /**
     * Return the Track with the given title, or null if none found for this Release.
     *
     * @param title to test
     * @return true if track title is already in use
     */
    public Track getTrack(String title, boolean ignoreNew) {
        title = title.toLowerCase();
        for (Track track : getTracksInternal()) {
            if (!ignoreNew || !track.isNew()) {
                String compName = track.getTitle();
                compName = compName.toLowerCase();
                if (compName.equals(title)) {
                    return track;
                }
            }
        }
        return null;
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
                .append("artists", this.artists)
                .append("tracks", this.tracks).toString();
    }

}
