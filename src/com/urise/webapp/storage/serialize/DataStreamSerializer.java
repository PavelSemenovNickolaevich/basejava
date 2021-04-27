package com.urise.webapp.storage.serialize;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements SerializeStrategy {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactsType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactsType, String> entry : r.getContacts().entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            Map<SectionType, AbstractSection> sections = r.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, AbstractSection> entry : r.getSections().entrySet()) {
                SectionType type = entry.getKey();
                AbstractSection section = entry.getValue();
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(entry.getKey().name());
                        dos.writeUTF(((SingleLineSection) section).getText());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        dos.writeUTF(entry.getKey().name());
                        List<String> list = ((ListSection) section).getText();
                        dos.writeInt(list.size());
                        for (String value : ((ListSection) section).getText()) {
                            dos.writeUTF(value);
                        }
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        dos.writeUTF(entry.getKey().name());
                        List<Organization> organizationsListSize = ((OrganizationSection) section).getExperienceList();
                        dos.writeInt(organizationsListSize.size());
                        for (Organization organization : ((OrganizationSection) section).getExperienceList()) {
                            dos.writeUTF(organization.getName());
                            dos.writeUTF(organization.getUrl());
                            List<Organization.Experience> experiencesSize = organization.getExperiences();
                            dos.writeInt(experiencesSize.size());
                            for (Organization.Experience value : organization.getExperiences()) {
                                writeDate(dos, value.getBeginDate());
                                writeDate(dos, value.getEndDate());
                                dos.writeUTF(value.getTitle());
                                dos.writeUTF(value.getDescription());
                            }
                        }
                        break;
                }
            }
        }
    }

    private void writeDate(DataOutputStream dos, LocalDate date) throws IOException {
        dos.writeInt(date.getYear());
        dos.writeInt(date.getMonth().getValue());
        dos.writeInt(date.getDayOfMonth());
    }

    private LocalDate readDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), dis.readInt());
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                ContactsType contactsType = ContactsType.valueOf(dis.readUTF());
                String value = dis.readUTF();
                resume.addContact(contactsType, value);
            }
            int sizeSections = dis.readInt();
            for (int i = 0; i < sizeSections; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.addSection(sectionType, new SingleLineSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        resume.addSection(sectionType, new ListSection(readListSection(dis)));
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        List<Organization> organizations = new ArrayList<>();
                        int organizationSize = dis.readInt();
                        for (int j = 0; j < organizationSize; j++) {
                            String name = dis.readUTF();
                            String url = dis.readUTF();
                            int positionSize = dis.readInt();
                            List<Organization.Experience> experiences = new ArrayList<>();
                            for (int k = 0; k < positionSize; k++) {
                                LocalDate startDate = readDate(dis);
                                LocalDate endDate = readDate(dis);
                                experiences.add(new Organization.Experience(startDate, endDate, dis.readUTF(), dis.readUTF()));
                            }
                            Organization organization = new Organization(name, url, experiences);
                            organizations.add(organization);
                        }
                        resume.addSection(sectionType, new OrganizationSection(organizations));
                        break;
                }
            }
            return resume;
        }
    }

    private List<String> readListSection(DataInputStream dis) throws IOException {
        int size = dis.readInt();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(dis.readUTF());
        }
        return list;
    }
}
