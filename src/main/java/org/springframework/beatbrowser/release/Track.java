package org.springframework.beatbrowser.release;

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

    public void setRelease(Release release) {
        this.release = release;
    }
}
