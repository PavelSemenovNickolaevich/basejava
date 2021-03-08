package com.urise.webapp.model;

import java.util.List;

public class BulletedListSection extends AbstractSection {

    private List<String> text;

    public BulletedListSection(List<String> text) {
        this.text = text;
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

        return text != null ? text.equals(that.text) : that.text == null;
    }

    @Override
    public int hashCode() {
        return text != null ? text.hashCode() : 0;
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

