package com.urise.webapp.storage.serialize;

import com.urise.webapp.model.*;

import java.io.*;
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
                        List<String> list = new ArrayList<>();
                        for (String value : ((ListSection) section).getText()) {
                            dos.writeUTF(value);
                            list.add(value);
                            dos.writeInt(list.size());
                        }
                }
            }
            //TODO implements sections
        }
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
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        resume.addSection(sectionType, new ListSection(readListSection(dis)));
                }
            }
            return resume;
        }
    }

    private List<String> readListSection(DataInputStream dis) throws IOException {
        int size = dis.readInt();
        System.out.println(size);
        List<String> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(dis.readUTF());
        }
        return list;
    }

}
