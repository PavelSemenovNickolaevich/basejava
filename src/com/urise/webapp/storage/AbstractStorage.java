package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract int getPosition(String uuid);

    protected abstract void doSave(Resume resume);

    protected abstract void doUpdate(Resume resume, int index);

    protected abstract Resume doGet(String uuid);

    protected abstract void doDelete(String uuid);


    public void save(Resume resume) {
        isExist(resume.getUuid());
        doSave(resume);
    }

    public void update(Resume resume) {
        int index = getPosition(resume.getUuid());
        isNotExist(resume.getUuid());
        doUpdate(resume, index);
    }

    public Resume get(String uuid) {
        isNotExist(uuid);
        return doGet(uuid);
    }

    public void delete(String uuid) {
        isNotExist(uuid);
        doDelete(uuid);
    }

    private void isExist(String uuid) {
        int index = getPosition(uuid);
        if (index >= 0) {
            throw new ExistStorageException(uuid);
        }
    }

    private void isNotExist(String uuid) {
        int index = getPosition(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
    }
}



