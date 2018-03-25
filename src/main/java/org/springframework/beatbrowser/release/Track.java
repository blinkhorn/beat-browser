package org.springframework.beatbrowser.release;

import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "tracks")
public class Track extends Release {
    @Column(name = "length")
    @NotEmpty
    private int length;

    @ManyToOne(cascade = CascadeType.ALL)
    private Release release;

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    protected Release getReleaseInternal() {
        if (this.release == null) {
            this.release = new Release();
        }
        return this.release;
    }

    protected void setReleaseInternal(Release release) {
        this.release = release;
    }

    public void setRelease(Release release) { this.release = release; }

    public Release getRelease() {
        return getReleaseInternal();
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId())
                .append("new", this.isNew())
                .append("genre", this.getGenre())
                .append("title", this.getTitle())
                .append("artists", this.getArtists())
                .append("label", this.getLabel())
                .append("length", this.length)
                .append("release", this.release).toString();

    }
}
