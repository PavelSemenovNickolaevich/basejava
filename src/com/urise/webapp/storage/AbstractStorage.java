package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract int getPosition(String uuid);

    protected abstract void doSave(Resume resume, int index);

    protected abstract void doUpdate(Resume resume, int index);

    protected abstract Resume doGet(int index);

    protected abstract void doDelete(int index);


    public void save(Resume resume) {
        int index = isExist(resume.getUuid());
        doSave(resume, index);
    }

    public void update(Resume resume) {
        int index = isNotExist(resume.getUuid());
        doUpdate(resume, index);
    }

    public Resume get(String uuid) {
        int index = isNotExist(uuid);
        return doGet(index);
    }

    public void delete(String uuid) {
        int index = isNotExist(uuid);
        doDelete(index);
    }

    private int isExist(String uuid) {
        int index = getPosition(uuid);
        if (index >= 0) {
            throw new ExistStorageException(uuid);
        }
        return index;
    }

    private int isNotExist(String uuid) {
        int index = getPosition(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }
}



