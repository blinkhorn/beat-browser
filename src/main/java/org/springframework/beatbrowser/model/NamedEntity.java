package org.springframework.beatbrowser.model;

import org.springframework.core.style.ToStringCreator;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;

@MappedSuperclass
public class NamedEntity extends BaseEntity {

    @Column(name = "name")
    @NotEmpty
    private String name;

    @Column(name = "year")
    @NotEmpty
    private int year;

    @Column(name = "country")
    @NotEmpty
    private String country;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("name", this.getName())
                .append("year", this.getYear())
                .append("country", this.getCountry()).toString();
    }

}
