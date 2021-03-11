package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Experience extends AbstractSection {

    private final String nameOrganization;
    private final List<ListOfExperience> listOfExperiences;

    public Experience(String nameOrganization, List<ListOfExperience> listOfExperiences) {
        this.nameOrganization = nameOrganization;
        this.listOfExperiences = listOfExperiences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience that = (Experience) o;
        return Objects.equals(nameOrganization, that.nameOrganization) && Objects.equals(listOfExperiences, that.listOfExperiences);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameOrganization, listOfExperiences);
    }

    @Override
    public String toString() {
        return "Experience{" +
                "nameOrganization='" + nameOrganization + '\'' +
                ", listOfExperiences=" + listOfExperiences +
                '}';
    }
}
