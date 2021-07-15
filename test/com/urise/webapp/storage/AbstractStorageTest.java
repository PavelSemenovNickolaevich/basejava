package com.urise.webapp.storage;

import com.urise.webapp.Config;
import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.ContactsType;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.urise.webapp.storage.ResumeTestData.createResume;

public abstract class AbstractStorageTest {

    protected static final File STORAGE_DIR = Config.get().getStorageDir();

    public final Storage storage;

    private static final String UUID_1 = UUID.randomUUID().toString();
  //  private static final String UUID_1 = "7346e9a7-b774-485d-943a-0fb3040d6c48";
    private static final String UUID_2 = UUID.randomUUID().toString();
  //  private static final String UUID_2 = "970d5022-9a00-4d21-b1f3-dc76a222a0b2";
    private static final String UUID_3 = UUID.randomUUID().toString();
  //  private static final String UUID_3 = "87d59185-5693-4419-86e5-af2a4cdd498f";
    private static final String UUID_4 = UUID.randomUUID().toString();

//    private static final String UUID_1 = "1";
//    private static final String UUID_2 = "2";
//    private static final String UUID_3 =  "3";
//    private static final String UUID_4 =  "4";
    //
    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;


    static {
//        RESUME_1 = createResume(UUID_1, "FullName1");
//        RESUME_2 = createResume(UUID_2, "FullName2");
//        RESUME_3 = createResume(UUID_3, "FullName3");
//        RESUME_4 = createResume(UUID_4, "FullName4");

   //     RESUME_1 = new Resume(UUID_1, "FullName1");
        RESUME_1 = new Resume(UUID_1, "FullName1");
        RESUME_2 = new Resume(UUID_2, "FullName2");
        RESUME_3 = new Resume(UUID_3, "FullName3");
        RESUME_4 = new Resume(UUID_4, "FullName4");

        RESUME_1.addContact(ContactsType.MAIL, "terminator@gmail.com");
        RESUME_1.addContact(ContactsType.PHONE, "34444444");

        RESUME_4.addContact(ContactsType.MAIL, "Mail1");
        RESUME_4.addContact(ContactsType.PHONE, "344444411114");
    }

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    @DisplayName("Storage size")
    public void size() throws Exception {
        assertSize(3);
    }

    @Test
    @DisplayName("Clear storage")
    void clear() throws Exception {
        storage.clear();
        assertSize(0);
    }

    @Test
    @DisplayName("Save resume in storage")
    void save() throws Exception {
        storage.save(RESUME_4);
        assertSize(4);
        assertResume(RESUME_4, UUID_4);
    }

    @Test
    @DisplayName("Check that resume is exist")
    public void saveExist() throws Exception {
        Assertions.assertThrows(ExistStorageException.class, () -> storage.save(RESUME_1));

    }

    @Test
    @DisplayName("Update resume")
    void update() throws Exception {
        Resume newResume = new Resume(UUID_1, "NewResumeWithFullName");
        newResume.addContact(ContactsType.MAIL, "123");
        newResume.addContact(ContactsType.PHONE, "34446666644444");
        newResume.addContact(ContactsType.SKYPE, "Skype");
        storage.update(newResume);
        assertResume(newResume, UUID_1);
    }

    @Test
    @DisplayName("Check that resume doesnt exist for update")
    public void updateNotExist() throws Exception {
        Resume newResume = new Resume("1234567890", "NewResumeWithFullName1");
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.update(newResume);
        });
    }

    @Test
    @DisplayName("Delete resume")
    void delete() throws Exception {
        storage.delete(UUID_3);
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_3));
        assertSize(2);
    }

    @Test
    @DisplayName("Check that resume doesnt exist for delete")
    public void deleteNotExist() throws Exception {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.delete(UUID_4);
        });
    }

    @Test
    @DisplayName("Get all resumes")
    void getAllSorted() throws Exception {
        List<Resume> resumes = storage.getAllSorted();
        Assertions.assertEquals(3, resumes.size());
        Assertions.assertEquals(resumes, Arrays.asList(RESUME_1, RESUME_2, RESUME_3));

    }

    @Test
    @DisplayName("Get resume")
    public void get() throws Exception {
        assertResume(RESUME_1, UUID_1);
    }

    @Test
    @DisplayName("Check that resume doesnt exist for get")
    public void getNotExist() throws Exception {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.get("dummy");
        });
    }

    private void assertSize(int size) {
        Assertions.assertEquals(size, storage.size());
    }

    private void assertResume(Resume resume, String uuid) {
        Assertions.assertEquals(resume, storage.get(uuid));
    }
}
