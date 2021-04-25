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
        int count = 0;
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
                        List<Organization> list2 = ((OrganizationSection) section).getExperienceList();
                        dos.writeInt(list2.size());
                        for (Organization organization : ((OrganizationSection) section).getExperienceList()) {
                            dos.writeUTF(organization.getName());
                            dos.writeUTF(organization.getUrl());
                            List<Organization.Experience> list3 = organization.getExperiences();
                            dos.writeInt(list3.size());
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

    private void writeDate(DataOutputStream dos, LocalDate beginDate) throws IOException {
        dos.writeInt(beginDate.getYear());
        dos.writeInt(beginDate.getMonth().getValue());
        dos.writeInt(beginDate.getDayOfMonth());
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
                        resume.addSection(sectionType, new OrganizationSection(new Organization(dis.readUTF()
                                , dis.readUTF(), new Organization.Experience(readDate(dis), readDate(dis)
                                , dis.readUTF(), dis.readUTF()))));
                }
            }
            return resume;
        }
    }

    private LocalDate readDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), dis.readInt());
    }

    private List<String> readListSection(DataInputStream dis) throws IOException {
        int size = dis.readInt();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(dis.readUTF());
        }
        System.out.println(list);
        return list;
    }
}
