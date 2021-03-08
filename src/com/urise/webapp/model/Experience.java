package com.urise.webapp.model;

import java.time.LocalDate;

public class Experience extends AbstractSection {

    private String nameOrganization;
    private LocalDate beginDate;
    private LocalDate endDate;
    private String title;
    private String description;

    public Experience(String nameOrganization, LocalDate beginDate, LocalDate endDate, String title, String description) {
        this.nameOrganization = nameOrganization;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    public String getNameOrganization() {
        return nameOrganization;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Experience that = (Experience) o;

        if (nameOrganization != null ? !nameOrganization.equals(that.nameOrganization) : that.nameOrganization != null)
            return false;
        if (beginDate != null ? !beginDate.equals(that.beginDate) : that.beginDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = nameOrganization != null ? nameOrganization.hashCode() : 0;
        result = 31 * result + (beginDate != null ? beginDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return nameOrganization + " " +
                beginDate + " - " +
                endDate + " " +
                title + " " +
                description;
    }
}
