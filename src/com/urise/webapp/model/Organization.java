package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class Organization extends AbstractSection {

    private final String name;
    private final String url;
    private final List<Experience> experiences;

    public Organization(String name, String url, List<Experience> experiences) {
        this.name = name;
        this.url = url;
        this.experiences = experiences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(name, that.name) && Objects.equals(url, that.url) && Objects.equals(experiences, that.experiences);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url, experiences);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", experiences=" + experiences +
                '}';
    }
}
