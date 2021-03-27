package com.urise.webapp.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OrganizationSection extends AbstractSection implements Serializable {
    private static final long serialVersionUID = 1L;
    private final List<Organization> experienceList;

    public OrganizationSection(List<Organization> experienceList) {
        Objects.requireNonNull(experienceList, "experience must not be null");
        this.experienceList = experienceList;
    }

    public OrganizationSection(Organization...organizations) {
        this(Arrays.asList(organizations));
    }

    public List<Organization> getExperienceList() {
        return experienceList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationSection that = (OrganizationSection) o;
        return Objects.equals(experienceList, that.experienceList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(experienceList);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Organization s : this.experienceList) {
            result.append("*").append(s).append("\n");
        }
        return result.toString();

    }
}
