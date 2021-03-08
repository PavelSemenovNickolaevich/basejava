package com.urise.webapp.model;

import java.util.List;

public class Organization {
    private List<Experience> experienceList;

    public Organization(List<Experience> experienceList) {
        this.experienceList = experienceList;
    }

    public List<Experience> getExperienceList() {
        return experienceList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return experienceList != null ? experienceList.equals(that.experienceList) : that.experienceList == null;
    }

    @Override
    public int hashCode() {
        return experienceList != null ? experienceList.hashCode() : 0;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Experience s : this.experienceList) {
            result.append("*").append(s).append("\n");
        }
        return result.toString();

    }
}
