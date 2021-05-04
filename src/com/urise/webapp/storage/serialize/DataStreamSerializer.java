package com.urise.webapp.storage.serialize;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class DataStreamSerializer implements SerializeStrategy {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            writeWithException(dos, r.getContacts().entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });
            writeWithException(dos, r.getSections().entrySet(), entry -> {
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
                        writeWithException(dos, ((ListSection) section).getText(), dos::writeUTF);
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        dos.writeUTF(name);
                        writeWithException(dos, ((OrganizationSection) section).getExperienceList(), organization -> {
                            dos.writeUTF(organization.getName());
                            dos.writeUTF(organization.getUrl());
                            writeWithException(dos, organization.getExperiences(), value -> {
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

    private <T> void writeWithException(DataOutputStream dos, Collection<T> collection, Consumer<T> write) throws IOException {
        dos.writeInt(collection.size());
        for (T t : collection) {
            write.write(t);
        }
    }

    @FunctionalInterface
    public interface Consumer<T> {
        void write(T t) throws IOException;
    }

    private void writeDate(DataOutputStream dos, LocalDate date) throws IOException {
        dos.writeInt(date.getYear());
        dos.writeInt(date.getMonth().getValue());
        dos.writeInt(date.getDayOfMonth());
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readAll(dis, () -> resume.addContact(ContactsType.valueOf(dis.readUTF()), dis.readUTF()));
            readAll(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.addSection(sectionType, readSection(dis, sectionType));
            });
            return resume;
        }
    }

    private AbstractSection readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                return new SingleLineSection(dis.readUTF());
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                return new ListSection((readListSection(dis)));
            case EDUCATION:
            case EXPERIENCE:
                return new OrganizationSection(readList(dis
                        , () -> new Organization(dis.readUTF(), dis.readUTF()
                        , readList(dis
                                , () -> new Organization.Experience(readDate(dis), readDate(dis), dis.readUTF(), dis.readUTF())))));
        }
        return null;
    }

    @FunctionalInterface
    public interface ReadProcess {
        void process() throws IOException;
    }

    private void readAll(DataInputStream dis, ReadProcess action) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            action.process();
        }
    }

    private<T> List readList(DataInputStream dis, Supplier<T> supplier) throws IOException {
        int size = dis.readInt();
        List<Object> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(supplier.get());
        }
        return list;
    }

    @FunctionalInterface
    private interface Supplier<T> {
        T get() throws IOException;
    }

    private List<String> readListSection(DataInputStream dis) throws IOException {
        int size = dis.readInt();
        List<String> listSection= new ArrayList<>();
        for (int i = 0; i < size; i++) {
            listSection.add(dis.readUTF());
        }
        return listSection;
    }

    private LocalDate readDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), dis.readInt());
    }
}
