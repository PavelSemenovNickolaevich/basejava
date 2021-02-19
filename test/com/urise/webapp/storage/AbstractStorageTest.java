package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public abstract class AbstractStorageTest {

    public final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;

    static {
        RESUME_1 = new Resume(UUID_1, "FullName1");
        RESUME_2 = new Resume(UUID_2, "FullName2");
        RESUME_3 = new Resume(UUID_3, "FullName3");
        RESUME_4 = new Resume(UUID_4, "FullName4");
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
        storage.update(newResume);
        assertResume(newResume, UUID_1);
    }

    @Test
    @DisplayName("Check that resume doesnt exist for update")
    public void updateNotExist() throws Exception {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.get("dummy");
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
    void getAll() throws Exception {
        List<Resume> resumes = storage.getAllSorted();
        Assertions.assertEquals(3, resumes.size());
        Assertions.assertEquals(RESUME_1, resumes.get(0));
        Assertions.assertEquals(RESUME_2, resumes.get(1));
        Assertions.assertEquals(RESUME_3, resumes.get(2));
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

//    @Test
//    @DisplayName("Storage of resume is overflow")
//    public void saveOverflow() throws Exception {
//        try {
//            for (int i = 3; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
//                storage.save(new Resume());
//            }
//        } catch (StorageException e) {
//            Assertions.fail("Overflow before time!");
//        }
//        Assertions.assertThrows(StorageException.class, () -> storage.save(new Resume()));
//    }

    private void assertSize(int size) {
        Assertions.assertEquals(size, storage.size());
    }

    private void assertResume(Resume resume, String uuid) {
        Assertions.assertEquals(resume, storage.get(uuid));
    }
}
