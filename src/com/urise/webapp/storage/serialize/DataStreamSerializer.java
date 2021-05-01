package com.urise.webapp.storage.serialize;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataStreamSerializer implements SerializeStrategy {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            writeWithExeption(dos, r.getContacts().entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });
            writeWithExeption(dos, r.getSections().entrySet(), entry -> {
                SectionType type = entry.getKey();
                AbstractSection section = entry.getValue();
                String name = entry.getKey().name();
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(name);
                        dos.writeUTF(((SingleLineSection) section).getText());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        dos.writeUTF(name);
                        writeWithExeption(dos, ((ListSection) section).getText(), value -> {
                            dos.writeUTF(value);
                        });
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        dos.writeUTF(name);
                        writeWithExeption(dos, ((OrganizationSection) section).getExperienceList(), organization -> {
                            dos.writeUTF(organization.getName());
                            dos.writeUTF(organization.getUrl());
                            writeWithExeption(dos, organization.getExperiences(), value -> {
                                writeDate(dos, value.getBeginDate());
                                writeDate(dos, value.getEndDate());
                                dos.writeUTF(value.getTitle());
                                dos.writeUTF(value.getDescription());
                            });
                        });
                        break;
                }
            });
        }
    }

    private <T> void writeWithExeption(DataOutputStream dos, Collection<T> collection, Consumer<T> write) throws IOException {
        dos.writeInt(collection.size());
        for (T t : collection) {
            write.write(t);
        }
    }

    public interface Consumer<T> {
        void write(T t) throws IOException;
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
                        List<String> listSection = new ArrayList<>();
                        int listSize = dis.readInt();
                        for (int c = 0; c < listSize; c++) {
                            listSection.add(dis.readUTF());
                        }
                        //                      resume.addSection(sectionType, new ListSection(readListSection(dis)));
                        resume.addSection(sectionType, new ListSection(listSection));
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
