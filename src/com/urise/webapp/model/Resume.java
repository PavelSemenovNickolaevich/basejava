package com.urise.webapp.model;


import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume>, Serializable {

    private static final long serialVersionUID = 1L;

    // Unique identifier
    private final String uuid;
    private final String fullName;

    private final Map<ContactsType, String> contacts = new EnumMap<>(ContactsType.class);

    private final Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getContact(ContactsType type) {
        return contacts.get(type);
    }

    public AbstractSection getSection(SectionType type) {
        return sections.get(type);
    }

    public String getUuid() {
        return uuid;
    }

    public void addContact(ContactsType type, String info) {
        contacts.put(type, info);
    }

    public void addSection(SectionType type, AbstractSection section) {
        sections.put(type, section);
    }

//    public void setUuid(String uuid) {
//        this.uuid = uuid;
//    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public int compareTo(Resume o) {
        int res = fullName.compareTo(o.fullName);
        return (res == 0) ? uuid.compareTo(o.uuid) : fullName.compareTo(o.fullName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) && Objects.equals(fullName, resume.fullName) && Objects.equals(contacts, resume.contacts) && Objects.equals(sections, resume.sections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, contacts, sections);
    }

    @Override
    public String toString() {
        return "Resume{" +
                "uuid='" + uuid + '\'' +
                ", fullName='" + fullName + '\'' +
                ", contacts=" + contacts +
                ", sections=" + sections +
                '}';
    }
}
