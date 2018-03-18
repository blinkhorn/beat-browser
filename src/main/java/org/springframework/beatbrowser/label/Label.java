package org.springframework.beatbrowser.label;

import org.springframework.beatbrowser.model.NamedEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "labels")
public class Label extends NamedEntity {

}
