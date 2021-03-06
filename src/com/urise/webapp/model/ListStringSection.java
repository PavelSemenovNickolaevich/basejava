package com.urise.webapp.model;

import java.util.List;

public class ListStringSection extends AbstractSection {

    private List<String> text;

    public ListStringSection(List<String> text) {
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

        ListStringSection that = (ListStringSection) o;

        return text != null ? text.equals(that.text) : that.text == null;
    }

    @Override
    public int hashCode() {
        return text != null ? text.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ListStringSection{" +
                "text=" + text +
                '}';
    }
}
