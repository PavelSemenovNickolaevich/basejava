package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getPosition(String uuid);

    protected abstract void doSave(Resume resume, Object index);

    protected abstract void doUpdate(Resume resume, Object index);

    protected abstract Resume doGet(Object index);

    protected abstract void doDelete(Object index);

    protected abstract boolean isCheckExist(Object index);


    public void save(Resume resume) {
        Object index = isExist(resume.getUuid());
        doSave(resume, index);
    }

    public void update(Resume resume) {
        Object index = isNotExist(resume.getUuid());
        doUpdate(resume, index);
    }

    public Resume get(String uuid) {
        Object index = isNotExist(uuid);
        return doGet((index));
    }

    public void delete(String uuid) {
        Object index = isNotExist(uuid);
        doDelete(index);
    }

    private Object isExist(String uuid) {
        Object index = getPosition(uuid);
        if (isCheckExist(index)) {
            throw new ExistStorageException(uuid);
        }
        return index;
    }

    private Object isNotExist(String uuid) {
        Object index = getPosition(uuid);
        if (!isCheckExist(index)) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }

}



