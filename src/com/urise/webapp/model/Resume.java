package com.urise.webapp.model;


import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    final private String uuid;
    private String fullName;
    ContactsType type;
    String name;

    final private  Map<ContactsType, String> contacts = new EnumMap<>(ContactsType.class);

    final private Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);

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

    public void setContacts(ContactsType type, String name) {
        this.type = type;
        this.name = name;

    }

    public String getUuid() {
        return uuid;
    }

//    public void setUuid(String uuid) {
//        this.uuid = uuid;
//    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (uuid != null ? !uuid.equals(resume.uuid) : resume.uuid != null) return false;
        if (fullName != null ? !fullName.equals(resume.fullName) : resume.fullName != null) return false;
        if (type != resume.type) return false;
        if (name != null ? !name.equals(resume.name) : resume.name != null) return false;
        if (contacts != null ? !contacts.equals(resume.contacts) : resume.contacts != null) return false;
        return sections != null ? sections.equals(resume.sections) : resume.sections == null;
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (contacts != null ? contacts.hashCode() : 0);
        result = 31 * result + (sections != null ? sections.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "uuid='" + uuid + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }

    @Override
    public int compareTo(Resume o) {
        int res = fullName.compareTo(o.fullName);
        return (res == 0) ? uuid.compareTo(o.uuid) : fullName.compareTo(o.fullName);
    }

}
