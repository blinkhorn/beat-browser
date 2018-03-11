package org.springframework.beatbrowser.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.beatbrowser.artist.Artist;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.util.*;

@MappedSuperclass
public class MusicEntity extends BaseEntity {
    
    @Column(name = "genre")
    @NotEmpty
    private String genre;

    @Column(name = "title")
    @NotEmpty
    private String title;

    public String getGenre() { return this.genre; }

    public void setGenre(String genre) { this.genre = genre; }

    public String getTitle() { return this.title; }

    public void setTitle(String title) { this.title = title; }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "musicEntity")
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
        // TODO: pull pets from API
    }


}
