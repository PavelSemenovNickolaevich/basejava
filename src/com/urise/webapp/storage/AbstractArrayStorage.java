package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

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
    public void doSave(Resume resume, Object index) {
        if (STORAGE_LIMIT == size) {
            throw new StorageException("Storage is full!", resume.getUuid());
        }
        addResume(resume, (Integer) index);
        size++;
    }

    @Override
    public void doUpdate(Resume resume, Object index) {
        storage[(int) index] = resume;
    }

    @Override
    public void doDelete(Object index) {
        deleteResume((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */

    @Override
    public List<Resume> getAllSorted() {
        Resume[] resume = new Resume[size];
        if (size >= 0) System.arraycopy(storage, 0, resume, 0, size);
        return Arrays.asList(resume);
    }

    @Override
    public Resume doGet(Object index) {
        return storage[(int) index];
    }


    protected abstract Object getPosition(String uuid);

    protected abstract void addResume(Resume resume, int index);

    protected abstract void deleteResume(int index);

}
