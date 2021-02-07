package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

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

    @Override
    public void doSave(Resume resume, int index) {
        if (STORAGE_LIMIT == size) {
            throw new StorageException("Storage is full!", resume.getUuid());
        }
        addResume(resume, index);
        size++;
    }

    @Override
    public void doUpdate(Resume resume, int index) {
        String uuid = resume.getUuid();
        index = getPosition(uuid);
        storage[index] = resume;
    }

    @Override
    public void doDelete(int index) {
        deleteResume(index);
        storage[size - 1] = null;
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resume = new Resume[size];
        if (size >= 0) System.arraycopy(storage, 0, resume, 0, size);
        return resume;
    }

    @Override
    public Resume doGet(int index) {
        return storage[index];
    }


    protected abstract int getPosition(String uuid);

    protected abstract void addResume(Resume resume, int index);

    protected abstract void deleteResume(int index);

}
