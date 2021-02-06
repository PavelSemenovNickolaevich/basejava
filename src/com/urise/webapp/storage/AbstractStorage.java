package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract void listUpdate(Resume resume, int index);

    public abstract int getPosition(String uuid);

    public void update(Resume resume) {
        int index = getPosition(resume.getUuid());
        listUpdate(resume, index);

    }
}



