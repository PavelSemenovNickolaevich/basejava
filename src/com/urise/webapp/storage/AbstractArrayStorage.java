package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {

    protected final static int STORAGE_LIMIT = 10;
    protected static final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        String uuid = resume.getUuid();
        int index = getPosition(uuid);
        if (index >= 0) {
            throw new ExistStorageException(uuid + " not exist");
        } else if (STORAGE_LIMIT == size) {
            throw new StorageException("Storage is full!", uuid);
        } else {
            addResume(resume, index);
            size++;
        }
    }

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        int index = getPosition(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid + " not exist");
        } else {
            storage[index] = resume;
        }

    }

    public void delete(String uuid) {
        int index = getPosition(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid + " not exist");
        } else {
            deleteResume(index);
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resume = new Resume[size];
        if (size >= 0) System.arraycopy(storage, 0, resume, 0, size);
        return resume;
    }


    public Resume get(String uuid) {
        int index = getPosition(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    protected abstract int getPosition(String uuid);

    protected abstract void addResume(Resume resume, int index);

    protected abstract void deleteResume(int index);

}
