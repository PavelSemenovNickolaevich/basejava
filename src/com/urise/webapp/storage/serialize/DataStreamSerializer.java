package com.urise.webapp.storage.serialize;

import com.urise.webapp.model.*;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
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
            for (Map.Entry<SectionType, AbstractSection> entry : r.getSections().entrySet()) {
                SectionType type = entry.getKey();
                AbstractSection section = entry.getValue();
                if (type.equals(SectionType.PERSONAL)) {
                    dos.writeUTF(entry.getKey().name());
                    dos.writeUTF(String.valueOf(section));
                    continue;

                }
                if (type.equals(SectionType.OBJECTIVE)) {
                    dos.writeUTF(entry.getKey().name());
                    dos.writeUTF(String.valueOf(section));
                    continue;

                }
                if (type.equals(SectionType.ACHIEVEMENT)) {
                    dos.writeUTF(entry.getKey().name());
                  //  List<String> list = Collections.singletonList(String.valueOf(section));
                    List<String> list = Arrays.asList(String.valueOf(section));
                    for (String value : list) {
                        dos.writeUTF(value);
                    }
                    continue;
                }
                if (type.equals(SectionType.QUALIFICATIONS)) {
                    dos.writeUTF(entry.getKey().name());
       //             List<String> list = Collections.singletonList(String.valueOf(section));
                    List<String> list = Arrays.asList(String.valueOf(section));
                    for (String value : list) {
                        dos.writeUTF(value);
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
            for (int i = 0; i < 4; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                if (sectionType.equals(SectionType.PERSONAL)) {
                    resume.addSection(sectionType, new SingleLineSection(dis.readUTF()));
                }
                if (sectionType.equals(SectionType.OBJECTIVE)) {
                    resume.addSection(sectionType, new SingleLineSection(dis.readUTF()));
                }
                if (sectionType.equals(SectionType.ACHIEVEMENT)) {
                    resume.addSection(sectionType, new ListSection(dis.readUTF()));
                }
                if (sectionType.equals(SectionType.QUALIFICATIONS)) {
                    resume.addSection(sectionType, new ListSection(dis.readUTF()));
                }
            }
            return resume;
        }
    }
}
