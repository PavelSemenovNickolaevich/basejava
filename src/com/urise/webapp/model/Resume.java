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
    private  String uuid;

    private  String fullName;

    private  Map<ContactsType, String> contacts = new EnumMap<ContactsType, String>(ContactsType.class);

    private Map<SectionType, AbstractSection> sections = new EnumMap<SectionType, AbstractSection>(SectionType.class);

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

        if (!uuid.equals(resume.uuid)) return false;
        return fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
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
