package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public abstract class AbstractArrayStorageTest {

    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);

    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_4 = new Resume(UUID_4);

    public AbstractArrayStorageTest(Storage storage) {
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
        Assertions.assertEquals(3, storage.size());
    }

    @Test
    @DisplayName("Clear storage")
    void clear() throws Exception {
        storage.clear();
        Assertions.assertEquals(0, storage.size());
    }

    @Test
    @DisplayName("Save resume in storage")
    void save() throws Exception {
        storage.save(RESUME_4);
        Assertions.assertEquals(RESUME_4, storage.get(RESUME_4.getUuid()));
    }

    @Test
    @DisplayName("Check that resume is exist")
    public void saveExist() throws Exception {
        Assertions.assertThrows(ExistStorageException.class, () -> {
            storage.save(RESUME_1);
        });
    }

    @Test
    @DisplayName("Update resume")
    void update() throws Exception {
        Resume newResume = new Resume(UUID_1);
        storage.update(newResume);
        Assertions.assertEquals(newResume, storage.get(UUID_1));
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
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.delete(UUID_3);
            Assertions.assertEquals(2, storage.size());
            storage.get(UUID_3);
        });
    }

    @Test
    @DisplayName("Check that resume doesnt exist for delete")
    public void deleteNotExist() throws Exception {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.get("dummy");
        });
    }


    @Test
    @DisplayName("Get all resumes")
    void getAll() throws Exception {
        Resume[] resumes = storage.getAll();
        Assertions.assertEquals(3, resumes.length);
        Assertions.assertEquals(RESUME_1, resumes[0]);
        Assertions.assertEquals(RESUME_2, resumes[1]);
        Assertions.assertEquals(RESUME_3, resumes[2]);
    }

    @Test
    @DisplayName("Get resume")
    public void get() throws Exception {
        Assertions.assertEquals(RESUME_1, storage.get(RESUME_1.getUuid()));
    }

    @Test
    @DisplayName("Check that resume doesnt exist for get")
    public void getNotExist() throws Exception {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.get("dummy");
        });
    }

    @Test
    @DisplayName("Storage of resume is overflow")
    public void saveOverflow() throws Exception {
        Assertions.assertThrows(StorageException.class, () -> {
            try {
                for (int i = 3; i <= AbstractArrayStorage.STORAGE_LIMIT - 1; i++) {
                    storage.save(new Resume());
                }
            } catch (StorageException e) {
                Assertions.fail("Overflow!");
            }
            storage.save(new Resume());
        });
    }
}
