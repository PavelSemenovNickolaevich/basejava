package com.urise.webapp.model;

import java.time.LocalDate;

public class WorkExperienceLearnSection extends AbstractSection {

    private String nameWorkPlace;

    private String nameLearnPlace;

    private LocalDate beginDate;

    private LocalDate endDate;

    private String title;

    private String description;

    public WorkExperienceLearnSection(String nameWorkPlace, String nameLearnPlace, LocalDate beginDate
            , LocalDate endDate, String title, String description) {
        this.nameWorkPlace = nameWorkPlace;
        this.nameLearnPlace = nameLearnPlace;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkExperienceLearnSection that = (WorkExperienceLearnSection) o;

        if (nameWorkPlace != null ? !nameWorkPlace.equals(that.nameWorkPlace) : that.nameWorkPlace != null)
            return false;
        if (nameLearnPlace != null ? !nameLearnPlace.equals(that.nameLearnPlace) : that.nameLearnPlace != null)
            return false;
        if (beginDate != null ? !beginDate.equals(that.beginDate) : that.beginDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = nameWorkPlace != null ? nameWorkPlace.hashCode() : 0;
        result = 31 * result + (nameLearnPlace != null ? nameLearnPlace.hashCode() : 0);
        result = 31 * result + (beginDate != null ? beginDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WorkExperienceLearnSection{" +
                "nameWorkPlace='" + nameWorkPlace + '\'' +
                ", nameLearnPlace='" + nameLearnPlace + '\'' +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
