package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Experience {

    private final LocalDate beginDate;
    private final LocalDate endDate;
    private final String title;
    private final String description;

    public Experience(LocalDate beginDate, LocalDate endDate, String title, String description) {
        Objects.requireNonNull(beginDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        Objects.requireNonNull(title, "title must not be null");
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
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
        return Objects.equals(beginDate, that.beginDate) && Objects.equals(endDate, that.endDate) && Objects.equals(title, that.title) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beginDate, endDate, title, description);
    }

    @Override
    public String toString() {
        return "Experience{" +
                "beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
