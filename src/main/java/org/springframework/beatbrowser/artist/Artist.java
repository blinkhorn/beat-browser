package org.springframework.beatbrowser.artist;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.beatbrowser.model.NamedEntity;
import org.springframework.beatbrowser.release.Release;
import org.springframework.beatbrowser.label.Label;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.*;

@Entity
@Table(name = "artists")
public class Artist extends NamedEntity {

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "artist")
    private Set<Release> releases;

    @ManyToMany(cascade = CascadeType.ALL,  mappedBy = "artist")
    private Set<Label> labels;

    protected Set<Release> getReleasesInternal() {
        if (this.releases == null) {
            this.releases = new HashSet<Release>();
        }
        return this.releases;
    }

    public void setReleases(Set<Release> releases) {
        this.releases = releases;
    }

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
        release.setArtists((Set<Artist>) this);
    }

    /**
     * Return the Release with the given title, or null if none found for this Artist.
     *
     * @param title to test
     * @return true if release title is already in use
     */
    public Release getRelease(String title) {
        return getRelease(title, false);
    }

    /**
     * Return the Release with the given title, or null if none found for this Artist.
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

    protected Set<Label> getLabelsInternal() {
        if (this.labels == null) {
            this.labels = new HashSet<Label>();
        }
        return this.labels;
    }

    public void setLabels(Set<Label> labels) {
        this.labels = labels;
    }

    public List<Label> getLabels() {
        List<Label> sortedLabels = new ArrayList<Label>(getLabelsInternal());
        PropertyComparator.sort(sortedLabels,
                new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedLabels);
    }

    public void addLabel(Label label) {
        if (label.isNew()) {
            getLabelsInternal().add(label);
        }
        label.setArtists((Set<Artist>) this);
    }

    /**
     * Return the Label with the given name, or null if none found for this Artist.
     *
     * @param name to test
     * @return true if label name is already in use
     */
    public Label getLabel(String name) {
        return getLabel(name, false);
    }

    /**
     * Return the Label with the given name, or null if none found for this Artist.
     *
     * @param name to test
     * @return true if label name is already in use
     */
    public Label getLabel(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (Label label : getLabelsInternal()) {
            if (!ignoreNew || !label.isNew()) {
                String compName = label.getName();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {
                    return label;
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
                .append("releases", this.releases)
                .append("labels", this.labels).toString();
    }

}
