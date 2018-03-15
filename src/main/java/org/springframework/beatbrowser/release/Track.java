package org.springframework.beatbrowser.release;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tracks")
public class Track extends Release {

}
