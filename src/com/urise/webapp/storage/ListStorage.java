package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private final List<Resume> resumeList = new ArrayList<>();

    @Override
    public void clear() {
        resumeList.clear();
    }

    @Override
    public void doSave(Resume resume, Integer index) {
        resumeList.add(resume);
    }

    @Override
    public void doUpdate(Resume resume, Integer index) {
        resumeList.set(index, resume);
    }

    @Override
    public Resume doGet(Integer index) {
        return resumeList.get(index);
    }

    @Override
    public void doDelete(Integer searchKey) {
        resumeList.remove((int)searchKey);
    }

    @Override
    protected boolean isExist(Integer index) {
        return index >= 0;
    }

    @Override
    public List<Resume> doCopyAll() {
        return resumeList;
    }

    @Override
    public int size() {
        return resumeList.size();
    }

    @Override
    public Integer getPosition(String uuid) {
        for (int i = 0; i < resumeList.size(); i++) {
            if (resumeList.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

}
