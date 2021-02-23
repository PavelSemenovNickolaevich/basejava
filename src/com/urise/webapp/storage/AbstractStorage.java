package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getPosition(String uuid);

    protected abstract void doSave(Resume resume, Object searchKey);

    protected abstract void doUpdate(Resume resume, Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract boolean isExist(Object searchKey);


    public void save(Resume resume) {
        Object index = getExistSearchKey(resume.getUuid());
        doSave(resume, index);
    }

    public void update(Resume resume) {
        Object index = getNotExistSearchKey(resume.getUuid());
        doUpdate(resume, index);
    }

    public Resume get(String uuid) {
        Object index = getNotExistSearchKey(uuid);
        return doGet((index));
    }

    public void delete(String uuid) {
        Object index =
                getNotExistSearchKey(uuid);
        doDelete(index);
    }

    private Object getExistSearchKey(String uuid) {
        Object index = getPosition(uuid);
        if (isExist(index)) {
            throw new ExistStorageException(uuid);
        }
        return index;
    }

    private Object getNotExistSearchKey(String uuid) {
        Object index = getPosition(uuid);
        if (!isExist(index)) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }

}



