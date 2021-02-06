package com.urise.webapp.storage;

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
    public void save(Resume resume) {
        resumeList.add(resume);
    }

    @Override
    public void listUpdate(Resume resume, int index) {
        resumeList.set(index, resume);

    }

    @Override
    public Resume get(String uuid) {
        return resumeList.get(getPosition(uuid));
    }

    @Override
    public void delete(String uuid) {
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

    public int getPosition(String uuid) {
        for (int i = 0; i < resumeList.size(); i++) {
            if (resumeList.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
