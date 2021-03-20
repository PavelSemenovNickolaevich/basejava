package com.urise.webapp.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BulletedListSection extends AbstractSection {

    private List<String> text;

    public BulletedListSection(List<String> text) {
        Objects.requireNonNull(text, "experience must not be null");
        this.text = text;
    }

    public BulletedListSection(String...text) {
        this(Arrays.asList(text));
    }

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BulletedListSection that = (BulletedListSection) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (String s : this.text) {
            result.append("*").append(s).append("\n");
        }
        return result.toString();
    }
}

