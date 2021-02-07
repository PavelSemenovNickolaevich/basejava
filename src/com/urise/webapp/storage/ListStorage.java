package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> resumeList = new ArrayList<>();


    @Override
    public void clear() {
        resumeList.clear();
    }

    @Override
    public void doSave(Resume resume) {
        String uuid = resume.getUuid();
        if (resumeList.size() == AbstractArrayStorage.STORAGE_LIMIT) {
            throw new StorageException("Storage is full!", uuid);
        }
        resumeList.add(resume);
    }

    @Override
    public void doUpdate(Resume resume, int index) {
        resumeList.set(index, resume);
    }

    @Override
    public Resume doGet(String uuid) {
        return resumeList.get(getPosition(uuid));
    }

    @Override
    public void doDelete(String uuid) {
        resumeList.remove(getPosition(uuid));
    }

    @Override
    public Resume[] getAll() {
        return resumeList.toArray(new Resume[resumeList.size()]);
    }

    @Override
    public int size() {
        return resumeList.size();
    }

    @Override
    public int getPosition(String uuid) {
        for (int i = 0; i < resumeList.size(); i++) {
            if (resumeList.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

}
