package org.springframework.beatbrowser.label;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.beatbrowser.artist.Artist;
import org.springframework.beatbrowser.release.Release;
import org.springframework.beatbrowser.model.NamedEntity;
import org.springframework.core.style.ToStringCreator;

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
     * Return the Artist with the given name, or null if none found for this Label.
     *
     * @param name to test
     * @return true if artist name is already in use
     */
    public Artist getArtist(String name) {
        return getArtist(name, false);
    }

    /**
     * Return the Artist with the given name, or null if none found for this Label.
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

    protected Set<Release> getReleasesInternal() {
        if (this.releases == null) {
            this.releases = new HashSet<Release>();
        }
        return this.releases;
    }

    protected void setReleaasesInternal(Set<Release> releases) {
        this.releases = releases;
    }

    public void setReleases(Set<Release> releases) { this.releases = releases; }

    public List<Release> getReleases() {
        List<Release> sortedReleases = new ArrayList<Release>(getReleasesInternal());
        PropertyComparator.sort(sortedReleases,
                new MutableSortDefinition("title", true, true));
        return Collections.unmodifiableList(sortedReleases);
    }

    public void addRelease(Release release) {
        if (release.isNew()) {
            getReleasesInternal().add(release);
        }
        release.setLabel(this);
    }

    /**
     * Return the Release with the given title, or null if none found for this Label
     *
     * @param title to test
     * @return true if release title is already in use
     */
    public Release getRelease(String title) {
        return getRelease(title, false);
    }

    /**
     * Return the Release with the given title, or null if none found for this Label.
     *
     * @param title to test
     * @return true if release title is already in use
     */
    public Release getRelease(String title, boolean ignoreNew) {
        title = title.toLowerCase();
        for (Release release : getReleasesInternal()) {
            if (!ignoreNew || !release.isNew()) {
                String compName = release.getTitle();
                compName = compName.toLowerCase();
                if (compName.equals(title)) {
                    return release;
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
                .append("name", this.getName())
                .append("year", this.getYear())
                .append("country", this.getCountry())
                .append("artists", this.artists)
                .append("releases", this.releases).toString();
    }

}
